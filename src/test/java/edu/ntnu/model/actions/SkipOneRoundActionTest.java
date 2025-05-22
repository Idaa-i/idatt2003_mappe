package edu.ntnu.model.actions;

public class SkipOneRoundActionTest extends AbstractTileActionTest {

  @Override
  protected TileAction createAction(int destination) {
    return new SkipOneRoundAction();  // destination irrelevant her
  }

  @Override
  protected int expectedResult(int inputPosition, int destination) {
    return inputPosition;
  }
}
