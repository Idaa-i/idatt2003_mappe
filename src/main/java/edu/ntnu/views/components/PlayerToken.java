package edu.ntnu.views.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class representing a visual token for a player on the gameboard.
 * This token is displayed as a circle with a black border
 */
public class PlayerToken extends Circle {
  /**
   * Constructor for PlayerToken class.
   * Constructs a PlayerToken with default styling, fixed radius and a blacks stroke
   */
  public PlayerToken() {
    setRadius(10);
    setStroke(Color.BLACK);
    setStrokeWidth(2);
  }
}