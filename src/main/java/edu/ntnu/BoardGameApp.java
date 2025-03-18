package edu.ntnu;
import edu.ntnu.board.factory.BoardGameFactory;
import edu.ntnu.game.BoardGame;


public class BoardGameApp {
  public static void main(String[] args) {
    BoardGame boardGame = BoardGameFactory.createBoardGame("easy", 2);
    boardGame.play();
  }
}
