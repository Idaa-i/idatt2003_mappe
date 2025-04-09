package edu.ntnu.model;
import java.util.*;

public class BoardGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private boolean gameOver;
    private List<BoardGameObserver> observers;

    public BoardGame(Board board, int numPlayers) {
        this.board = new Board(90);
        players = new ArrayList<>();
        dice = new Dice(2);
        gameOver = false;
        observers = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, board.getStartTile()));
        }
    }

    public void addObserver(BoardGameObserver observer) {
        observers.add(observer);
    }

    private void notifyPlayerMove(Player player) {
        for (BoardGameObserver observer : observers) {
            observer.onPlayerMove(player, player.getCurrentTile());
        }
    }

    private void notifyPlayerWon(Player winner) {
        for (BoardGameObserver observer : observers) {
            observer.onPlayerWin(winner);
        }
    }

    public void play() {
        while (!gameOver) {
            for (Player player : players) {
                if (player.isSkipOneRound()) {
                    System.out.println(player.getName() + " skips this round");
                    player.setSkipOneRound(false);
                    continue;
                }
                int roll = dice.roll();
                System.out.println(player.getName() + " rolled " + roll);
                player.move(roll, board);

                if (player.hasWon()) {
                    System.out.println(player.getName() + " wins!");
                    gameOver = true;
                    break;
                }
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}

