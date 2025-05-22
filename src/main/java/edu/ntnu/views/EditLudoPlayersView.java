package edu.ntnu.views;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.Tile;
import edu.ntnu.views.Ludo.GameScreen;
import javafx.application.Application;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Class representing a JavaFX application for editing players in a game of Ludo.
 * Allows players to add, remove and configure players
 */
public class EditLudoPlayersView extends Application {
  private VBox playersBox;
  private ArrayList<Player> players = new ArrayList<>();
  private final int MAX_PLAYERS = 4;
  private final String[] colors = {"#719063", "#9D61E6", "#E79A61", "#E76264"};
  private Label errorLabel;
  private HashMap<Player, HBox> playerRows = new HashMap<>();

  /**
   * Main entry point for the JavaFX application.
   *
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Method for initializing and displaying the JavaFX stage for editing players.
   *
   * @param primaryStage the primary stage for this application
   */
  @Override
  public void start(Stage primaryStage) {
    BorderPane root = new BorderPane();
    root.setStyle("-fx-background-color: #dbe8fd;");

    HBox titleBox = new HBox();
    titleBox.setStyle("-fx-background-color: #ffffe0;");
    titleBox.setAlignment(Pos.CENTER_LEFT);

    Label titleLabel = new Label("Edit players:");
    titleLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-padding: 20px;");
    titleBox.getChildren().add(titleLabel);

    VBox contentBox = new VBox(20);
    contentBox.setPadding(new Insets(20));
    contentBox.setStyle(
        "-fx-background-color: #ffffe0; -fx-border-color: #dbe8fd; -fx-border-width: 40px;");

    playersBox = new VBox(40);
    playersBox.setStyle("-fx-background-color: #ffffe0;");
    playersBox.setPrefHeight(450);
    playersBox.setAlignment(Pos.TOP_LEFT);
    VBox.setVgrow(playersBox, Priority.ALWAYS);

    errorLabel = new Label();
    errorLabel.setStyle("-fx-text-fill: red;");

    Button addButton = new Button("Add +");
    addButton.setStyle(
        "-fx-background-color: #dbe8fd; "
            + "-fx-text-fill: BLACK; "
            + "-fx-border-width: 2px; "
            + "-fx-padding: 5px 10px; "
            + "-fx-font-weight: bold; "
            + "-fx-font-size: 18px;"
            + "-fx-border-radius: 5px;");

    addButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));
    addButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05));

    addButton.setOnAction(e -> addPlayer());

    Button saveButton = new Button("Save players?");
    saveButton.setStyle(
        "-fx-background-color: #dbe8fd; "
            + "-fx-text-fill: BLACK; "
            + "-fx-border-color: transparent; "
            + "-fx-padding: 5px 10px; "
            + "-fx-font-weight: bold; "
            + "-fx-font-size: 18px;"
            + "-fx-border-radius: 5px;");

    saveButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));
    saveButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05));

    saveButton.setOnAction(e -> {
      if (validatePlayers()) {
        savePlayersToFile();
      }
    });

    Button playButton = new Button("Play!");
    playButton.setStyle(
        "-fx-background-color: #dbe8fd; "
            + "-fx-text-fill: BLACK; "
            + "-fx-border-color: transparent; "
            + "-fx-padding: 5px 10px; "
            + "-fx-font-weight: bold; "
            + "-fx-font-size: 18px;"
            + "-fx-border-radius: 5px;");

    playButton.minWidthProperty().bind(primaryStage.widthProperty().multiply(0.15));
    playButton.minHeightProperty().bind(primaryStage.heightProperty().multiply(0.05));

    playButton.setOnAction(e -> {
      if (players.isEmpty()) {
        errorLabel.setText("Zero players is not allowed.");
        return;
      }

      HashSet<String> usedNames = new HashSet<>();
      HashSet<String> usedColors = new HashSet<>();

      for (Player player : players) {
        String name = player.getName().trim().toLowerCase();
        String color = player.getColor().trim().toLowerCase();

        if (!usedNames.add(name)) {
          errorLabel.setText("Duplicate names are not allowed.");
          return;
        }

        if (!usedColors.add(color)) {
          errorLabel.setText("Duplicate colors are not allowed.");
          return;
        }
      }

      errorLabel.setText(""); // Empty errormessage if everything is OK

      try {
        GameScreen.setPlayers(players);
        new GameScreen().start(primaryStage);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    });
    HBox buttonBox = new HBox(10, addButton, saveButton, playButton);
    buttonBox.setAlignment(Pos.BOTTOM_LEFT);

    contentBox.getChildren().addAll(playersBox, errorLabel, buttonBox);
    contentBox.setAlignment(Pos.BOTTOM_LEFT);

    VBox mainContent = new VBox(titleBox, contentBox);

    root.setCenter(mainContent);

    Scene scene = new Scene(root, 600, 550);
    primaryStage.setTitle("Ludo - Edit Players");
    primaryStage.setScene(scene);
    primaryStage.setMaximized(true);

    primaryStage.show();

    addPlayer();
    addPlayer();
    addPlayer();
  }

  /**
   * Method for adding a new player to the game.
   */
  private void addPlayer() {
    if (players.size() >= MAX_PLAYERS) {
      errorLabel.setText("Maximum of " + MAX_PLAYERS + " players allowed!");
      return;
    }

    Player newPlayer =
        new Player("Player" + (players.size() + 1), colors[players.size()], new Tile(0));
    players.add(newPlayer);
    HBox playerRow = createPlayerRow(newPlayer);
    playersBox.getChildren().add(playerRow);
    playerRows.put(newPlayer, playerRow);

    errorLabel.setText("");
  }

  /**
   * Method for creating a UI row for player.
   * Including name input, color selection and remove button
   *
   * @param player the player to create a row for
   * @return an HBox containing the player's UI elements
   */
  private HBox createPlayerRow(Player player) {
    TextField nameField = new TextField(player.getName());
    nameField.textProperty().addListener((obs, oldVal, newVal) -> player.setName(newVal));
    nameField.setPrefWidth(150);
    nameField.setPrefHeight(35);
    nameField.setStyle(
        "-fx-background-color: transparent; "
            + "-fx-font-size: 16px; "
            + "-fx-font-family: 'Arial'; "
            + "-fx-font-weight: bold; "
            + "-fx-padding: 5px;");

    HBox colorBox = new HBox(15);
    colorBox.setPadding(new Insets(7));
    colorBox.setAlignment(Pos.CENTER_LEFT);
    colorBox.setStyle("-fx-background-color: #c6e2ff;");

    ArrayList<Rectangle> colorRects = new ArrayList<>();

    for (String color : colors) {
      Rectangle colorRect = new Rectangle(30, 30, Color.web(color.toLowerCase()));
      colorRect.setFill(Color.web(color.toLowerCase()));

      colorRects.add(colorRect);

      colorRect.setOpacity(player.getColor().equals(color) ? 0.5 : 1.0);

      colorRect.setOnMouseClicked(e -> {
        player.setColor(color);
        updateColorSelection(player, colorRects);
      });

      colorBox.getChildren().add(colorRect);
    }

    Button removeButton = new Button("-");
    removeButton.setStyle(
        "-fx-background-color: transparent; "
            + "-fx-text-fill: black; "
            + "-fx-border-color: black; "
            + "-fx-border-width: 1px; "
            + "-fx-padding: 5px; "
            + "-fx-border-radius: 5px; "
            + "-fx-font-weight: bold;"
            + "-fx-font-size: 16px; ");

    removeButton.setPrefSize(35, 35);
    removeButton.setAlignment(Pos.CENTER);

    removeButton.setOnAction(e -> removePlayer(player));

    HBox nameColorBox = new HBox(15, nameField, colorBox);
    HBox.setHgrow(nameColorBox, Priority.ALWAYS);

    HBox row = new HBox(15, nameColorBox, removeButton);
    row.setAlignment(Pos.CENTER_LEFT);
    row.setPadding(new Insets(7));
    row.setStyle("-fx-background-color: #e6f2ff;");
    row.setPrefHeight(60);

    return row;
  }

  /**
   * Method for updating the color selection UI for a player, highlighting the selected color.
   *
   * @param player     the player whose color is being updated
   * @param colorRects list of color rectangles in the UI
   */
  private void updateColorSelection(Player player, ArrayList<Rectangle> colorRects) {
    for (int i = 0; i < colorRects.size(); i++) {
      Rectangle colorRect = colorRects.get(i);
      if (player.getColor().equals(colors[i])) {
        colorRect.setOpacity(0.7);
      } else {
        colorRect.setOpacity(1.0);
      }
    }
  }

  /**
   * Method for removing a player form the game and updated the UI accordingly.
   *
   * @param player the player to remove
   */
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
  /**
   * Method for validating that all players have unique named and colors.
   *
   * @return true if validation passes, otherwise false
   */
  private boolean validatePlayers() {
    if (players.isEmpty()) {
      errorLabel.setText("Zero players is not allowed!");
      return false;
    }

    HashSet<String> names = new HashSet<>();
    HashSet<String> colors = new HashSet<>();

    for (Player player : players) {
      if (!names.add(player.getName().trim().toLowerCase())) {
        errorLabel.setText("Duplicate names are not allowed!");
        return false;
      }
      if (!colors.add(player.getColor().trim().toLowerCase())) {
        errorLabel.setText("Duplicate colors are not allowed!");
        return false;
      }
    }

    errorLabel.setText("");
    return true;
  }

  /**
   * Method for saving the current list of players to a CSV file.
   */
  private void savePlayersToFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("ludo_players.csv"))) {
      writer.println("Name,Color");

      for (Player player : players) {
        writer.println(player.getName() + "," + player.getColor());
      }
      errorLabel.setText("Players saved successfully!");
    } catch (IOException e) {
      errorLabel.setText("Failed to save players!");
    }
  }
}