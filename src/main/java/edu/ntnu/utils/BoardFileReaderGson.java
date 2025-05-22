package edu.ntnu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.model.actions.TileAction;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.Tile;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Implementation of BoardFileReader that read a board configuration from a JSON file.
 */
public class BoardFileReaderGson implements BoardFileReader {
  /**
   * Method for reading a board configuration from the specified file path and deserializes it into
   * a Board-object.
   *
   * @param path the path to theJSON file containing the board configuration
   * @return a Board-object representing the loaded board
   */
  @Override
  public Board readBoard(Path path) {
    try {
      String json = Files.readString(path);
      return deserializeBoard(json);
    } catch (IOException e) {
      throw new RuntimeException("Error reading board from file: " + path, e);
    }
  }

  /**
   * Method for deserializing a JSON string into a Board-object.
   *
   * @param json the JSON string representing the board
   * @return the deserialized Board-object
   */
  private Board deserializeBoard(String json) {
    Gson gson = new GsonBuilder()
        .registerTypeAdapter(TileAction.class, new TileActionAdapter())
        .create();

    Type boardType = new TypeToken<Board>() {
    }.getType();
    Board board = gson.fromJson(json, boardType);

    int boardSize = board.getTiles().keySet().stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElseThrow(() -> new RuntimeException("No tiles found in JSON"));

    for (int i = 1; i <= boardSize; i++) {
      if (!board.getTiles().containsKey(i)) {
        board.addTile(new Tile(i));
      }
    }

    for (Tile tile : board.getTiles().values()) {
      tile.setAction(tile.getAction());
    }

    return board;
  }
}