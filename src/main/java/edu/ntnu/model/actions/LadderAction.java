package edu.ntnu.model.actions;

public class LadderAction implements TileAction {
    private int destination;

    public LadderAction(int destination) {
        this.destination = destination;
    }

    public int execute(int position){
        return destination;
    }
}