package edu.ntnu.views;

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
 * Class representing a JavaFX application that serves as the main entry point for selecting a
 * boardgame.
 */
public class WelcomePageView extends Application {
  private static final double INITIAL_WIDTH = 600;
  private static final double INITIAL_HEIGHT = 400;

  /**
   * Methos for initializing and displaying the welcome screen with game selection options.
   *
   * @param primaryStage the primary stage for this application
   */
  @Override
  public void start(Stage primaryStage) {
    VBox root = new VBox(20);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    Label titleLabel = new Label("Welcome to our boardgame");
    titleLabel.styleProperty().bind(
        root.widthProperty().multiply(0.05).asString("-fx-font-size: %fpx; -fx-font-weight: bold"));

    Label chooseLabel = new Label("Choose a game");
    chooseLabel.styleProperty()
        .bind(root.widthProperty().multiply(0.035).asString("-fx-font-size: %fpx"));

    // Ludo image and click handler (add this in your VBox layout setup)
    Image ludoImage = new Image("file:src/main/resources/images/ludo.png");
    ImageView ludoImageView = new ImageView(ludoImage);
    ludoImageView.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    ludoImageView.setPreserveRatio(true);
    ludoImageView.setOnMouseClicked(event -> {
      try {
        new EditLudoPlayersView().start(primaryStage);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    root.getChildren().add(ludoImageView);


    ImageView snakesImage = new ImageView(
        new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
    snakesImage.setPreserveRatio(true);
    snakesImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    snakesImage.setOnMouseClicked(event -> openEditPlayers(primaryStage, "Snakes & Ladders"));

    Label ludoLabel = new Label("Ludo");
    Label snakesLabel = new Label("Snakes & Ladders");

    VBox ludoBox = new VBox(10, ludoLabel, ludoImageView);
    ludoBox.setAlignment(Pos.CENTER);
    VBox snakesBox = new VBox(10, snakesLabel, snakesImage);
    snakesBox.setAlignment(Pos.CENTER);

    HBox gameSelection = new HBox(20, ludoBox, snakesBox);
    gameSelection.setAlignment(Pos.CENTER);

    root.getChildren().addAll(titleLabel, chooseLabel, gameSelection);

    Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
    root.prefWidthProperty().bind(scene.widthProperty());
    root.prefHeightProperty().bind(scene.heightProperty());

    primaryStage.setTitle("Boardgame Selection");
    primaryStage.setScene(scene);
    primaryStage.setMinWidth(400);
    primaryStage.setMinHeight(300);
    primaryStage.show();
  }

  /**
   * Method for opening the player setup screen for the selected game.
   *
   * @param primaryStage the current stage
   * @param game         the name of the selected game
   */
  private void openEditPlayers(Stage primaryStage, String game) {
    EditSnLPlayersView editView = new EditSnLPlayersView();
    editView.start(primaryStage, game);
  }

  /**
   * Method for launching the JavaFX application.
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}