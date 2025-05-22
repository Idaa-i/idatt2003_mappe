package edu.ntnu.model.board;

import edu.ntnu.model.actions.LadderAction;
import edu.ntnu.model.actions.SkipOneRoundAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
  @Test
  void testGetPosition() {
    Tile tile = new Tile(5);
    assertEquals(5, tile.getPosition());
  }

  @Test
  void testSetAndGetAction() {
    Tile tile = new Tile(1);
    LadderAction action = new LadderAction(10);

    tile.setAction(action);
    assertEquals(action, tile.getAction());
  }

  @Test
  void testExecuteActionWhenNoActionSet() {
    Tile tile = new Tile(3);
    assertEquals(3, tile.executeAction(3));
  }

  @Test
  void testExecuteActionWhenActionIsSet() {
    Tile tile = new Tile(4);
    tile.setAction(new LadderAction(9));

    assertEquals(9, tile.executeAction(4));
  }

  @Test
  void testExecuteSkipAction() {
    Tile tile = new Tile(6);
    tile.setAction(new SkipOneRoundAction());

    assertEquals(6, tile.executeAction(6));
  }
}
