package edu.ntnu.model;

public class SkipOneRoundAction implements TileAction{
  private int destination;

  public SkipOneRoundAction(int destination) {
    this.destination = destination;
  }

  public int execute(int position) {
    return destination;
  }
}

