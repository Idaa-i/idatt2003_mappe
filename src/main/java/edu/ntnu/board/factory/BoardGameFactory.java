package edu.ntnu.board.factory;

import edu.ntnu.board.Board;
import edu.ntnu.game.BoardGame;
import edu.ntnu.board.LadderAction;
import edu.ntnu.board.SnakeAction;

/**
 * BoardGameFactory for creating different board game variants.
 */
public class BoardGameFactory {

    /**
     * Creates a predefined board game.
     * @param type The type of board game to create.
     * @return A new BoardGame instance.
     */
    public static BoardGame createBoardGame(String type, int numPlayers) {
        switch (type.toLowerCase()) {
            case "easy":
                return createEasyBoardGame(numPlayers);
            case "hard":
                return createHardBoardGame(numPlayers);
            default:
                throw new IllegalArgumentException("Unknown board game type: " + type);
        }
    }

    /**
     * Creates an easy board game with predefined ladders and snakes.
     * @return A BoardGame instance.
     */
    private static BoardGame createEasyBoardGame(int numPlayers) {
        Board board = new Board(90);
        board.getTile(4).setAction(new LadderAction(14));
        board.getTile(8).setAction(new LadderAction(30));
        board.getTile(50).setAction(new SnakeAction(10));
        board.getTile(80).setAction(new SnakeAction(20));
        return new BoardGame(board, numPlayers);
    }
    }