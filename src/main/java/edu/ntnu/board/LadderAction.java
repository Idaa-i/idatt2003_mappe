package edu.ntnu.board;

public class LadderAction implements TileAction {
    private int destination;
    public LadderAction(int destination) {
        this.destination = destination;
    }
    public int execute(int position){
        System.out.println("Ladder! Moving to " + destination);
        return destination;
    }
}
