package edu.ntnu.model.actions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTileActionTest {

  protected abstract TileAction createAction(int destination);

  protected abstract int expectedResult(int inputPosition, int destination);

  @Test
  void testExecuteReturnsExpectedResult() {
    TileAction action = createAction(10);

    assertEquals(expectedResult(5, 10), action.execute(5));
    assertEquals(expectedResult(0, 10), action.execute(0));
    assertEquals(expectedResult(100, 10), action.execute(100));
  }
}
