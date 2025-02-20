package edu.ntnu.game;

import edu.ntnu.board.Tile;

public class Player {
    private String name;
    private Tile currentTile;

    public Player(String name, Tile startTile){
        this.name = name;
        this.currentTile = startTile;
    }

    public String getName() {
        return name;
    }

    public void move(int roll, Board board){
        int newPosition = currentTile.getPosition() + roll;
        currentTile = board.getTile(currentTile.executeAction(newPosition));
        System.out.println(name + " moved to tile " + currentTile.getPosition());
    }

    public Boolean hasWon(){
        return currentTile.getPosition() == 90;
    }
}
