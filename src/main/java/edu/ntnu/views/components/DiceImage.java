package edu.ntnu.views.components;

import edu.ntnu.model.dice.Die;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Class representing a visual component for displaying a single die.
 */
public class DiceImage extends Group {
  private static final double SIZE = 50;
  private static final double DOT_RADIUS = 5;
  private Die die;

  /**
   * Constructor for DiceImage class.
   * Constructs a DiceImage for the given Die
   *
   * @param die the die whose value will be visually represented
   */
  public DiceImage(Die die) {
    this.die = die;
    Rectangle background = new Rectangle(SIZE, SIZE);
    background.setFill(Color.WHITE);
    background.setStroke(Color.BLACK);
    background.setStrokeWidth(2);
    background.setArcWidth(15);
    background.setArcHeight(15);
    getChildren().add(background);
    updateDiceFace();
  }

  /**
   * Method for updating the visual representation of the die face based on its current value.
   */
  public void updateDiceFace() {
    int value = die.getValue();
    if (value < 1 || value > 6) {
      throw new IllegalArgumentException("Terningverdien må være mellom 1 og 6");
    }
    getChildren().removeIf(node -> node instanceof Circle);

    switch (value) {
      case 1:
        addDot(SIZE / 2, SIZE / 2);
        break;
      case 2:
        addDot(SIZE / 4, SIZE / 4);
        addDot(3 * SIZE / 4, 3 * SIZE / 4);
        break;
      case 3:
        addDot(SIZE / 4, SIZE / 4);
        addDot(SIZE / 2, SIZE / 2);
        addDot(3 * SIZE / 4, 3 * SIZE / 4);
        break;
      case 4:
        addDot(SIZE / 4, SIZE / 4);
        addDot(SIZE / 4, 3 * SIZE / 4);
        addDot(3 * SIZE / 4, SIZE / 4);
        addDot(3 * SIZE / 4, 3 * SIZE / 4);
        break;
      case 5:
        addDot(SIZE / 4, SIZE / 4);
        addDot(SIZE / 4, 3 * SIZE / 4);
        addDot(SIZE / 2, SIZE / 2);
        addDot(3 * SIZE / 4, SIZE / 4);
        addDot(3 * SIZE / 4, 3 * SIZE / 4);
        break;
      case 6:
        addDot(SIZE / 4, SIZE / 4);
        addDot(SIZE / 4, SIZE / 2);
        addDot(SIZE / 4, 3 * SIZE / 4);
        addDot(3 * SIZE / 4, SIZE / 4);
        addDot(3 * SIZE / 4, SIZE / 2);
        addDot(3 * SIZE / 4, 3 * SIZE / 4);
        break;
    }
  }

  /**
   * Adds a dot (circle) to the die face at the specified coordinates.
   *
   * @param x the x-coordinate of the dot
   * @param y the y-coordinate of the dot
   */
  private void addDot(double x, double y) {
    Circle dot = new Circle(x, y, DOT_RADIUS);
    dot.setFill(Color.BLACK);
    getChildren().add(dot);
  }
}