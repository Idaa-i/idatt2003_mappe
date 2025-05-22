package edu.ntnu.views;

import edu.ntnu.model.BoardGameObserver;
import edu.ntnu.model.Player;
import edu.ntnu.model.board.Tile;

/**
 * Implementation of BoardGameObserver to handle updates from BoardGame and notify the view.
 */
public class BoardGameObserverImplementation implements BoardGameObserver {
  private final GameView view;

  /**
   * Constructs a BoardGameObserverImpl with the specified view.
   *
   * @param view the GameView to update
   */
  public BoardGameObserverImplementation(GameView view) {
    this.view = view;
  }

  /**
   * Method that will be called when a player moved to a new tile.
   * Notifies the view to update
   *
   * @param player      the player who moved
   * @param newPosition the new position of the player who moved
   */
  @Override
  public void onPlayerMove(Player player, Tile newPosition) {
    view.updatePlayerPosition(player);
  }

  /**
   * Method that will be called when a player wins the game.
   * Notifies the view to announce the winner
   *
   * @param winner the player that won the game
   */
  @Override
  public void onPlayerWin(Player winner) {
    view.announceWinner(winner);
  }
}