package edu.ntnu.controller;

import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Player;
import edu.ntnu.views.GameView;

/**
 * Class for handling game logic and mediating the interactions between BoardGame and GameView
 */
public class BoardGameController {
  private BoardGame model;
  private GameView view;
  private int currentPlayerIndex = 0;

  /**
   * Constructs a BoardGameController with the specified model and view.
   * @param model the BoardGame model to control
   * @param view the GameView to update and display information
   */
  public BoardGameController(BoardGame model, GameView view) {
    this.model = model;
    this.view = view;
    model.addObserver(new BoardGameObserverImplementation(view));
  }

  /**
   * Method for executing game turn for all players.
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

  private void advanceToNextPlayer() {
    currentPlayerIndex = (currentPlayerIndex + 1) % model.getPlayers().size();
  }
}