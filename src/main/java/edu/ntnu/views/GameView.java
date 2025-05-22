package edu.ntnu.views;

import edu.ntnu.model.Player;

/**
 * Interface responsible for outlining the methods required for updating the visual representation
 * of a boardgame.
 * Serves as a bridge between the game logic and the user interface
 */
public interface GameView {
  /**
   * Method for updating the visual position of the specified player on the gameboard.
   *
   * @param player the player whose position should be updated
   */
  void updatePlayerPosition(Player player);

  /**
   * Method for announcing the winner of the game in the user interface.
   *
   * @param winner the player who has won the game
   */
  void announceWinner(Player winner);

  /**
   * Method for displaying a message describing the current action or event in the game.
   *
   * @param message the message to be shown to the user
   */
  void showActionMessage(String message);
}