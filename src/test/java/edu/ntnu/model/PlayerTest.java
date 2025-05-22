package edu.ntnu.model;

import edu.ntnu.model.actions.LadderAction;
import edu.ntnu.model.actions.SkipOneRoundAction;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  private Board board;
  private Player player;

  @BeforeEach
  void setUp() {
    board = new Board(10);
    player = new Player("Alice", "Red", board.getStartTile());
    player.setBoard(board);
  }

  @Test
  void testInitialValues() {
    assertEquals("Alice", player.getName());
    assertEquals("Red", player.getColor());
    assertEquals(board.getStartTile(), player.getCurrentTile());
    assertFalse(player.isSkipOneRound());
  }

  @Test
  void testSetters() {
    player.setName("Bob");
    player.setColor("Blue");
    assertEquals("Bob", player.getName());
    assertEquals("Blue", player.getColor());
  }

  @Test
  void testMoveWithoutAction() {
    String result = player.move(3, board);
    assertTrue(result.contains("rolled 3"));
    assertEquals(4, player.getCurrentTile().getPosition());
  }

  @Test
  void testMoveOntoLadder() {
    board.getTile(4).setAction(new LadderAction(8));
    player.setCurrentTile(board.getTile(1));
    String result = player.move(3, board); // Moves to tile 4, then up to 8
    assertTrue(result.contains("Landed on ladder up"));
    assertEquals(8, player.getCurrentTile().getPosition());
  }

  @Test
  void testMoveOntoSkipTile() {
    board.getTile(4).setAction(new SkipOneRoundAction());
    player.setCurrentTile(board.getTile(1));
    String result = player.move(3, board); // Moves to 4, triggers skip
    assertTrue(result.contains("Landed on skip-tile"));
    assertTrue(player.isSkipOneRound());
    assertEquals(4, player.getCurrentTile().getPosition());

    String skipped = player.move(4, board);
    assertTrue(skipped.contains("skips this round"));
    assertFalse(player.isSkipOneRound());
  }

  @Test
  void testWinCondition() {
    player.setCurrentTile(board.getTile(board.getSize()));
    player.setBoard(board);
    assertTrue(player.hasWon());
  }

  @Test
  void testMoveBounceBackIfRollTooHigh() {
    player.setCurrentTile(board.getTile(9));
    String result = player.move(3, board);
    assertTrue(result.contains("Moving to tile 8"));
    assertEquals(8, player.getCurrentTile().getPosition());
  }
}
