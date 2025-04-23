package edu.ntnu.controller;

import edu.ntnu.model.BoardGameObserver;
import edu.ntnu.model.Player;
import edu.ntnu.model.Tile;
import edu.ntnu.views.GameView;

/**
 * Implementation of BoardGameObserver to handle updates from BoardGame and notify the view.
 */
public class BoardGameObserverImplementation implements BoardGameObserver {
  private final GameView view;

  /**
   * Constructs a BoardGameObserverImpl with the specified view.
   * @param view the GameView to update
   */
  public BoardGameObserverImplementation(GameView view) {
    this.view = view;
  }

  @Override
  public void onPlayerMove(Player player, Tile newPosition) {
    view.updatePlayerPosition(player);
  }

  @Override
  public void onPlayerWin(Player winner) {
    view.announceWinner(winner);
  }
}