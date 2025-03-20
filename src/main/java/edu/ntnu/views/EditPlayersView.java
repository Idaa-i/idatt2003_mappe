package edu.ntnu.views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class EditPlayersView extends Application {
    private VBox playersBox;
    private final int MAX_PLAYERS = 4;
    private Label errorLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        playersBox = new VBox(10);
        errorLabel = new Label();

        Button addButton = new Button("Add +");
        addButton.setOnAction(e -> addPlayer());

        Button saveButton = new Button("Save players?");

        Button playButton = new Button("Play!");

        HBox buttonBox = new HBox(10, addButton, saveButton, playButton);
        VBox layout = new VBox(10, new Label("Edit Players:"), playersBox, errorLabel, buttonBox);
        root.setCenter(layout);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Edit Players");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addPlayer() {
        if (players.size() >= MAX_PLAYERS) {
            errorLabel.setText("Max 4 players allowed!");
            return;
        }
        Player newPlayer = new Player("Player" + (players.size() + 1));
        players.add(newPlayer);
        playersBox.getChildren().add(createPlayerRow(newPlayer));
    }

}
