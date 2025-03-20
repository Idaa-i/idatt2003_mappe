package edu.ntnu.game;
import edu.ntnu.board.BackToStartAction;
import edu.ntnu.board.LadderAction;
import edu.ntnu.board.SkipOneRoundAction;
import edu.ntnu.board.SnakeAction;
import edu.ntnu.board.TileAction;
import java.util.*;
import edu.ntnu.board.Board;

public class BoardGame {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private boolean gameOver;

    public BoardGame(Board board, int numPlayers) {
        this.board = new Board(90);
        players = new ArrayList<>();
        dice = new Dice(2);
        gameOver = false;

        board.addAction(new LadderAction(16), 6);
        board.addAction(new LadderAction(46), 34);
        board.addAction(new LadderAction(83), 79);

        board.addAction(new SnakeAction(2), 22);
        board.addAction(new SnakeAction(9), 27);
        board.addAction(new SnakeAction(36), 58);
        board.addAction(new SnakeAction(52), 70);
        board.addAction(new SnakeAction(64), 86);

        board.addAction(new BackToStartAction(1), 38);
        board.addAction(new BackToStartAction(1), 67);

        board.addAction(new SkipOneRoundAction(32), 32);
        board.addAction(new SkipOneRoundAction(41), 41);

        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i, board.getStartTile()));
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

