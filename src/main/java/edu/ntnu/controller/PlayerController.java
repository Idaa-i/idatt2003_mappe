package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.views.EditSnLPlayersView;

import java.util.ArrayList;
import java.util.List;

public class PlayerController {
  private List<Player> players;
  private EditSnLPlayersView view;

  public PlayerController(EditSnLPlayersView view) {
    this.players = new ArrayList<>();
    this.view = view;
  }

  public void addPlayer(String name, String color, edu.ntnu.model.Tile startTile) {
    Player player = new Player(name, startTile);
    player.setColor(color);
    players.add(player);
    view.updatePlayerList(players);
  }

  public void removePlayer(Player player) {
    players.remove(player);
    view.updatePlayerList(players);
  }

  public List<Player> getPlayers() {
    return players;
  }
}