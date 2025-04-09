package edu.ntnu.view;

import edu.ntnu.model.Player;

public interface GameView {
  void showMessage(String message);
  void updatePlayerPosition(Player player);
  void announceWinner(Player winner);
}