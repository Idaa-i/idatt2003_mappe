package edu.ntnu.views.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerToken extends Circle {
  public PlayerToken() {
    setRadius(10);
    setStroke(Color.BLACK);
    setStrokeWidth(2);
  }
}