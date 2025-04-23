package edu.ntnu.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {
    private Tile tile;
    private TileAction action;

    @BeforeEach
    void setUp() {
        tile = new Tile(5);
        action = position -> position + 2;
        tile.setAction(action);
    }

    @Test
    void testGetPosition() {
        assertEquals(5, tile.getPosition());
    }

    @Test
    void testExecuteAction() {
        int newPosition = tile.executeAction(5);
        assertEquals(7, newPosition);
    }
}