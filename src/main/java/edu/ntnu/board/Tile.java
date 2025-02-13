package edu.ntnu.board;

public class Tile {
    private int position;
    private TileAction action;

    public Tile(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setAction(TileAction) {
        this.action = action;
    }

    public int executeAction(int currentPosition){
        if (action != null){
            return action.execute(currentPosition);
        }
        return currentPosition;
    }
}
