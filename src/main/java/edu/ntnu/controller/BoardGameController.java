package edu.ntnu.controller;

import edu.ntnu.model.BoardGame;
import edu.ntnu.model.Player;
import edu.ntnu.model.Tile;
import edu.ntnu.view.GameView;

public class BoardGameController {
  private BoardGame model;
  private GameView view;

  public BoardGameController(BoardGame model, GameView view) {
    this.model = model;
    this.view = view;
    model.addObserver(new BoardGameObserverImpl());
  }

  public void playTurn() {
    for (Player player : model.getPlayers()) {
      if (player.isSkipOneRound()) {
        view.showMessage(player.getName() + " skips this round");
        player.setSkipOneRound(false);
        continue;
      }
      int roll = model.rollDice();
      view.showMessage(player.getName() + " rolled " + roll);
      player.move(roll, model.getBoard());
      model.notifyPlayerMove(player);
      view.updatePlayerPosition(player);

      if (player.hasWon()) {
        view.showMessage(player.getName() + " wins!");
        model.setGameOver(true);
        model.notifyPlayerWon(player);
        break;
      }
    }
  }

  private class BoardGameObserverImpl implements edu.ntnu.model.BoardGameObserver {
    @Override
    public void onPlayerMove(Player player, Tile newPosition) {
      view.updatePlayerPosition(player);
    }

    @Override
    public void onPlayerWin(Player winner) {
      view.showMessage(winner.getName() + " has won the game!");
    }
  }
}