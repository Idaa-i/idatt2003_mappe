package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.BoardGame;
import edu.ntnu.views.BoardGameObserverImplementation;
import edu.ntnu.views.GameView;

/**
 * Controller class responsible for handling the game logic and mediating interactions.
 */
public class BoardGameController {
  private BoardGame model;
  private GameView view;
  private int currentPlayerIndex = 0;

  /**
   * Constructor for BoardGameController class.
   * Constructs a BoardGameController with the specified model and view
   *
   * @param model the BoardGame model to control
   * @param view  the GameView to update and display information
   */
  public BoardGameController(BoardGame model, GameView view) {
    this.model = model;
    this.view = view;
    model.addObserver(new BoardGameObserverImplementation(view));
  }

  /**
   * Method for executing a game turn for the current player using the provided dice roll.
   *
   * @param roll the result of the dice roll
   */
  public void playTurn(int roll) {
    Player currentPlayer = model.getPlayers().get(currentPlayerIndex);

    if (currentPlayer.isSkipOneRound()) {
      view.showActionMessage(currentPlayer.getName() + " skips this round");
      currentPlayer.setSkipOneRound(false);
      advanceToNextPlayer();
      return;
    }

    String moveMessage = currentPlayer.move(roll, model.getBoard());
    view.showActionMessage(moveMessage);
    model.notifyPlayerMove(currentPlayer);

    if (currentPlayer.hasWon()) {
      model.setGameOver(true);
      model.notifyPlayerWon(currentPlayer);
      view.announceWinner(currentPlayer);
    } else {
      advanceToNextPlayer();
    }
  }

  /**
   * Method for advancing the turn to the next player in the list.
   * Wraps around to the first player if the end of the list is reached
   */
  private void advanceToNextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % model.getPlayers().size();
  }
}