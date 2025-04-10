package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.utils.BoardGameFactory;
import edu.ntnu.model.BoardGame;
import edu.ntnu.view.SnakesAndLaddersLevelView;
import edu.ntnu.view.HardSnLGameView;
import edu.ntnu.view.EasySnLGameView;
import java.util.List;

public class LevelController {
  private SnakesAndLaddersLevelView view;

  public LevelController(SnakesAndLaddersLevelView view) {
    this.view = view;
  }

  public void openGame(String level, List<Player> players) {
    BoardGame game = BoardGameFactory.createBoardGame(level.toLowerCase(), players);
    if (level.equalsIgnoreCase("Hard")) {
      HardSnLGameView gameView = new HardSnLGameView(game);
      gameView.start(view.getStage());
    } else if (level.equalsIgnoreCase("Easy")) {
      EasySnLGameView gameView = new EasySnLGameView(game);
      gameView.start(view.getStage());
    }
  }
}