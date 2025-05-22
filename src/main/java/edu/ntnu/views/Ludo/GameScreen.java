package edu.ntnu.views.Ludo;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.Tile;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * GameScreen initializes and displays the GUI for a Ludo game using JavaFX.
 * It sets up the game canvas, user input handlers, and the animation loop that
 * continuously redraws the game state
 */
public class GameScreen extends Application {

  private GameMoves gameMoves;
  private static ArrayList<Player> logicPlayers;

  /**
   * Sets the list of logical players to be used in the game. This method must be.
   * called before launching the app unless using fallback defaults
   *
   * @param players The players to be used in the game session
   */
  public static void setPlayers(ArrayList<Player> players) {
    logicPlayers = players;
  }

  /**
   * The entry point for the JavaFX application. Sets up the main window, canvas,
   * input listeners, and a timer loop for rendering game state
   *
   * @param primaryStage The window for this JavaFX application
   */
  @Override
  public void start(Stage primaryStage) {
    // If no players provided, create 4 default ones (useful for debugging)
    if (logicPlayers == null || logicPlayers.isEmpty()) {
      logicPlayers = new ArrayList<>();
      String[] defaultColors = {"#E76264", "#719063", "#E79A61", "#9D61E6"};
      for (int i = 0; i < 4; i++) {
        logicPlayers.add(new Player("Player " + (i + 1), defaultColors[i], new Tile(0)));
      }
    }

    gameMoves = new GameMoves(logicPlayers);

    Canvas canvas = new Canvas(1000, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    canvas.setOnKeyPressed(e -> gameMoves.handleKeyPress(e));
    canvas.setOnMouseClicked(e -> gameMoves.handleMouseClick(e));

    StackPane root = new StackPane(canvas);
    Scene scene = new Scene(root, 1000, 600);

    primaryStage.setTitle("LUDO");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    canvas.requestFocus(); //Ensures canvas recieves keyboard input
    //Setup animation loop that continously redraws game state
    javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame(
        javafx.util.Duration.millis(10),
        e -> gameMoves.draw(gc)
    );

    javafx.animation.Timeline timeline = new javafx.animation.Timeline(keyFrame);
    timeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
    timeline.play();

    primaryStage.show();
  }
}
