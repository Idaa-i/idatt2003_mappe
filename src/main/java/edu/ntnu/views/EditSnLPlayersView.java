package edu.ntnu.views;

import edu.ntnu.controller.PlayerController;
import edu.ntnu.model.Player;
import edu.ntnu.utils.CSVUtils;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

    @Override
    public void start(Stage primaryStage) {
        start(primaryStage, null);
    }

    public void start(Stage primaryStage, String game) {
        this.selectedGame = game;
        this.controller = new PlayerController(this);

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
        addButton.setOnAction(e -> addPlayer());

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

        addPlayer();
        addPlayer();
        addPlayer();
    }

    private void addPlayer() {
        if (controller.getPlayers().size() >= MAX_PLAYERS) {
            errorLabel.setText("Maximum of " + MAX_PLAYERS + " players allowed!");
            return;
        }
        controller.addPlayer("Player" + (controller.getPlayers().size() + 1), colors[controller.getPlayers().size()], null);
        errorLabel.setText("");
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
        nameField.textProperty().addListener((obs, oldVal, newVal) -> player.setName(newVal));
        nameField.prefWidthProperty().bind(playersBox.widthProperty().multiply(0.25));
        nameField.styleProperty().bind(playersBox.widthProperty().multiply(0.03).asString("-fx-background-color: transparent; -fx-font-size: %fpx; -fx-font-weight: bold"));

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
                player.setColor(color);
                updateColorSelection(player, colorRects);
            });
            colorBox.getChildren().add(colorRect);
        }

        Button removeButton = new Button("-");
        removeButton.styleProperty().bind(playersBox.widthProperty().multiply(0.03).asString("-fx-background-color: transparent; -fx-text-fill: black; -fx-border-color: black; -fx-border-width: 1px; -fx-font-weight: bold; -fx-font-size: %fpx"));
        removeButton.setOnAction(e -> controller.removePlayer(player));

        HBox row = new HBox(15, nameField, colorBox, removeButton);
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
        for (Player player : controller.getPlayers()) {
            if (!names.add(player.getName())) {
                errorLabel.setText("Duplicate names are not allowed!");
                return false;
            }
            if (!colors.add(player.getColor())) {
                errorLabel.setText("Duplicate colors are not allowed!");
                return false;
            }
        }
        errorLabel.setText("");
        return true;
    }

    private void goToLevelView(Stage primaryStage) {
        SnakesAndLaddersLevelView levelView = new SnakesAndLaddersLevelView(controller.getPlayers());
        levelView.start(primaryStage);
    }
}