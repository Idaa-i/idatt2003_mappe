package edu.ntnu.model.board;

import edu.ntnu.model.Player;
import edu.ntnu.model.dice.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {

  private Board board;
  private Player player1;
  private Player player2;
  private BoardGame game;

  @BeforeEach
  void setUp() {
    board = new Board(10);
    player1 = new Player("Alice", "Red", board.getStartTile());
    player2 = new Player("Bob", "Blue", board.getStartTile());
    game = new BoardGame(board, Arrays.asList(player1, player2), 1);
  }

  @Test
  void testGameInitialization() {
    assertEquals(2, game.getPlayers().size());
    assertEquals(board, game.getBoard());
    assertFalse(game.isGameOver());
    assertEquals(1, player1.getCurrentTile().getPosition());
    assertEquals(1, player2.getCurrentTile().getPosition());
  }

  @Test
  void testSetAndCheckGameOver() {
    assertFalse(game.isGameOver());
    game.setGameOver(true);
    assertTrue(game.isGameOver());
  }

  @Test
  void testDiceRollRange() {
    Dice dice = new Dice(2);
    int roll = dice.roll();
    assertTrue(roll >= 2 && roll <= 12);
  }
}
