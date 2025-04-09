package edu.ntnu.game;

import edu.ntnu.model.Board;
import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {
    private BoardGame boardGame;
    private Board board;
    private Dice dice;

    @BeforeEach
    void setUp() {
        boardGame = new BoardGame(board,2);
        board = new Board(90);
        dice = new Dice(2);
    }

    @Test
    void testGameInitialization() {
        assertNotNull(boardGame);
    }

    @Test
    void testGameNotOverAtStart() {
        assertFalse(boardGame.isGameOver());
    }
}