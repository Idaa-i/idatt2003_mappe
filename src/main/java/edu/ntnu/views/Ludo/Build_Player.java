package edu.ntnu.views.Ludo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Build_Player {

    Player[] players = new Player[4];
    int[][] initialX = {
            {1, 1, 3, 3},
            {10, 10, 12, 12},
            {10, 10, 12, 12},
            {1, 1, 3, 3}
    };
    int[][] initialY = {
            {1, 3, 1, 3},
            {1, 3, 1, 3},
            {10, 12, 10, 12},
            {10, 12, 10, 12}
    };

    public Build_Player(int height, int width) {
        // Initialize players
        for (int i = 0; i < 4; i++) {
            players[i] = new Player(height, width);  // Initialize each player with their pawns
        }
    }

    public void draw(GraphicsContext gc) {
        // Set up any drawing settings
        gc.setStroke(Color.BLACK);  // Set stroke color to black (if needed)
        gc.setLineWidth(2);         // Set line width (if needed)

        // Draw all players' pawns
        for (int i = 0; i < 4; i++) {
            players[i].draw(gc, initialX[i], initialY[i], i);  // Pass initialX, initialY, and playerIndex
        }
    }
}
