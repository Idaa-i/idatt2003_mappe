package edu.ntnu;
import edu.ntnu.utils.BoardGameFactory;
import edu.ntnu.model.BoardGame;


public class BoardGameApp {
  public static void main(String[] args) {
    BoardGame boardGame = BoardGameFactory.createBoardGame("easy", 2);
    boardGame.play();
  }
}
