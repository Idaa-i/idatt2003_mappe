package edu.ntnu.boardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("This is our boardgame!");
        Scene scene = new Scene(label, 300, 200);
        stage.setScene(scene);
        stage.setTitle("BoardGame");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
