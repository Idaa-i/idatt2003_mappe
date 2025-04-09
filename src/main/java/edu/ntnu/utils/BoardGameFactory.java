package edu.ntnu.utils;

import edu.ntnu.model.*;

import java.util.Map;

public class BoardGameFactory {
    public static BoardGame createBoardGame(String type, int numPlayers) {
        switch (type.toLowerCase()) {
            case "easy": return createEasyBoardGame(numPlayers);
            case "hard": return createHardBoardGame(numPlayers);
            default: throw new IllegalArgumentException("Unknown board game type: " + type);
        }
    }

    private static BoardGame createEasyBoardGame(int numPlayers) {
        Board board = new Board(36);
        board.getTile(14).setAction(new SnakeAction(2));
        board.getTile(30).setAction(new SnakeAction(19));

        board.getTile(6).setAction(new LadderAction(8));
        board.getTile(27).setAction(new LadderAction(33));

        board.getTile(26).setAction(new SkipOneRoundAction());

        return new BoardGame(board, numPlayers, 1); // Én terning for Easy
    }

    private static BoardGame createHardBoardGame(int numPlayers) {
        Board board = new Board(90);

        board.getTile(22).setAction(new SnakeAction(2));
        board.getTile(27).setAction(new SnakeAction(9));
        board.getTile(58).setAction(new SnakeAction(36));
        board.getTile(70).setAction(new SnakeAction(52));
        board.getTile(86).setAction(new SnakeAction(64));

        board.getTile(6).setAction(new LadderAction(16));
        board.getTile(34).setAction(new LadderAction(46));
        board.getTile(79).setAction(new LadderAction(83));

        board.getTile(38).setAction(new BackToStartAction(1));
        board.getTile(67).setAction(new BackToStartAction(1));

        board.getTile(32).setAction(new SkipOneRoundAction());
        board.getTile(41).setAction(new SkipOneRoundAction());
        return new BoardGame(board, numPlayers, 2); // To terninger for Hard
    }

    public static BoardGame createCustomBoardGame(int numTiles, Map<Integer, TileAction> tileActions, int numPlayers) {
        Board board = new Board(numTiles);
        for (Map.Entry<Integer, TileAction> entry : tileActions.entrySet()) {
            board.getTile(entry.getKey()).setAction(entry.getValue());
        }
        return new BoardGame(board, numPlayers, 1); // Standard til én terning
    }
}