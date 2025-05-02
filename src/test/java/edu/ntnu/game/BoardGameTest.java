package edu.ntnu.game;

import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.BoardGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {
    private BoardGame boardGame;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(90);
        boardGame = new BoardGame(board, 4, 2);
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