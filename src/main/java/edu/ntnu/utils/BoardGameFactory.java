package edu.ntnu.utils;

import edu.ntnu.model.*;
import edu.ntnu.model.actions.*;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.BoardGame;
import java.nio.file.Path;
import java.util.List;

public class BoardGameFactory {
    private static final BoardFileReaderGson boardReader = new BoardFileReaderGson();
    public static BoardGame createBoardGame(String type, List<Player> players) {
        switch (type.toLowerCase()) {
            case "easy": return createEasyBoardGame(players);
            case "hard": return createHardBoardGame(players);
            default: throw new IllegalArgumentException("Unknown board game type: " + type);
        }
    }

    private static BoardGame createEasyBoardGame(List<Player> players) {
        Board board = boardReader.readBoard(Path.of("src/main/resources/files/EasyBoard.json"));
        BoardGame game = new BoardGame(board, players, 1);
        for (Player player : game.getPlayers()) {
            player.setBoard(board);
        }
        return game;
    }

    private static BoardGame createHardBoardGame(List<Player> players) {
        Board board = boardReader.readBoard(Path.of("src/main/resources/files/HardBoard.json"));
        BoardGame game = new BoardGame(board, players, 2);
        for (Player player : game.getPlayers()) {
            player.setBoard(board);
        }
        return game;
    }
}