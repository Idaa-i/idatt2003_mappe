package edu.ntnu.views.Ludo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Pawn represents the game pieces for a player in the Ludo game.
 * It handles its drawing on the game board, and tracks its position along the
 * predefined path
 */
public class Pawn {
  int x, y;       // Position of the pawn on the board
  int current;    // Index of the pawn's current position on the path
  int height, width; // Height and with of a tile

  /**
   * Constructs a Pawn instance with the specified tile dimensions.
   *
   * @param h Height of a single tile on the board
   * @param w Width of a singe tile on the board
   */
  public Pawn(int h, int w) {
    this.current = -1;  // -1 means the pawn is not on the board yet
    this.x = -1;
    this.y = -1;
    this.height = h;
    this.width = w;
  }

  /**
   * Draws the pawn on the game board at its current position.
   * If the pawn has not yet moved, it is drawn in its initial corner
   *
   * @param gc   The GraphicContext used to draw the pawn
   * @param i    The initial X ile index for drawing the pawn
   * @param j    The initial Y tile index for drawing the pawn
   * @param play Player index, 0=Red, 1=Green, 2=Orange, 3=Purple
   */
  public void draw(GraphicsContext gc, int i, int j, int play) {
    // Initial position if pawn hasn't moved yet (current == -1)
    if (current == -1) {
      int temp1 = 80 + (height / 2), temp2 = 50 + (width / 2);
      x = i;
      y = j;

      // Set color based on player index using the new color scheme
      switch (play) {
        case 0:
          gc.setFill(Color.web("E76264"));
          break;  // Red
        case 1:
          gc.setFill(Color.web("719063"));
          break;  // Green
        case 2:
          gc.setFill(Color.web("E79A61"));
          break;  // Orange
        case 3:
          gc.setFill(Color.web("9D61E6"));
          break;  // Purple
      }

      // Draw pawn as an oval
      gc.fillOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10);

      // Draw the outline of the pawn
      gc.setLineWidth(2);
      gc.setStroke(Color.BLACK);
      gc.strokeOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10);
    } else {
      // Draw pawn at the new position on the path
      int temp1 = 80, temp2 = 50;
      x = Path.ax[play][current];  // Get the x-coordinate of the pawn on the path
      y = Path.ay[play][current];  // Get the y-coordinate of the pawn on the path

      // Set color based on player index using the new color scheme
      switch (play) {
        case 0:
          gc.setFill(Color.web("E76264"));
          break;  // Salmon/Red
        case 1:
          gc.setFill(Color.web("719063"));
          break;  // Green
        case 2:
          gc.setFill(Color.web("E79A61"));
          break;  // Orange
        case 3:
          gc.setFill(Color.web("9D61E6"));
          break;  // Purple
      }

      // Draw pawn at the new position
      gc.fillOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10);

      // Draw the outline of the pawn
      gc.setLineWidth(2);
      gc.setStroke(Color.BLACK);
      gc.strokeOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10);
    }
  }
}
