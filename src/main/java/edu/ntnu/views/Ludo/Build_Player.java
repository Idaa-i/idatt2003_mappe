package edu.ntnu.views.Ludo;

import edu.ntnu.model.Player;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Build_Player class is responsible for visually initializing and managing player pawns
 * on the Ludo game board. It creates and stores player representation (PlayerWrapper) and handles
 * their initial graphic placement based on color.
 * This class acts as a bridge between the Player model and the graphic game interface
 */

public class Build_Player {
  /**
   * Array of player wrappers representing up to four players.
   */

  public PlayerWrapper[] players = new PlayerWrapper[4];
  /**
   * Initial X positions for pawns of each color.
   */
  int[][] initialX = {
      {1, 1, 3, 3},     // Red
      {10, 10, 12, 12}, // Green
      {10, 10, 12, 12}, // Orange
      {1, 1, 3, 3}      // Purple
  };
  /**
   * Initial Y positions for pawns of each color.
   */
  int[][] initialY = {
      {1, 3, 1, 3},
      {1, 3, 1, 3},
      {10, 12, 10, 12},
      {10, 12, 10, 12}
  };

  /**
   * Map from player hex strings to corresponding player index in the array.
   */
  private final HashMap<String, Integer> colorToIndex = new HashMap<>() {{
    put("#E76264", 0); // Red
    put("#719063", 1); // Green
    put("#E79A61", 2); // Orange
    put("#9D61E6", 3); // Purple
  }};

  /**
   * Constructs a Build_Player object that initializes player wrappers from a list of Player
   * objects.
   *
   * @param logicPlayers List of logical players to be visualized
   * @param height       Height of the game board
   * @param width        Width of the game board
   */
  public Build_Player(ArrayList<Player> logicPlayers, int height, int width) {
    for (Player p : logicPlayers) {
      String color = p.getColor();
      Integer index = colorToIndex.get(color);

      if (index != null && players[index] == null) {
        players[index] = new PlayerWrapper(p, height, width);
      } else {
        System.err.println("Invalid or duplicate color: " + color);
      }
    }
  }

  /**
   * Draws all the player tokens onto the GraphicContext.
   *
   * @param gc The graphic context used to draw the players
   */
  public void draw(GraphicsContext gc) {
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);
    for (int i = 0; i < players.length; i++) {
      if (players[i] != null) {
        players[i].draw(gc, initialX[i], initialY[i], i);
      }
    }
  }

  /**
   * Retrieves the list of logical Player objects from the player wrappers.
   *
   * @return List of logical players represented in this view.
   */
  public ArrayList<Player> getLogicPlayers() {
    ArrayList<Player> result = new ArrayList<>();
    for (PlayerWrapper wrapper : players) {
      if (wrapper != null) {
        result.add(wrapper.getLogicPlayer());
      }
    }
    return result;
  }

}
