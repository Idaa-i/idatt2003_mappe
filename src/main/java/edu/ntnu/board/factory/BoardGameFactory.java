package edu.ntnu.board.factory;

import edu.ntnu.board.*;
import edu.ntnu.game.BoardGame;

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

    /**
     * Creates a hard board game with more difficult ladder and snake placements.
     * @return A BoardGame instance.
     */
    private static BoardGame createHardBoardGame(int numPlayers) {
        Board board = new Board(90);
        board.getTile(3).setAction(new LadderAction(22));
        board.getTile(6).setAction(new LadderAction(15));
        board.getTile(17).setAction(new SnakeAction(5));
        board.getTile(28).setAction(new SnakeAction(8));
        board.getTile(38).setAction(new BackToStartAction(1));
        board.getTile(32).setAction(new SkipOneRoundAction(32));
        return new BoardGame(board, numPlayers);
    }

    }