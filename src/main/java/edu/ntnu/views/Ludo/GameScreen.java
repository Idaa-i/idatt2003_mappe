package edu.ntnu.views.Ludo;

import edu.ntnu.model.Tile;
import edu.ntnu.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameScreen extends Application {

    private GameMoves gameMoves;
    private static ArrayList<Player> logicPlayers;

    public static void setPlayers(ArrayList<Player> players) {
        logicPlayers = players;
    }

    @Override
    public void start(Stage primaryStage) {
        // Optional fallback in case GameScreen is run directly (for debugging)
        if (logicPlayers == null || logicPlayers.isEmpty()) {
            logicPlayers = new ArrayList<>();
            String[] defaultColors = {"#E76264", "#719063", "#E79A61", "#9D61E6"};
            for (int i = 0; i < 4; i++) {
                logicPlayers.add(new Player("Player " + (i + 1), defaultColors[i], new Tile(0)));
            }
        }

        gameMoves = new GameMoves(logicPlayers);

        Canvas canvas = new Canvas(1000, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnKeyPressed(e -> gameMoves.handleKeyPress(e));
        canvas.setOnMouseClicked(e -> gameMoves.handleMouseClick(e));

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setTitle("LUDO");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        canvas.requestFocus();

        javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame(
                javafx.util.Duration.millis(10),
                e -> gameMoves.draw(gc)
        );

        javafx.animation.Timeline timeline = new javafx.animation.Timeline(keyFrame);
        timeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        timeline.play();

        primaryStage.show();
    }
}
