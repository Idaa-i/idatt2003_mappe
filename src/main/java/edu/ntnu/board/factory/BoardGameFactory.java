package edu.ntnu.board.factory;

import edu.ntnu.board.*;
import edu.ntnu.game.BoardGame;
import com.google.gson.*;
import edu.ntnu.board.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;



/**
 * BoardGameFactory for creating different board game variants.
 */
public class BoardGameFactory {
    private static final String SAVE_DIRECTORY = "saved_games";
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

    /**
     * Creates a custom board game based on user-defined parameters.
     * @param numTiles Number of tiles in the board.
     * @param tileActions Map of tile positions to their actions.
     * @param numPlayers Number of players in the game.
     * @return A new BoardGame instance.
     */
    public static BoardGame createCustomBoardGame(int numTiles, Map<Integer, TileAction> tileActions, int numPlayers) {
        Board board = new Board(numTiles);

        for (Map.Entry<Integer, TileAction> entry : tileActions.entrySet()) {
            int position = entry.getKey();
            TileAction action = entry.getValue();
            board.getTile(position).setAction(action);
        }

        return new BoardGame(board, numPlayers);
    }

    /**
     * Saves a board game to a JSON file.
     * @param game The board game to save.
     * @param fileName The name of the file (without extension).
     */
    public static void saveBoardGame(BoardGame game, String fileName) {
        File saveDir = new File(SAVE_DIRECTORY);
        if (!saveDir.exists()) {
            saveDir.mkdirs();  // Oppretter mappen hvis den ikke finnes
        }

        File saveFile = new File(saveDir, fileName + ".json");

        try (Writer writer = new FileWriter(saveFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(game, writer);
            System.out.println("Game saved to " + saveFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    /**
     * Loads all saved board games from the save directory.
     * @return List of BoardGame instances.
     */
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