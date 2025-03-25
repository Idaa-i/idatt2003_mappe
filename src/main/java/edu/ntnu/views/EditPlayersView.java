package edu.ntnu.views;

import javafx.application.Application;
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
        titleLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-border-width: 20px; -fx-border-color: #ffffe0;");
        titleBox.getChildren().add(titleLabel);

        VBox contentBox = new VBox(20); // Increased spacing between content elements
        contentBox.setPadding(new Insets(20));
        contentBox.setStyle("-fx-background-color: #ffffe0; -fx-border-color: #dbe8fd; -fx-border-width: 40px;");

        playersBox = new VBox(40); // Increased spacing between player rows
        playersBox.setStyle("-fx-background-color: #ffffe0;");
        playersBox.setPrefHeight(450); // Fixed height to create more space
        playersBox.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(playersBox, Priority.ALWAYS);

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        Button addButton = new Button("Add +");
        addButton.setStyle(
                "-fx-background-color: #dbe8fd; " + // Blue background
                        "-fx-text-fill: BLACK; " + // Black text
                        "-fx-border-width: 2px; " + // Border
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Bold text
                        "-fx-font-size: 18px;" +
                        "-fx-border-radius: 5px;"); // Rounded border

        // Bind button width and height to scene size
        addButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));  // 15% of window width
        addButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05)); // 5% of window height

        addButton.setOnAction(e -> addPlayer());

        Button saveButton = new Button("Save players?");
        saveButton.setStyle(
                "-fx-background-color: #dbe8fd; " + // Blue background
                        "-fx-text-fill: BLACK; " + // Black text
                        "-fx-border-color: transparent; " + // No border
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Bold text
                        "-fx-font-size: 18px;" +
                        "-fx-border-radius: 5px;"); // Rounded border

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
                "-fx-background-color: #dbe8fd; " + // Blue background
                        "-fx-text-fill: BLACK; " + // Black text
                        "-fx-border-color: transparent; " + // No border
                        "-fx-padding: 5px 10px; " + // Minimal padding
                        "-fx-font-weight: bold; " + // Bold text
                        "-fx-font-size: 18px;" +
                        "-fx-border-radius: 5px;"); // Rounded border

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

        Scene scene = new Scene(root, 600, 550);
        primaryStage.setTitle("Edit Players");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);

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
        nameField.setPrefWidth(150);
        nameField.setPrefHeight(35);
        nameField.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-font-size: 16px; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-font-weight: bold; " +
                        "-fx-padding: 5px;");

        HBox colorBox = new HBox(15); // Increased spacing between color rectangles
        colorBox.setPadding(new Insets(7));
        colorBox.setAlignment(Pos.CENTER_LEFT);
        colorBox.setStyle("-fx-background-color: #c6e2ff;");

        // Create a list to hold the color rectangles
        ArrayList<Rectangle> colorRects = new ArrayList<>();

        for (String color : colors) {
            Rectangle colorRect = new Rectangle(30, 30, Color.web(color.toLowerCase()));
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
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: black; " +
                        "-fx-border-color: black; " +
                        "-fx-border-width: 1px; " +
                        "-fx-padding: 5px; " +
                        "-fx-border-radius: 5px; " +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 16px; ");

        removeButton.setPrefSize(35, 35);
        removeButton.setAlignment(Pos.CENTER);

        removeButton.setOnAction(e -> removePlayer(player));

        // Use HBox with HGrow to push the remove button to the right
        HBox nameColorBox = new HBox(15, nameField, colorBox);
        HBox.setHgrow(nameColorBox, Priority.ALWAYS);

        HBox row = new HBox(15, nameColorBox, removeButton);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(7));
        row.setStyle("-fx-background-color: #e6f2ff;");
        row.setPrefHeight(60);

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
