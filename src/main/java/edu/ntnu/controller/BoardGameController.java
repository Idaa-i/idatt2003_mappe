package edu.ntnu.controller;

import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Player;
import edu.ntnu.view.GameView;

/**
 * Class for handling game logic and mediating the interactions between BoardGame and GameView
 */
public class BoardGameController {
  private BoardGame model;
  private GameView view;

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
  public void playTurn() {
    for (Player player : model.getPlayers()) {
      if (player.isSkipOneRound()) {
        view.showMessage(player.getName() + " skips this round");
        player.setSkipOneRound(false);
        continue;
      }
      int roll = model.rollDice();
      view.showMessage(player.getName() + " rolled " + roll);
      player.move(roll, model.getBoard());
      model.notifyPlayerMove(player);

      if (player.hasWon()) {
        model.setGameOver(true);
        model.notifyPlayerWon(player);
        break;
      }
    }
  }
}