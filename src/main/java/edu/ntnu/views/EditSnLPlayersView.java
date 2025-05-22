package edu.ntnu.views;

import edu.ntnu.controller.PlayerController;
import edu.ntnu.model.Player;
import edu.ntnu.utils.CSVUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class EditSnLPlayersView extends Application {
    private VBox playersBox;
    private PlayerController controller;
    private final int MAX_PLAYERS = 4;
    private final String[] colors = {"Red", "Orange", "Yellow", "Green", "Cyan", "Magenta"};
    private Label errorLabel;
    private HashMap<Player, HBox> playerRows = new HashMap<>();
    private static final double INITIAL_WIDTH = 600;
    private static final double INITIAL_HEIGHT = 400;
    private String selectedGame;
    private List<String[]> csvPlayers;

    @Override
    public void start(Stage primaryStage) {
        start(primaryStage, null);
    }

    public void start(Stage primaryStage, String game) {
        this.selectedGame = game;
        this.controller = new PlayerController(this);
        this.csvPlayers = new ArrayList<>();
        loadPlayersFromCSV();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #dbe8fd;");

        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-background-color: #ffffe0;");
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.setPadding(new Insets(10));

        Label titleLabel = new Label("Edit players:");
        titleLabel.styleProperty().bind(root.widthProperty().multiply(0.05).asString("-fx-font-size: %fpx; -fx-font-weight: bold"));
        titleBox.getChildren().add(titleLabel);

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(20));
        contentBox.setStyle("-fx-background-color: #ffffe0;");

        playersBox = new VBox(20);
        playersBox.setAlignment(Pos.TOP_LEFT);
        VBox.setVgrow(playersBox, Priority.ALWAYS);

        errorLabel = new Label();
        errorLabel.styleProperty().bind(root.widthProperty().multiply(0.03).asString("-fx-font-size: %fpx; -fx-text-fill: red"));

        Button addButton = new Button("Add +");
        addButton.styleProperty().bind(root.widthProperty().multiply(0.03).asString("-fx-background-color: #dbe8fd; -fx-text-fill: BLACK; -fx-font-weight: bold; -fx-font-size: %fpx; -fx-border-radius: 5px"));
        addButton.setOnAction(e -> {
            if (controller.getPlayers().size() < MAX_PLAYERS) {
                controller.addPlayer("Player" + (controller.getPlayers().size() + 1), colors[controller.getPlayers().size() % colors.length], null);
            } else {
                errorLabel.setText("Maximum of " + MAX_PLAYERS + " players reached!");
            }
        });

        Button saveButton = new Button("Save players?");
        saveButton.styleProperty().bind(root.widthProperty().multiply(0.03).asString("-fx-background-color: #dbe8fd; -fx-text-fill: BLACK; -fx-font-weight: bold; -fx-font-size: %fpx; -fx-border-radius: 5px"));
        saveButton.setOnAction(e -> {
            if (validatePlayers()) {
                CSVUtils.writePlayersToCSV("players.csv", controller.getPlayers());
                errorLabel.setText("Players saved successfully!");
            }
        });

        Button playButton = new Button("Play!");
        playButton.styleProperty().bind(root.widthProperty().multiply(0.03).asString("-fx-background-color: #dbe8fd; -fx-text-fill: BLACK; -fx-font-weight: bold; -fx-font-size: %fpx; -fx-border-radius: 5px"));
        playButton.setOnAction(e -> {
            if (validatePlayers()) {
                goToLevelView(primaryStage);
            }
        });

        HBox buttonBox = new HBox(10, addButton, saveButton, playButton);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);

        contentBox.getChildren().addAll(playersBox, errorLabel, buttonBox);
        root.setCenter(new VBox(titleBox, contentBox));

        Scene scene = new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT);
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        primaryStage.setTitle("Edit Players");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(300);
        primaryStage.show();

        controller.addPlayer("Player1", colors[0], null);
        controller.addPlayer("Player2", colors[1], null);
    }

    private void loadPlayersFromCSV() {
        String csvFile = "players.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = parseCSVLine(line);
                if (data.length >= 1 && !data[0].trim().isEmpty()) {
                    csvPlayers.add(data);
                }
            }
        } catch (IOException e) {
            errorLabel.setText("Error reading players.csv: " + e.getMessage());
        }
    }

    private String[] parseCSVLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        result.add(field.toString());
        return result.toArray(new String[0]);
    }

    public void updatePlayerList(List<Player> players) {
        playersBox.getChildren().clear();
        playerRows.clear();
        for (Player player : players) {
            HBox row = createPlayerRow(player);
            playersBox.getChildren().add(row);
            playerRows.put(player, row);
        }
    }

    private HBox createPlayerRow(Player player) {
        TextField nameField = new TextField(player.getName());
        nameField.textProperty().addListener((obs, oldVal, newVal) -> controller.updatePlayerName(player, newVal));
        nameField.prefWidthProperty().bind(playersBox.widthProperty().multiply(0.25));
        nameField.styleProperty().bind(playersBox.widthProperty().multiply(0.03).asString("-fx-background-color: transparent; -fx-font-size: %fpx; -fx-font-weight: bold"));

        ChoiceBox<String> playerDropdown = new ChoiceBox<>();
        playerDropdown.getItems().add("Custom");
        for (String[] csvPlayer : csvPlayers) {
            playerDropdown.getItems().add(csvPlayer[0].trim());
        }
        playerDropdown.setValue("Custom");
        playerDropdown.setPrefWidth(100);

        HBox colorBox = new HBox(15);
        colorBox.setPadding(new Insets(7));
        ArrayList<Rectangle> colorRects = new ArrayList<>();
        for (String color : colors) {
            Rectangle colorRect = new Rectangle();
            colorRect.widthProperty().bind(playersBox.widthProperty().multiply(0.05));
            colorRect.heightProperty().bind(playersBox.widthProperty().multiply(0.05));
            colorRect.setFill(Color.web(color.toLowerCase()));
            colorRects.add(colorRect);
            colorRect.setOpacity(player.getColor() != null && player.getColor().equals(color) ? 0.5 : 1.0);
            colorRect.setOnMouseClicked(e -> {
                controller.updatePlayerColor(player, color);
                updateColorSelection(player, colorRects);
                playerDropdown.setValue("Custom");
            });
            colorBox.getChildren().add(colorRect);
        }

        playerDropdown.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals("Custom")) {
                for (String[] csvPlayer : csvPlayers) {
                    if (csvPlayer[0].equals(newVal)) {
                        controller.updatePlayerName(player, csvPlayer[0]);
                        String color = csvPlayer.length > 1 ? csvPlayer[1] : colors[0];
                        controller.updatePlayerColor(player, color);
                        updateColorSelection(player, colorRects);
                        break;
                    }
                }
            }
        });

        Button removeButton = new Button("-");
        removeButton.styleProperty().bind(playersBox.widthProperty().multiply(0.03).asString("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px; -fx-font-weight: bold; -fx-font-size: %fpx"));
        removeButton.setOnAction(e -> controller.removePlayer(player));

        HBox row = new HBox(15, nameField, playerDropdown, colorBox, removeButton);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: #e6f2ff;");
        return row;
    }

    private void updateColorSelection(Player player, ArrayList<Rectangle> colorRects) {
        for (int i = 0; i < colorRects.size(); i++) {
            colorRects.get(i).setOpacity(player.getColor().equals(colors[i]) ? 0.7 : 1.0);
        }
    }

    private boolean validatePlayers() {
        HashSet<String> names = new HashSet<>();
        HashSet<String> colors = new HashSet<>();

        List<Player> players = controller.getPlayers();

        if (players.isEmpty()) {
            errorLabel.setText("Zero players is not allowed!");
            return false;
        }

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

        errorLabel.setText(""); // No errors
        return true;
    }


    private void goToLevelView(Stage primaryStage) {
        SnakesAndLaddersLevelView levelView = new SnakesAndLaddersLevelView(controller.getPlayers());
        levelView.start(primaryStage);
    }
}