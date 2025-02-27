package edu.ntnu.game;
import java.util.*;
import edu.ntnu.board.Board;

class BoardGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private boolean gameOver;

    public BoardGame(int numPlayers) {
        board = new Board(90);
        players = new ArrayList<>();
        dice = new Dice(2);
        gameOver = false;

        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, board.getStartTile()));
        }
    }

    public void play() {
        while (!gameOver) {
            for (Player player : players) {
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
}

