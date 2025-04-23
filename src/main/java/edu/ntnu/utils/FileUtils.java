package edu.ntnu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.model.BoardGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {
  private static final String SAVE_DIRECTORY = "saved_games";

  public static void saveBoardGame(BoardGame game, String fileName) {
    File saveDir = new File(SAVE_DIRECTORY);
    if (!saveDir.exists()) {
      saveDir.mkdirs();
    }
    File saveFile = new File(saveDir, fileName + ".json");
    try (Writer writer = new FileWriter(saveFile)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(game, writer);
    } catch (IOException e) {
      System.err.println("Error saving game: " + e.getMessage());
    }
  }

  public static List<BoardGame> loadAllSavedBoardGames() {
    List<BoardGame> boardGames = new ArrayList<>();
    File saveDir = new File(SAVE_DIRECTORY);
    if (!saveDir.exists() || !saveDir.isDirectory()) {
      return boardGames;
    }
    Gson gson = new Gson();
    for (File file : Objects.requireNonNull(saveDir.listFiles((dir, name) -> name.endsWith(".json")))) {
      try (Reader reader = new FileReader(file)) {
        BoardGame game = gson.fromJson(reader, BoardGame.class);
        boardGames.add(game);
      } catch (IOException e) {
        System.err.println("Error loading game from file: " + file.getName());
      }
    }
    return boardGames;
  }
}