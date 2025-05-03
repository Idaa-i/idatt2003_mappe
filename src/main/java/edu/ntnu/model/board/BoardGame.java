package edu.ntnu.model.board;

import edu.ntnu.model.BoardGameObserver;
import edu.ntnu.model.dice.Dice;
import edu.ntnu.model.Player;
import java.util.ArrayList;
import java.util.List;

public class BoardGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private boolean gameOver;
    private List<BoardGameObserver> observers;

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

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int rollDice() {
        return dice.roll();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void addObserver(BoardGameObserver observer) {
        observers.add(observer);
    }

    public void notifyPlayerMove(Player player) {
        for (BoardGameObserver observer : observers) {
            observer.onPlayerMove(player, player.getCurrentTile());
        }
    }

    public void notifyPlayerWon(Player winner) {
        for (BoardGameObserver observer : observers) {
            observer.onPlayerWin(winner);
        }
    }
}