package edu.ntnu.views.Ludo;

import javafx.scene.canvas.GraphicsContext;

public class Player {
    int height, width, status, coin;
    Pawn[] pawns = new Pawn[4];

    public Player(int height, int width) {
        this.status = -1;
        this.coin = 0;
        for (int i = 0; i < 4; i++) {
            pawns[i] = new Pawn(height, width);
        }
    }

    public void draw(GraphicsContext gc, int[] initialX, int[] initialY, int playerIndex) {
        // Implement drawing functionality if needed for the player
        // Example: Draw each pawn
        for (int i = 0; i < 4; i++) {
            // Pass GraphicsContext, x, y, and playerIndex to the Pawn's draw method
            pawns[i].draw(gc, initialX[i], initialY[i], playerIndex);
        }
    }
}
