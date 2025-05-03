package edu.ntnu.model.actions;

public class SnakeAction implements TileAction {
    private int destination;

    public SnakeAction(int destination) {
        this.destination = destination;
    }
    public int execute(int position){
        return destination;
    }
}