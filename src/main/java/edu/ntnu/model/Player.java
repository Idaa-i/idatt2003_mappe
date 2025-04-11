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

    public String move(int roll, Board board) {
        this.board = board; // Ensure board is set
        if (skipOneRound) {
            skipOneRound = false;
            return name + " skips this round.";
        }
        if (currentTile == null) {
            return name + " cannot move: no current tile.";
        }
        StringBuilder message = new StringBuilder(name + " rolled " + roll);
        int boardSize = board.getSize();
        int newPosition = currentTile.getPosition() + roll;

        // Handle moving beyond board size
        if (newPosition > boardSize) {
            int excess = newPosition - boardSize;
            newPosition = boardSize - excess;
        }

        Tile newTile = board.getTile(newPosition);
        if (newTile == null) {
            message.append(", moving to invalid tile");
            return message.toString();
        }

        TileAction action = newTile.getAction();
        int finalPosition = newPosition;

        if (action != null) {
            finalPosition = action.execute(newPosition);
            if (action instanceof LadderAction) {
                message.append("\nLanded on ladder up");
            } else if (action instanceof SnakeAction) {
                message.append("\nLanded on ladder down");
            } else if (action instanceof SkipOneRoundAction) {
                message.append("\nLanded on skip-tile");
                skipOneRound = true;
                currentTile = newTile;
                return message.toString();
            } else if (action instanceof BackToStartAction) {
                message.append("\nLanded on back-to-start-tile");
            }
            newTile = board.getTile(finalPosition);
        }

        message.append("\nMoving to tile ").append(finalPosition);
        currentTile = newTile;
        return message.toString();
    }

    public boolean hasWon() {
        return currentTile != null && board != null && currentTile.getPosition() == board.getSize();
    }
}
