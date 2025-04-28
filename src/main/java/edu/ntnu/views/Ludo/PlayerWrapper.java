package edu.ntnu.views.Ludo;

import edu.ntnu.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerWrapper {
    private Player logicPlayer;
    public Pawn[] pawns = new Pawn[4];
    public int coin;
    private int height, width;

    public PlayerWrapper(Player logicPlayer, int height, int width) {
        this.logicPlayer = logicPlayer;
        this.height = height;
        this.width = width;
        this.coin = 0;

        for (int i = 0; i < 4; i++) {
            pawns[i] = new Pawn(height, width);
        }
    }

    public void draw(GraphicsContext gc, int[] initialX, int[] initialY, int playerIndex) {

        // Draw the 4 pawns
        for (int i = 0; i < 4; i++) {
            pawns[i].draw(gc, initialX[i], initialY[i], playerIndex);
        }
    }

    public String getName() {
        return logicPlayer.getName();
    }

    public String getColor() {
        return logicPlayer.getColor();
    }


    public Player getLogicPlayer() {
        return logicPlayer;
    }
}
