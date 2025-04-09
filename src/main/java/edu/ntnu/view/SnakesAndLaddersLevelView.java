package edu.ntnu.view;

import edu.ntnu.controller.LevelController;
import javafx.application.Application;
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

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.controller = new LevelController(this);

    Label selectLevel = new Label("Select level");
    selectLevel.setStyle("-fx-font-size: 20px;");

    ImageView hardImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders-hard.png").toExternalForm()));
    hardImage.setFitHeight(150);
    hardImage.setFitWidth(150);
    hardImage.setOnMouseClicked(event -> controller.openGame("Hard"));

    ImageView easyImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
    easyImage.setFitHeight(150);
    easyImage.setFitWidth(150);
    easyImage.setOnMouseClicked(event -> controller.openGame("Easy"));

    Label hardLabel = new Label("Hard");
    Label easyLabel = new Label("Easy");

    VBox hardBox = new VBox(10, hardLabel, hardImage);
    VBox easyBox = new VBox(10, easyLabel, easyImage);

    HBox gameSelection = new HBox(20, hardBox, easyBox);
    gameSelection.setStyle("-fx-alignment: center;");

    VBox root = new VBox(60, selectLevel, gameSelection);
    root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    Scene scene = new Scene(root, 600, 400);
    stage.setTitle("Level Selection");
    stage.setScene(scene);
    stage.show();
  }

  public Stage getStage() {
    return stage;
  }
}