package edu.ntnu.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SnakesAndLaddersLevelView extends Application {
  public void start(Stage stage) {
    Label selectLevel = new Label("Select level");
    selectLevel.setStyle("-fx-font-size: 20px;");

    ImageView hardImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders-hard.png").toExternalForm()));
    hardImage.setFitHeight(150);
    hardImage.setFitWidth(150);
    hardImage.setOnMouseClicked(event -> openGame("Hard"));

    ImageView easyImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
    easyImage.setFitHeight(150);
    easyImage.setFitWidth(150);
    easyImage.setOnMouseClicked(event -> openGame("Easy"));

    Label hardLabel = new Label("Hard");
    hardLabel.setStyle("-fx-font-size: 16px;");
    Label easyLabel = new Label("Easy");
    easyLabel.setStyle("-fx-font-size: 16px;");

    VBox hardBox = new VBox(10, hardLabel, hardImage);
    hardBox.setStyle("-fx-alignment: center;");

    VBox easyBox = new VBox(10, easyLabel, easyImage);
    easyBox.setStyle("-fx-alignment: center;");

    HBox gameSelection = new HBox(20, hardBox, easyBox);
    gameSelection.setStyle("-fx-alignment: center;");

    VBox root = new VBox(60, selectLevel, gameSelection);
    root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    Scene scene = new Scene(root, 600, 400);

    String fontUrl = getClass().getResource("/fonts/AsapCondensed-Black.ttf").toExternalForm();
    scene.getStylesheets().add(fontUrl);

    stage.setTitle("Level Selection");
    stage.setScene(scene);
    stage.show();
  }

  private void openGame(String level) {
    System.out.println("Navigating to game");
    // Implement navigation path here
  }

  public static void main(String[] args) {
    launch(args);
  }
}