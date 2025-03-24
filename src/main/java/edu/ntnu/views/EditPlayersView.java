package edu.ntnu.views;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EditPlayersView extends Application {
    private VBox playersBox;
    private ArrayList<Player> players = new ArrayList<>();
    private final int MAX_PLAYERS = 4;
    private final String[] colors = {"Red", "Orange", "Yellow", "Green", "Cyan", "Magenta"};
    private Label errorLabel;
    private HashMap<Player, HBox> playerRows = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #dbe8fd;"); // Pale blue background

        // Title with yellow background, no padding
        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-background-color: #ffffe0;"); // Yellow background for header
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Edit players:");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-border-width: 20px; -fx-border-color: #ffffe0;");
        titleBox.getChildren().add(titleLabel);

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(20)); // Existing padding
        contentBox.setStyle("-fx-background-color: #ffffe0; -fx-border-color: #dbe8fd; -fx-border-width: 20px;"); // Yellow with black border

        playersBox = new VBox(10);
        playersBox.setStyle("-fx-background-color: #ffffe0;"); // White background to create visual separation
        playersBox.setPrefHeight(450); // Fixed height to create more space
        playersBox.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(playersBox, Priority.ALWAYS);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addButton = new Button("Add +");
        addButton.setStyle(
                "-fx-background-color: #dbe8fd; " + // Gjør bakgrunnen gjennomsiktig
                        "-fx-text-fill: BLACK; " + // Grønn tekstfarge for Add +
                        "-fx-border-width: 2px; " + // Tynn ramme rundt knappen
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Fet tekst
                        "-fx-border-radius: 5px;"); // Rette kanter for knappen

        // Bind button width and height to scene size
        addButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));  // 15% of window width
        addButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05)); // 5% of window height

        addButton.setOnAction(e -> addPlayer());

        Button saveButton = new Button("Save players?");
        saveButton.setStyle(
                "-fx-background-color: #dbe8fd; " + // Grønn bakgrunn
                        "-fx-text-fill: BLACK; " + // Hvit tekst
                        "-fx-border-color: transparent; " + // Ingen ramme
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Fet tekst
                        "-fx-border-radius: 5px;"); // Rette kanter

        // Bind button width and height to scene size
        saveButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));  // 15% of window width
        saveButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05)); // 5% of window height

        saveButton.setOnAction(e -> {
            if (validatePlayers()) {
                savePlayersToFile();
            }
        });

        Button playButton = new Button("Play!");
        playButton.setStyle(
                "-fx-background-color: #dbe8fd; " + // Grønn bakgrunn
                        "-fx-text-fill: BLACK; " + // Hvit tekst
                        "-fx-border-color: transparent; " + // Ingen ramme
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Fet tekst
                        "-fx-border-radius: 5px;"); // Rette kanter

        // Bind button width and height to scene size
        playButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));  // 15% of window width
        playButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05)); // 5% of window height

        playButton.setOnAction(e -> {
            if (validatePlayers()) {
                goToLevelView(primaryStage);
            }
        });

        HBox buttonBox = new HBox(10, addButton, saveButton, playButton);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);

        contentBox.getChildren().addAll(playersBox, errorLabel, buttonBox);
        contentBox.setAlignment(Pos.BOTTOM_LEFT);

        VBox mainContent = new VBox(titleBox, contentBox);

        root.setCenter(mainContent);

        Scene scene = new Scene(root, 600, 650);
        primaryStage.setTitle("Edit Players");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add initial players to match the wireframe
        addPlayer();
        addPlayer();
        addPlayer();
    }

    private void addPlayer() {
        if (players.size() >= MAX_PLAYERS) return;

        Player newPlayer = new Player("Player" + (players.size() + 1));
        players.add(newPlayer);
        HBox playerRow = createPlayerRow(newPlayer);
        playersBox.getChildren().add(playerRow);
        playerRows.put(newPlayer, playerRow);
    }

    private HBox createPlayerRow(Player player) {
        TextField nameField = new TextField(player.getName());
        nameField.textProperty().addListener((obs, oldVal, newVal) -> player.setName(newVal));
        nameField.setPrefWidth(120); // Litt bredere for plass
        nameField.setStyle(
                "-fx-background-color: transparent; " + // Gjør bakgrunnen gjennomsiktig
                        "-fx-border-width: 0 0 2px 0; " + // Tynn linje kun nederst
                        "-fx-border-color: BLACK; " + // Lys grå for linjen
                        "-fx-font-size: 14px; " + // Mindre fontstørrelse for en mer elegant følelse
                        "-fx-font-family: 'Arial'; " + // Enkel og moderne font
                        "-fx-padding: 5px 0 0 0;"); // Lite mellomrom på toppen

        HBox colorBox = new HBox(5);
        colorBox.setPadding(new Insets(5));
        colorBox.setAlignment(Pos.CENTER_LEFT);
        colorBox.setStyle("-fx-background-color: #c6e2ff;"); // Litt blå bakgrunn for fargevalgene

        // Create a list to hold the color rectangles
        ArrayList<Rectangle> colorRects = new ArrayList<>();

        for (String color : colors) {
            Rectangle colorRect = new Rectangle(20, 20, Color.web(color.toLowerCase()));
            colorRect.setFill(Color.web(color.toLowerCase()));

            // Add the color rectangle to the list
            colorRects.add(colorRect);

            colorRect.setOpacity(player.getColor().equals(color) ? 0.5 : 1.0);  // Default opacity

            // Set the event to update color and appearance when selected
            colorRect.setOnMouseClicked(e -> {
                player.setColor(color);  // Update player's color
                updateColorSelection(player, colorRects);  // Update color selection visuals
            });

            colorBox.getChildren().add(colorRect);
        }

        Button removeButton = new Button("-");
        removeButton.setStyle(
                "-fx-background-color: transparent; " + // Gjør bakgrunnen gjennomsiktig
                        "-fx-text-fill: black; " + // Svart tekst
                        "-fx-border-color: black; " + // Svart ramme
                        "-fx-border-width: 1px; " + // Tynn ramme
                        "-fx-padding: 0px 6px; " +
                        "-fx-border-radius: 3px; " + // Rundede hjørner på rammen
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 15px; "); // Fet tekst for knappen

        removeButton.setMaxHeight(20); // Sett ønsket høyde
        removeButton.setMaxWidth(20);  // Sett ønsket bredde
        removeButton.setOnAction(e -> removePlayer(player));

        HBox row = new HBox(10, nameField, colorBox, removeButton);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(5));
        row.setStyle("-fx-background-color: #e6f2ff;"); // Lys blå bakgrunn for hele raden

        return row;
    }

    private void updateColorSelection(Player player, ArrayList<Rectangle> colorRects) {
        for (int i = 0; i < colorRects.size(); i++) {
            Rectangle colorRect = colorRects.get(i);
            if (player.getColor().equals(colors[i])) {
                colorRect.setOpacity(0.7);  // Make the selected color lighter
            } else {
                colorRect.setOpacity(1.0);  // Keep other colors at normal opacity
            }
        }
    }


    private void removePlayer(Player player) {
        players.remove(player);
        playersBox.getChildren().clear();
        playerRows.remove(player);
        for (Player p : players) {
            HBox row = createPlayerRow(p);
            playersBox.getChildren().add(row);
            playerRows.put(p, row);
        }
    }

    private boolean validatePlayers() {
        HashSet<String> names = new HashSet<>();
        HashSet<String> colors = new HashSet<>();

        for (Player player : players) {
            if (!names.add(player.getName())) {
                errorLabel.setText("Duplicate names are not allowed!");
                return false;
            }
            if (!colors.add(player.getColor())) {
                errorLabel.setText("Duplicate colors are not allowed!");
                return false;
            }
        }
        errorLabel.setText(""); // Clear error message if validation passes
        return true;
    }

    private void savePlayersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("players.txt"))) {
            for (Player player : players) {
                writer.println(player.getName() + ":" + player.getColor());
            }
        } catch (IOException e) {
            errorLabel.setText("Failed to save players!");
        }
    }

    private void goToLevelView(Stage primaryStage) {
        System.out.println("Going to level view...");
        // Navigate to another view if needed
    }

    // Player class to represent each player
    private class Player {
        private String name;
        private String color;

        public Player(String name) {
            this.name = name;
            this.color = "Red"; // Default color
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
