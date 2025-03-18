package edu.ntnu.board.factory;

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
    public static BoardGame createBoardGame(String type) {
        switch (type.toLowerCase()) {
            case "classic":
                return createEasyBoardGame();
            case "hard":
                return createHardBoardGame();
            default:
                throw new IllegalArgumentException("Unknown board game type: " + type);
        }
    }
