package edu.ntnu.model.board;

import edu.ntnu.model.actions.TileAction;

/**
 * Class representing a single tile on the game board.
 */
public class Tile {
  private int position;
  private TileAction action;

  /**
   * The constructor for the Tile class.
   * Constructs a tile with a specific position
   *
   * @param position the position of the tile on the board
   */
  public Tile(int position) {
    this.position = position;
  }

  /**
   * Method for returning the position of the tile.
   *
   * @return the tile's position
   */
  public int getPosition() {
    return position;
  }

  /**
   * Method for returning the action associated with this tile, if any.
   *
   * @return the tile's action
   */
  public TileAction getAction() {
    return action;
  }

  /**
   * Method for setting an action to be executed when a player land on this tile.
   *
   * @param action the action to associate with this tile
   */
  public void setAction(TileAction action) {
    this.action = action;
  }

  /**
   * Executes the tile's action, if one is set.
   *
   * @param currentPosition the player's current position
   * @return the new postiion after executing the action, or the same position if no action is set
   */
  public int executeAction(int currentPosition) {
    if (action != null) {
      return action.execute(currentPosition);
    }
    return currentPosition;
  }
}