package edu.ntnu.model.board;

import edu.ntnu.model.BoardGameObserver;
import edu.ntnu.model.Player;
import edu.ntnu.model.dice.Dice;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the logic and state of a board game.
 */
public class BoardGame {
  private Board board;
  private List<Player> players;
  private Dice dice;
  private boolean gameOver;
  private List<BoardGameObserver> observers;

  /**
   * Constructor for BoardGame class.
   * Constructs a new BoardGame instance
   *
   * @param board   the game board
   * @param players the list of player participating in the game
   * @param numDice the number of dice used in the game
   */
  public BoardGame(Board board, List<Player> players, int numDice) {
    this.board = board;
    this.players = new ArrayList<>(players);
    this.dice = new Dice(numDice);
    this.gameOver = false;
    this.observers = new ArrayList<>();

    for (Player player : this.players) {
      player.setCurrentTile(board.getStartTile());
    }
  }

  /**
   * Method for returning the game board.
   *
   * @return the board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Method for returning the list of players in the game.
   *
   * @return the list of players
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Method for checking whether the game is over.
   *
   * @return true if the game is over, otherwise false
   */
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Method for setting the game over state.
   *
   * @param gameOver sets gameOver true to mark the game as over, otherwise false
   */
  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  /**
   * Mehtod for adding an obsrver to the game.
   *
   * @param observer the observer to add
   */
  public void addObserver(BoardGameObserver observer) {
    observers.add(observer);
  }

  /**
   * Method for notifying all observes that a player has moved.
   *
   * @param player the player who moved
   */
  public void notifyPlayerMove(Player player) {
    for (BoardGameObserver observer : observers) {
      observer.onPlayerMove(player, player.getCurrentTile());
    }
  }

  /**
   * Method for notifying all observes that a player has won the game.
   *
   * @param winner the player who won
   */
  public void notifyPlayerWon(Player winner) {
    for (BoardGameObserver observer : observers) {
      observer.onPlayerWin(winner);
    }
  }
}