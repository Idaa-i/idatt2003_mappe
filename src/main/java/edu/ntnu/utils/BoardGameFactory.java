package edu.ntnu.utils;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.BoardGame;
import java.nio.file.Path;
import java.util.List;

/**
 * Class representing a BoardGameFactory.
 * Responsible for creating BoardGame instances based on predefined difficulty levels
 */
public class BoardGameFactory {
  private static final BoardFileReaderGson boardReader = new BoardFileReaderGson();

  /**
   * Constructor for BoardGameFactory class.
   * Creates a BoardGame instance based on the specified type
   *
   * @param type    the type of board game to create (easy or hard)
   * @param players the list of players participating in the game
   * @return a configured BoardGame instance
   * @throws IllegalArgumentException if the type is unknown
   */
  public static BoardGame createBoardGame(String type, List<Player> players) {
    switch (type.toLowerCase()) {
      case "easy":
        return createEasyBoardGame(players);
      case "hard":
        return createHardBoardGame(players);
      default:
        throw new IllegalArgumentException("Unknown board game type: " + type);
    }
  }

  /**
   * Method for creating an "easy" boardgame with a single die and a simpler board layout.
   *
   * @param players the list of players
   * @return a BoardGame instance with the easy board configuration
   */
  private static BoardGame createEasyBoardGame(List<Player> players) {
    Board board = boardReader.readBoard(Path.of("src/main/resources/files/EasyBoard.json"));
    BoardGame game = new BoardGame(board, players, 1);
    game.getPlayers().forEach(player -> player.setBoard(board));
    return game;
  }

  /**
   * Method for creating a "hard" boardgame with two dice and more complex board layout.
   *
   * @param players the list of players
   * @return a BoardGame instance with the hard board configuration.
   */
  private static BoardGame createHardBoardGame(List<Player> players) {
    Board board = boardReader.readBoard(Path.of("src/main/resources/files/HardBoard.json"));
    BoardGame game = new BoardGame(board, players, 2);
    game.getPlayers().forEach(player -> player.setBoard(board));
    return game;
  }
}