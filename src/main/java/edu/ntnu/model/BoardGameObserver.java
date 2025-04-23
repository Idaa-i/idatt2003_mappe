package edu.ntnu.model;

/**
 * This class represents a boardgame observer which handles notifications about state changes in
 * the BoardGame class.
 */
public interface BoardGameObserver {
  /**
   * This method will be called whenever a player moves to a new position.
   *
   * @param player      the player who moved
   * @param newPosition the new position of the player
   */
  void onPlayerMove(Player player, Tile newPosition);

  /**
   * This method will be called whenever a player wins the game.
   *
   * @param winner the player that won
   */
  void onPlayerWin(Player winner);
}
