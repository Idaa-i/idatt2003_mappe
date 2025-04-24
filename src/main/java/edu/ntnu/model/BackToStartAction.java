package edu.ntnu.model;

public class BackToStartAction implements TileAction{
  private int destination;

  public BackToStartAction(int destination) {
    this.destination = destination;
  }

  public int execute(int position) {
    return destination;
  }
}