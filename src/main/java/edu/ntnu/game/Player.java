package edu.ntnu.game;

import edu.ntnu.CSVExample;
import edu.ntnu.board.Tile;
import edu.ntnu.board.Board;

public class Player {
    private String name;
    private String color;
    private Tile currentTile;

    public Player(String name, Tile startTile){
        this.name = name;
        this.color = color;
        CSVExample.addPlayer(this);
        this.currentTile = startTile;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void move(int roll, Board board){
        if(currentTile == null){
            System.err.println(name + "has an invalid tile");
            return;
        }
        int newPosition = currentTile.getPosition() + roll;

        // If player lands over 90, go the difference backwards
        if (newPosition > 90) {
            int excess = newPosition - 90;
            newPosition = 90 - excess; // Go backwards
        }

        Tile newTile = board.getTile(currentTile.executeAction(newPosition));

        if (newTile == null) {
            System.err.println(name + "landed on an invalid tile: " + newPosition);
            return;
        }

        currentTile = newTile;
        System.out.println(name + " moved to tile " + currentTile.getPosition());
    }

    public Boolean hasWon(){
        return currentTile.getPosition() == 90;
    }
}
