package edu.ntnu.game;

import edu.ntnu.CSVExample;
import edu.ntnu.board.SkipOneRoundAction;
import edu.ntnu.board.Tile;
import edu.ntnu.board.Board;
import edu.ntnu.board.TileAction;

public class Player {
    private String name;
    private String color;
    private Tile currentTile;
    private boolean skipOneRound;

    public Player(String name, String color, Tile startTile){
        this.name = name;
        this.color = color;
        this.currentTile = startTile;
        this.skipOneRound = false;
        CSVExample.addPlayer(this);
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) { this.color = color; }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public boolean isSkipOneRound() {
        return skipOneRound;
    }

    public void setSkipOneRound(boolean skipOneRound) {
        this.skipOneRound = skipOneRound;
    }

    public void move(int roll, Board board){
        if (skipOneRound){
            System.out.println(name + " skips one round!");
            skipOneRound = false;
            return;
        }
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

        Tile newTile = board.getTile(newPosition);

        if (newTile == null) {
            System.err.println(name + "landed on an invalid tile: " + newPosition);
            return;
        }

        TileAction action = board.getAction(newPosition);
        if (action != null) {
            newPosition = action.execute(newPosition);
            newTile = board.getTile(newPosition);
            if (action instanceof SkipOneRoundAction) {
                skipOneRound = true;
            }
        }

        currentTile = newTile;
        System.out.println(name + " moved to tile " + currentTile.getPosition());
    }

    public Boolean hasWon(){
        return currentTile.getPosition() == 90;
    }
}
