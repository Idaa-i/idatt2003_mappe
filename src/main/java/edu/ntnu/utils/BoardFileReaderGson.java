package edu.ntnu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.model.actions.*;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.Tile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class BoardFileReaderGson implements BoardFileReader {
  @Override
  public Board readBoard(Path path) {
    try {
      String json = Files.readString(path);
      return deserializeBoard(json);
    } catch (IOException e) {
      throw new RuntimeException("Error reading board from file: " + path, e);
    }
  }

  private Board deserializeBoard(String json) {
    Gson gson = new GsonBuilder()
        .registerTypeAdapter(TileAction.class, new TileActionAdapter())
        .create();

    Type boardType = new TypeToken<Board>() {}.getType();
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