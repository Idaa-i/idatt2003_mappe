package edu.ntnu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Oppretter en label og legger den i et StackPane
        Label label = new Label("Welcome to the Boardgame!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 300); // Korrekt bruk av root som rot-node

        // Setter opp vinduet
        stage.setScene(scene);
        stage.setTitle("BoardGame");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
