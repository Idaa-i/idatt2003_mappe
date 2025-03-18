package edu.ntnu.board;

public class SkipOneRoundAction implements TileAction{
  private int destination;

  public SkipOneRoundAction(int destination) {
    this.destination = destination;
  }

  public int execute(int position) {
    System.out.println("Skip next round!");
    return destination;
  }
}

