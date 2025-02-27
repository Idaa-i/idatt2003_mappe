package edu.ntnu;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {
  private Board board;
  private Tile tile1;
  private Tile tile2;

  @BeforeEach
  void setUp() {
    board = new Board();
    tile1 = new Tile(1, "Start");
    tile2 = new Tile(2, "Finish");
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
    assertNull(board.getTile(3));
  }
}