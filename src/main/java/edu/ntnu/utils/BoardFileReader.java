package edu.ntnu.utils;

import edu.ntnu.model.board.Board;
import java.nio.file.Path;

/**
 * Interface representing a reader that can load a game board from a file.
 */
public interface BoardFileReader {
  /**
   * Method for reading a board configuration from the specified file path.
   *
   * @param path the path to the board configuration file path
   * @return a Board-object representing the loaded board
   */
  Board readBoard(Path path);
}