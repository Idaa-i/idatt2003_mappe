package edu.ntnu.views.Ludo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameScreen extends Application {

    private GameMoves gameMoves;

    @Override
    public void start(Stage primaryStage) {
        // Create a GameMoves instance (which will handle the game logic and rendering)
        gameMoves = new GameMoves();

        // Create a Canvas to draw on
        Canvas canvas = new Canvas(1000, 600);

        // Get the GraphicsContext to draw the game
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set up the key event handling (for rolling the dice and interacting with the game)
        canvas.setOnKeyPressed(e -> gameMoves.handleKeyPress(e));
        canvas.setOnMouseClicked(e -> gameMoves.handleMouseClick(e));

        // Use a StackPane as the root of the scene
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        // Set up the Scene
        Scene scene = new Scene(root, 1000, 600);

        // Set the scene on the stage
        primaryStage.setTitle("LUDO");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        // Focus the canvas so that it can capture key events
        canvas.requestFocus();

        // Main game loop to continuously redraw the canvas
        // We can use a Timeline for continuous redrawing
        javafx.animation.KeyFrame keyFrame = new javafx.animation.KeyFrame(
                javafx.util.Duration.millis(10), // Update every 10ms
                e -> {
                    gameMoves.draw(gc);  // Redraw the game state on the canvas
                });

        javafx.animation.Timeline timeline = new javafx.animation.Timeline(keyFrame);
        timeline.setCycleCount(javafx.animation.Timeline.INDEFINITE);
        timeline.play();

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
