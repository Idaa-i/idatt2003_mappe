package edu.ntnu.view;

import edu.ntnu.controller.BoardGameController;
import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Die;
import edu.ntnu.model.Player;
import edu.ntnu.view.components.DiceImage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HardSnLGameView extends Application implements GameView {
  private BoardGame model;
  private BoardGameController controller;
  private DiceImage diceImage1;
  private DiceImage diceImage2;
  private Label winnerLabel;

  public HardSnLGameView(BoardGame model) {
    this.model = model;
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
      controller.playTurn();
    });

    HBox diceBox = new HBox(20, diceImage1, diceImage2);
    diceBox.setAlignment(Pos.CENTER);

    winnerLabel = new Label("");
    winnerLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #ff0000;");
    winnerLabel.setAlignment(Pos.CENTER);

    VBox bottomBox = new VBox(20, diceBox, rollButton, winnerLabel);
    bottomBox.setAlignment(Pos.CENTER);

    VBox root = new VBox(20, boardImageView, bottomBox);
    root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    Scene scene = new Scene(root, 650, 700);
    primaryStage.setTitle("Snakes and Ladders - Hard");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void updatePlayerPosition(Player player) {
    System.out.println(player.getName() + " moved to tile " + player.getCurrentTile().getPosition());
  }

  @Override
  public void announceWinner(Player winner) {
    winnerLabel.setText(winner.getName() + " has won the game!");
    VBox bottomBox = (VBox) winnerLabel.getParent();
    Button rollButton = (Button) bottomBox.getChildren().get(1);
    rollButton.setDisable(true);
  }
}