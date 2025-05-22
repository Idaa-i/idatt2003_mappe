package edu.ntnu.model.actions;

public class SnakeActionTest extends AbstractTileActionTest {

  @Override
  protected TileAction createAction(int destination) {
    return new SnakeAction(destination);
  }

  @Override
  protected int expectedResult(int inputPosition, int destination) {
    return destination;
  }
}
