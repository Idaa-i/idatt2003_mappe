package edu.ntnu.model;

public class Player {
    private String name;
    private String color;
    private Tile currentTile;
    private boolean skipOneRound;
    private Board board;

    public Player(String name, Tile startTile) {
        this.name = name;
        this.currentTile = startTile;
        this.skipOneRound = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public boolean isSkipOneRound() {
        return skipOneRound;
    }

    public void setSkipOneRound(boolean skipOneRound) {
        this.skipOneRound = skipOneRound;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void move(int roll, Board board) {
        if (skipOneRound) {
            skipOneRound = false;
            return;
        }
        if (currentTile == null) {
            return;
        }
        int boardSize = board.getSize();
        int newPosition = currentTile.getPosition() + roll;

        if (newPosition > boardSize) {
            int excess = newPosition - boardSize;
            newPosition = boardSize - excess;
        }

        Tile newTile = board.getTile(newPosition);
        if (newTile != null) {
            TileAction action = newTile.getAction();
            if (action != null) {
                int updatedPosition = action.execute(newPosition);
                if (action instanceof SkipOneRoundAction) {
                    skipOneRound = true;
                }
                newTile = board.getTile(updatedPosition);
            }
            currentTile = newTile;
        }
    }

    public boolean hasWon() {
        return currentTile != null && board != null && currentTile.getPosition() == board.getSize();
    }
}