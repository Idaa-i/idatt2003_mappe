package edu.ntnu.board;

public class SnakeAction implements TileAction {
    private int destination;
    public SnakeAction(int destination) {
        this.destination = destination;
    }
    public int execute(int position){
        System.out.println("Snake! Moving to " + destination);
        return destination;
    }
}
