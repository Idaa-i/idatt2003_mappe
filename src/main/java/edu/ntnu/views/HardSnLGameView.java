package edu.ntnu.views;

import edu.ntnu.controller.BoardGameController;
import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Die;
import edu.ntnu.model.Player;
import edu.ntnu.views.components.DiceImage;
import edu.ntnu.views.components.PlayerToken;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HardSnLGameView extends Application implements GameView {
  private BoardGame model;
  private BoardGameController controller;
  private DiceImage diceImage1;
  private DiceImage diceImage2;
  private Label winnerLabel;
  private Label actionLabel;
  private Pane boardPane;
  private Map<Player, PlayerToken> playerTokens;

  public HardSnLGameView(BoardGame model) {
    this.model = model;
    this.playerTokens = new HashMap<>();
  }

  @Override
  public void start(Stage primaryStage) {
    if (model == null) {
      throw new IllegalStateException("BoardGame model must be provided.");
    }
    this.controller = new BoardGameController(model, this);

    ImageView boardImageView = new ImageView(new Image(getClass().getResourceAsStream("/images/snakes-and-ladders-hard.png")));
    boardImageView.setFitWidth(600);
    boardImageView.setFitHeight(600);

    boardPane = new Pane();
    boardPane.getChildren().add(boardImageView);

    Die die1 = new Die();
    Die die2 = new Die();
    diceImage1 = new DiceImage(die1);
    diceImage2 = new DiceImage(die2);

    Button rollButton = new Button("ROLL!");
    rollButton.setStyle("-fx-background-color: #ff9999; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 18px; -fx-border-radius: 5px;");
    rollButton.setOnAction(event -> {
      die1.roll();
      die2.roll();
      diceImage1.updateDiceFace();
      diceImage2.updateDiceFace();
      int roll = die1.getValue() + die2.getValue();
      controller.playTurn(roll);
    });


    HBox diceBox = new HBox(20, diceImage1, diceImage2);
    diceBox.setAlignment(Pos.CENTER);

    winnerLabel = new Label("");
    winnerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: green;");
    winnerLabel.setAlignment(Pos.CENTER);

    actionLabel = new Label("");
    actionLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
    actionLabel.setAlignment(Pos.CENTER);

    VBox bottomBox = new VBox(20, diceBox, rollButton, actionLabel, winnerLabel);
    bottomBox.setAlignment(Pos.CENTER);

    VBox root = new VBox(20, boardPane, bottomBox);
    root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    Scene scene = new Scene(root, 650, 700);
    primaryStage.setTitle("Snakes and Ladders - Hard");
    primaryStage.setScene(scene);
    primaryStage.show();
    initializePlayerTokens();
  }
  private void initializePlayerTokens() {
    int offsetIndex = 0;
    for (Player player : model.getPlayers()) {
      PlayerToken token = new PlayerToken();
      String color = player.getColor();
      if (color != null && !color.isEmpty()) {
        try {
          token.setFill(javafx.scene.paint.Color.web(color.toLowerCase()));
        } catch (IllegalArgumentException e) {
          System.err.println("Ugyldig farge for " + player.getName() + ": " + color + ". Bruker standardfarge.");
          token.setFill(javafx.scene.paint.Color.GRAY);
        }
      } else {
        token.setFill(javafx.scene.paint.Color.GRAY);
      }
      playerTokens.put(player, token);
      boardPane.getChildren().add(token);

      updatePlayerPosition(player, offsetIndex * 5);
      offsetIndex++;
    }
  }

  @Override
  public void showActionMessage(String message) {
    actionLabel.setText(message);
  }

  @Override
  public void updatePlayerPosition(Player player) {
    updatePlayerPosition(player, 0);
  }

  private void updatePlayerPosition(Player player, double offset) {
    PlayerToken token = playerTokens.get(player);
    if (token != null) {
      int tilePosition = player.getCurrentTile().getPosition();
      double[] coordinates = getTileCoordinates(tilePosition);
      token.setCenterX(coordinates[0] + offset);
      token.setCenterY(coordinates[1]);
    }
  }

  @Override
  public void announceWinner(Player winner) {
    winnerLabel.setText(winner.getName() + " won the game!");
    VBox bottomBox = (VBox) winnerLabel.getParent();
    Button rollButton = (Button) bottomBox.getChildren().get(1);
    rollButton.setDisable(true);
  }

  private double[] getTileCoordinates(int tilePosition) {
    int rows = 9;
    int cols = 10;
    double tileWidth = 600.0 / cols;
    double tileHeight = 600.0 / rows;

    int row = (tilePosition - 1) / cols;
    int col = (tilePosition - 1) % cols;

    if (row % 2 == 1) {
      col = cols - 1 - col;
    }

    double x = col * tileWidth + tileWidth / 2;
    double y = (rows - 1 - row) * tileHeight + tileHeight / 2;

    return new double[]{x, y};
  }
}
