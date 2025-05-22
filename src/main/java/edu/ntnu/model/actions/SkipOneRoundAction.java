package edu.ntnu.model.actions;

/**
 * Class representing an action where the player must skip the next round.
 */
public class SkipOneRoundAction implements TileAction {
  private int position;

  /**
   * Constructor for SkipOneRoundAction class.
   */
  public SkipOneRoundAction() {
  }

  /**
   * Method for executing the skip action.
   *
   * @param position the current position of the player
   * @return also the current position of the player
   */
  public int execute(int position) {
    return position;
  }
}
