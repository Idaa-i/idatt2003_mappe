package edu.ntnu.model.actions;

/**
 * Class representing ladder up action in board game.
 */
public class LadderAction implements TileAction {
  private int destination;

  /**
   * Constructor for LadderAction class.
   *
   * @param destination the tile position the player will be moved to
   */
  public LadderAction(int destination) {
    this.destination = destination;
  }

  /**
   * Method for executing the ladder action, moving the player to the destination tile.
   *
   * @param position the current position of the player
   * @return the position of the destination tile
   */
  public int execute(int position) {
    return destination;
  }
}