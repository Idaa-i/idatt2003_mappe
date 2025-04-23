package edu.ntnu.model;

public class SkipOneRoundAction implements TileAction{
  private int position;

  public SkipOneRoundAction() {
  }

  public int execute(int position) {
    return position;
  }
}

