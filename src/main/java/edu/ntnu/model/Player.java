package edu.ntnu.model;

import edu.ntnu.model.actions.BackToStartAction;
import edu.ntnu.model.actions.LadderAction;
import edu.ntnu.model.actions.SkipOneRoundAction;
import edu.ntnu.model.actions.SnakeAction;
import edu.ntnu.model.actions.TileAction;
import edu.ntnu.model.board.Board;
import edu.ntnu.model.board.Tile;

/**
 * Class representing a player in the board game.
 */
public class Player {
  private String name;
  private String color;
  private Tile currentTile;
  private boolean skipOneRound;
  private Board board;

  /**
   * Constructor for Player class.
   *
   * @param name      the player's name
   * @param color     the player's color
   * @param startTile the starttile of the player
   */
  public Player(String name, String color, Tile startTile) {
    this.name = name;
    this.color = color;
    this.currentTile = startTile;
    this.skipOneRound = false;
  }

  /**
   * Method for returning the player's name.
   *
   * @return the name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Method for setting the player's name.
   *
   * @param name the new name for the player
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Method for returning the player's color.
   *
   * @return the color of the player
   */
  public String getColor() {
    return color;
  }

  /**
   * Method for retting the player's color.
   *
   * @param color the new color for the player
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Method for returning the tile the player is currently on.
   *
   * @return the current tile
   */
  public Tile getCurrentTile() {
    return currentTile;
  }

  /**
   * Method for setting the player's current tile.
   *
   * @param currentTile the tile to set as current
   */
  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  /**
   * Method for checking if the player must skip the next round.
   *
   * @return true if the player must skip a round, otherwise false
   */
  public boolean isSkipOneRound() {
    return skipOneRound;
  }

  /**
   * Method for setting whether the player must skip the next round.
   *
   * @param skipOneRound true to skip next round, otherwise false
   */
  public void setSkipOneRound(boolean skipOneRound) {
    this.skipOneRound = skipOneRound;
  }

  /**
   * Method for setting the board the player is playing on.
   *
   * @param board the game board
   */
  public void setBoard(Board board) {
    this.board = board;
  }

  /**
   * Method for moving the player based on a dice roll ad applies any tile actions.
   *
   * @param roll  the result of the dice roll
   * @param board the game board
   * @return a message describing the move and any actions triggered
   */
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

  /**
   * Method for checking if the player has won the game.
   *
   * @return true if the player is on the last tile, otherwise false
   */
  public boolean hasWon() {
    return currentTile != null && board != null && currentTile.getPosition() == board.getSize();
  }
}