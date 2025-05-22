package edu.ntnu.views;

import edu.ntnu.controller.LevelController;
import edu.ntnu.model.Player;
import edu.ntnu.model.board.BoardGame;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class representing a JavaFX application that presents a level selection screen for Snakes and
 * Ladders game.
 */
public class SnakesAndLaddersLevelView extends Application {
  private LevelController controller;
  private Stage stage;
  private List<Player> players;
  private static final double INITIAL_WIDTH = 600;
  private static final double INITIAL_HEIGHT = 400;

  /**
   * Constructor for SnakesAndLaddersLevelView class.
   * Constructs a new SnakesAndLaddersLevelView with the given list of players
   *
   * @param players the list of players participating in the game
   */
  public SnakesAndLaddersLevelView(List<Player> players) {
    this.players = players;
  }

  /**
   * Methos for initializing and displaying the level selection screen.
   *
   * @param stage the primary stage for this application
   */
  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.controller = new LevelController(this);

    VBox root = new VBox(20);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    Label selectLevel = new Label("Select level");
    selectLevel.styleProperty()
        .bind(root.widthProperty().multiply(0.04).asString("-fx-font-size: %fpx"));

    ImageView hardImage = new ImageView(
        new Image(getClass().getResource("/images/snakes-and-ladders-hard.png").toExternalForm()));
    hardImage.setPreserveRatio(true);
    hardImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    hardImage.setOnMouseClicked(event -> {
      BoardGame game = controller.createGame("Hard", players);
      HardSnLGameView gameView = new HardSnLGameView(game);
      gameView.start(stage);
    });

    ImageView easyImage = new ImageView(
        new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
    easyImage.setPreserveRatio(true);
    easyImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    easyImage.setOnMouseClicked(event -> {
      BoardGame game = controller.createGame("Easy", players);
      EasySnLGameView gameView = new EasySnLGameView(game);
      gameView.start(stage);
    });

    Label hardLabel = new Label("Hard");
    Label easyLabel = new Label("Easy");

    VBox hardBox = new VBox(10, hardLabel, hardImage);
    hardBox.setAlignment(Pos.CENTER);
    VBox easyBox = new VBox(10, easyLabel, easyImage);
    easyBox.setAlignment(Pos.CENTER);

    HBox gameSelection = new HBox(20, hardBox, easyBox);
    gameSelection.setAlignment(Pos.CENTER);

    root.getChildren().addAll(selectLevel, gameSelection);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    root.prefWidthProperty().bind(scene.widthProperty());
    root.prefHeightProperty().bind(scene.heightProperty());

    stage.setTitle("Level Selection");
    stage.setScene(scene);
    stage.setMinWidth(400);
    stage.setMinHeight(300);
    stage.show();
  }

  /**
   * Method for returning the primary stage used by this view.
   *
   * @return the JavaFX stage instance
   */
  public Stage getStage() {
    return stage;
  }
}