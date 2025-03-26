package edu.ntnu.views;

import edu.ntnu.components.DiceImage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * HardSnLGameView-klassen representerer ett SnL spill,level Hard.
 */
public class HardSnLGameView extends Application {

  /**
   * Metode for å sette opp scenen.
   *
   * @param primaryStage hovedvinduet for applikasjonen
   */
  @Override
  public void start(Stage primaryStage) {
    InputStream imageStream = getClass().getResourceAsStream("/images/snakes-and-ladders-hard.png");
    if (imageStream == null) {
      throw new IllegalStateException("Kan ikke finne snakes-and-ladders-hard.png på /images/snakes-and-ladders-hard.png. Sørg for at filen er i src/main/resources/images/");
    }
    ImageView boardImageView = new ImageView(new Image(imageStream));

    boardImageView.setFitWidth(600);
    boardImageView.setFitHeight(600);

    //Oppretter statiske terningbilder (kun for for det visuelle)
    DiceImage diceImage1 = new DiceImage(50, 50);
    DiceImage diceImage2 = new DiceImage(50, 50);
    diceImage1.setDiceFace(1);
    diceImage2.setDiceFace(1);

    Button rollButton = new Button("ROLL!");
    rollButton.setStyle(
        "-fx-background-color: #ff9999; " +
            "-fx-text-fill: black; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 18px; " +
            "-fx-border-radius: 5px; " +
            "-fx-padding: 10px 20px;"
    );

    HBox diceBox = new HBox(20, diceImage1, diceImage2); // Økt avstand mellom terningene
    diceBox.setAlignment(Pos.CENTER);
    VBox bottomBox = new VBox(20, diceBox, rollButton); // Økt avstand mellom terninger og knapp
    bottomBox.setAlignment(Pos.CENTER);
    bottomBox.setPadding(new Insets(20));

    VBox root = new VBox(20, boardImageView, bottomBox);
    root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

    Scene scene = new Scene(root, 650, 700);
    primaryStage.setTitle("Snakes and Ladders");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}