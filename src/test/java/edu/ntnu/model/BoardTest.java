package edu.ntnu.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  private Board board;
  private Tile tile1;
  private Tile tile2;

  @BeforeEach
  void setUp() {
    board = new Board(90); //Updated for correct boardsize
    tile1 = new Tile(1);
    tile2 = new Tile(2);
  }

  @Test
  void testAddTile(){
    board.addTile(tile1);
    board.addTile(tile2);
    assertEquals(tile1, board.getTile(1));
  }

  @Test
  void testGetTileValid() {
    board.addTile(tile1);
    board.addTile(tile2);
    assertEquals(tile2, board.getTile(2));
  }

  @Test
  void testGetTileInvalid() {
    assertNull(board.getTile(91));
  }
  @Test
  void testGetStartTile() {
    assertEquals(1, board.getStartTile().getPosition()); // Test that start tile is correct
  }
}
