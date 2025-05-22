package edu.ntnu.model.actions;

/**
 * Class representing an action that can be executed when a player lands on this tile.
 * Implementing classes define specific behaviors such as skipping a turn, moving player to a new
 * position or sending the player back to start
 */
public interface TileAction {
  /**
   * Method for executing the action associated with a tile.
   *
   * @param position the current position of the player
   * @return the new position after the action is executed
   */
  int execute(int position);
}