package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.BoardGame;
import edu.ntnu.utils.BoardGameFactory;
import edu.ntnu.views.SnakesAndLaddersLevelView;
import java.util.List;

/**
 * Controller class responsible for handling level selection and game creation.
 */
public class LevelController {
  private SnakesAndLaddersLevelView view;

  /**
   * Constructor for LevelController class.
   * Constructs a LevelController with the specified view
   *
   * @param view the view used to display level selection and related UI
   */
  public LevelController(SnakesAndLaddersLevelView view) {
    this.view = view;
  }

  /**
   * Method for creating a new BoardGame instance based on the sleected leven and list of players.
   *
   * @param level   the difficulty level or board configuration name
   * @param players the list of players participating in the game
   * @return a new BoardGame instance configured for the selected level
   */
  public BoardGame createGame(String level, List<Player> players) {
    return BoardGameFactory.createBoardGame(level.toLowerCase(), players);
  }
}