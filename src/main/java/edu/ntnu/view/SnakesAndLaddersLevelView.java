package edu.ntnu.view;

import edu.ntnu.controller.LevelController;
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

public class SnakesAndLaddersLevelView extends Application {
  private LevelController controller;
  private Stage stage;
  private static final double INITIAL_WIDTH = 600;
  private static final double INITIAL_HEIGHT = 400;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.controller = new LevelController(this);

    VBox root = new VBox(20);
    root.setAlignment(Pos.CENTER);
    root.setPadding(new Insets(20));

    Label selectLevel = new Label("Select level");
    selectLevel.styleProperty().bind(root.widthProperty().multiply(0.04).asString("-fx-font-size: %fpx"));

    ImageView hardImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders-hard.png").toExternalForm()));
    hardImage.setPreserveRatio(true);
    hardImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    hardImage.setOnMouseClicked(event -> controller.openGame("Hard"));

    ImageView easyImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
    easyImage.setPreserveRatio(true);
    easyImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
    easyImage.setOnMouseClicked(event -> controller.openGame("Easy"));

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

  public Stage getStage() {
    return stage;
  }
}