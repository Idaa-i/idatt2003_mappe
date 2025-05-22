package edu.ntnu.utils;

import edu.ntnu.model.board.Board;
import java.nio.file.Path;

public interface BoardFileReader {
  Board readBoard(Path path);
}