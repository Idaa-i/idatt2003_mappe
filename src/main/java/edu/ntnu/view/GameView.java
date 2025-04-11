package edu.ntnu.view;

import edu.ntnu.model.Player;

public interface GameView {
  void updatePlayerPosition(Player player);
  void announceWinner(Player winner);
  void showActionMessage(String message);
}
