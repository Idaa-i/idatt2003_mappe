package edu.ntnu.board;

public class BackToStartAction implements TileAction{
  private int destination;

  public BackToStartAction(int destination) {
    this.destination = destination;
  }

  public int execute(int position) {
    System.out.println("Moving back to start!");
    return destination;
  }
}
