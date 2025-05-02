package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.utils.BoardGameFactory;
import edu.ntnu.model.board.BoardGame;
import edu.ntnu.views.SnakesAndLaddersLevelView;
import java.util.List;

public class LevelController {
  private SnakesAndLaddersLevelView view;

  public LevelController(SnakesAndLaddersLevelView view) {
    this.view = view;
  }

  public BoardGame createGame(String level, List<Player> players) {
    return BoardGameFactory.createBoardGame(level.toLowerCase(), players);
  }
}