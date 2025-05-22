package edu.ntnu.model.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
  @Test
  void testBoardInitialization() {
    Board board = new Board(10);
    assertEquals(10, board.getSize());
    assertNotNull(board.getTile(1));
    assertNotNull(board.getTile(10));
  }

  @Test
  void testGetStartTile() {
    Board board = new Board(5);
    assertEquals(1, board.getStartTile().getPosition());
  }

  @Test
  void testGetTile() {
    Board board = new Board(4);
    Tile tile = board.getTile(3);
    assertNotNull(tile);
    assertEquals(3, tile.getPosition());
  }
}
