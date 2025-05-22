package edu.ntnu.model.actions;

public class BackToStartActionTest extends AbstractTileActionTest {

  @Override
  protected TileAction createAction(int destination) {
    return new BackToStartAction(destination);
  }

  @Override
  protected int expectedResult(int inputPosition, int destination) {
    return destination;
  }
}
