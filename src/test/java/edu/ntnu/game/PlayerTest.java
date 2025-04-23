package edu.ntnu.game;

import edu.ntnu.model.Board;
import edu.ntnu.model.Player;
import edu.ntnu.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Board board;
    private Player player;
    private Tile startTile;

    @BeforeEach
    void setUp() {
        board = new Board(90);
        startTile = new Tile(1);
        board.addTile(startTile);
        player = new Player("TestPlayer", startTile);
    }

    @Test
    void testGetName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testMove() {
        Tile tile2 = new Tile(2);
        board.addTile(tile2);
        player.move(1, board);
        assertEquals(tile2, board.getTile(2));
    }

    @Test
    void testHasWon() {
        Tile winningTile = new Tile(90);
        board.addTile(winningTile);
        player.move(89, board);
        assertTrue(player.hasWon());
    }
}
