package edu.ntnu.model.actions;

public class LadderActionTest extends AbstractTileActionTest {

  @Override
  protected TileAction createAction(int destination) {
    return new LadderAction(destination);
  }

  @Override
  protected int expectedResult(int inputPosition, int destination) {
    return destination;
  }
}
