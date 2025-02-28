package edu.ntnu;
import edu.ntnu.game.BoardGame;


public class BoardGameApp {
  public static void main(String[] args) {
    BoardGame boardGame = new BoardGame(2);
    boardGame.play();
  }
}
