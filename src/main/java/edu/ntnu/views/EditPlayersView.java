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
    private ArrayList<Player> players = new ArrayList<>();
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
        saveButton.setOnAction(e -> savePlayersToFile());

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

    private HBox createPlayerRow(Player player) {
        TextField nameField = new TextField(player.getName());
        nameField.textProperty().addListener((obs, oldVal, newVal) -> player.setName(newVal));

        ComboBox<String> colorPicker = new ComboBox<>();
        colorPicker.getItems().addAll("Red", "Orange", "Yellow", "Green", "Cyan", "Magenta");
        colorPicker.setValue("Red");
        colorPicker.setOnAction(e -> player.setColor(colorPicker.getValue()));

        Button removeButton = new Button("-");
        removeButton.setOnAction(e -> removePlayer(player, nameField));

        return new HBox(10, nameField, colorPicker, removeButton);
    }

    private void removePlayer(Player player, TextField nameField) {
        players.remove(player);
        playersBox.getChildren().removeIf(node -> ((HBox) node).getChildren().contains(nameField));
    }

    private void savePlayersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("players.txt"))) {
            for (Player player : players) {
                writer.println(player.getName() + "," + player.getColor());
            }
            errorLabel.setText("Players saved successfully!");
        } catch (IOException e) {
            errorLabel.setText("Error saving players!");
        }
    }

    static class Player {
        private String name;
        private String color;

        public Player(String name) {
            this.name = name;
            this.color = "Red"; // Default color
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getColor() { return color; }
        public void setColor(String color) { this.color = color; }
    }
}
