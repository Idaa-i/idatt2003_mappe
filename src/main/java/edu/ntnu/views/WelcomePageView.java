package edu.ntnu.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomePageView extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Welcome to our boardgame");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label chooseLabel = new Label("Choose a game");
        chooseLabel.setStyle("-fx-font-size: 18px;");



        Label ludoLabel = new Label("Ludo");
        Label snakesLabel = new Label("Snakes & Ladders");

        VBox ludoBox = new VBox(ludoLabel);
        VBox snakesBox = new VBox(snakesLabel);

        HBox gameSelection = new HBox(20, ludoBox, snakesBox);
        gameSelection.setStyle("-fx-alignment: center;");

        VBox root = new VBox(20, titleLabel, chooseLabel, gameSelection);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Boardgame Selection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
