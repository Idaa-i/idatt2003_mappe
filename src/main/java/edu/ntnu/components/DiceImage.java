package edu.ntnu.components;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * DiceImage-klassen representerer en visuell fremstilling av en terning.
 */
public class DiceImage extends Group {
  private static final double SIZE = 50;
  private static final double DOT_RADIUS = 5;

  /**
   * Konstruktør for DiceImage.
   *
   * @param width  bredden på terningbildet
   * @param height høyden på terningbildet
   */
  public DiceImage(double width, double height) {
    Rectangle background = new Rectangle(SIZE, SIZE);
    background.setFill(Color.WHITE);
    background.setStroke(Color.BLACK);
    background.setStrokeWidth(2);
    background.setArcWidth(15);
    background.setArcHeight(15);
    getChildren().add(background);
  }

  /**
   * Setter terningens verdi.
   *
   * @param value terningens verdi (må være mellom 1 og 6)
   * @throws IllegalArgumentException hvis verdien ikke er mellom 1 og 6
   */
  public void setDiceFace(int value) {
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
   * Legger til en prikk på terningens overflate ved de spesifiserte koordinatene.
   *
   * @param x x-koordinaten til prikken
   * @param y y-koordinaten til prikken
   */
  private void addDot(double x, double y) {
    Circle dot = new Circle(x, y, DOT_RADIUS);
    dot.setFill(Color.BLACK);
    getChildren().add(dot);
  }
}