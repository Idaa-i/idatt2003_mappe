package edu.ntnu.model.actions;

/**
 * Class representing an action that sends a player back to the starttile.
 */
public class BackToStartAction implements TileAction {
  private int destination;

  /**
   * Constructor for BackToStartAction class.
   *
   * @param destination the starttile
   */
  public BackToStartAction(int destination) {
    this.destination = destination;
  }

  /**
   * Method for executing the action.
   *
   * @param position the current position of the player
   * @return the destination tile position
   */
  public int execute(int position) {
    return destination;
  }
}