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

public class WelcomePageView extends Application {
    private static final double INITIAL_WIDTH = 600;
    private static final double INITIAL_HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label titleLabel = new Label("Welcome to our boardgame");
        titleLabel.styleProperty().bind(root.widthProperty().multiply(0.05).asString("-fx-font-size: %fpx; -fx-font-weight: bold"));

        Label chooseLabel = new Label("Choose a game");
        chooseLabel.styleProperty().bind(root.widthProperty().multiply(0.035).asString("-fx-font-size: %fpx"));

        ImageView ludoImage = new ImageView(new Image(getClass().getResource("/images/ludo.png").toExternalForm()));
        ludoImage.setPreserveRatio(true);
        ludoImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
        ludoImage.setOnMouseClicked(event -> openEditPlayers(primaryStage, "Ludo"));

        ImageView snakesImage = new ImageView(new Image(getClass().getResource("/images/snakes-and-ladders.png").toExternalForm()));
        snakesImage.setPreserveRatio(true);
        snakesImage.fitWidthProperty().bind(root.widthProperty().multiply(0.25));
        snakesImage.setOnMouseClicked(event -> openEditPlayers(primaryStage, "Snakes & Ladders"));

        Label ludoLabel = new Label("Ludo");
        Label snakesLabel = new Label("Snakes & Ladders");

        VBox ludoBox = new VBox(10, ludoLabel, ludoImage);
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

    private void openEditPlayers(Stage primaryStage, String game) {
        EditSnLPlayersView editView = new EditSnLPlayersView();
        editView.start(primaryStage, game);
    }

    public static void main(String[] args) {
        launch(args);
    }
}