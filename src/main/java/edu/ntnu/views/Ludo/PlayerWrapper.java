package edu.ntnu.views.Ludo;
import edu.ntnu.model.Player;
import javafx.scene.canvas.GraphicsContext;

/**
 * The PlayerWrapper class acts as a bridge between the logical representation of a
 * Player and its visual components in the Ludo game
 */
public class PlayerWrapper {
    private Player logicPlayer; //The logical player object, holding the player's name and color
    public Pawn[] pawns = new Pawn[4];
    public int coin;
    private int height, width;

    /**
     * Constructs a PlayerWrapper for the specified logical player, initializing all pawns
     * @param logicPlayer The logical representation of the player
     * @param height Height of the boards tiles for rendering
     * @param width Width of the boards tiles for rendering
     */
    public PlayerWrapper(Player logicPlayer, int height, int width) {
        this.logicPlayer = logicPlayer;
        this.height = height;
        this.width = width;
        this.coin = 0;

        for (int i = 0; i < 4; i++) {
            pawns[i] = new Pawn(height, width);
        }
    }

    /**
     * Draws the players pawn on the board using the specified GraphicContext
     * @param gc The graphics context to draw with
     * @param initialX The initial X positions for each pawn
     * @param initialY The initial Y positions for each pawn
     * @param playerIndex The index of the player (used for color and path selection)
     */
    public void draw(GraphicsContext gc, int[] initialX, int[] initialY, int playerIndex) {

        // Draw the 4 pawns
        for (int i = 0; i < 4; i++) {
            pawns[i].draw(gc, initialX[i], initialY[i], playerIndex);
        }
    }

    /**
     * Returns the players name
     * @return The name of the player
     */
    public String getName() {
        return logicPlayer.getName();
    }

    /**
     * Returns the players assigned color as hex code string
     * @return
     */
    public String getColor() {
        return logicPlayer.getColor();
    }

    /**
     * Returns the underlying logical Player object
     * @return The logical player
     */
    public Player getLogicPlayer() {
        return logicPlayer;
    }
}
