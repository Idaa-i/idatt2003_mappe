package edu.ntnu.model;

import edu.ntnu.model.actions.LadderAction;
import edu.ntnu.model.actions.SnakeAction;
import edu.ntnu.model.actions.TileAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileActionTest {
    @Test
    void testLadderAction() {
        TileAction ladder = new LadderAction(10);
        assertEquals(10, ladder.execute(5));
    }

    @Test
    void testSnakeAction() {
        TileAction snake = new SnakeAction(2);
        assertEquals(2, snake.execute(8));
    }

}