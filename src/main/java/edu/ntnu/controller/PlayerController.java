package edu.ntnu.controller;

import edu.ntnu.model.Player;
import edu.ntnu.model.board.Tile;
import edu.ntnu.views.EditSnLPlayersView;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class responsible for managing players in the game setup phase.
 */
public class PlayerController {
  private List<Player> players;
  private EditSnLPlayersView view;

  /**
   * Constructor for PlayerController class.
   * Constructs a PLayerController with the specified view
   *
   * @param view the view used to display and edit player information
   */
  public PlayerController(EditSnLPlayersView view) {
    this.players = new ArrayList<>();
    this.view = view;
  }

  /**
   * Method for adding a new player to the list and updated the view.
   *
   * @param name      the name of the player
   * @param color     the color representing the player
   * @param startTile the starting tile for the player
   */
  public void addPlayer(String name, String color, Tile startTile) {
    Player player = new Player(name, color, startTile);
    players.add(player);
    view.updatePlayerList(players);
  }

  /**
   * Method for removing a player form the list and updates the view.
   *
   * @param player the player to remove
   */
  public void removePlayer(Player player) {
    players.remove(player);
    view.updatePlayerList(players);
  }

  /**
   * Method for updating the name of the player.
   *
   * @param player  the player whose name is being updated.
   * @param newName the new name to assign
   */
  public void updatePlayerName(Player player, String newName) {
    player.setName(newName);
    view.updatePlayerList(players);
  }

  /**
   * Method for updating the color of the player.
   *
   * @param player   the player whose color is being updated
   * @param newColor the new color to assign
   */
  public void updatePlayerColor(Player player, String newColor) {
    player.setColor(newColor);
    view.updatePlayerList(players);
  }

  /**
   * Method for returning the list of players.
   *
   * @return the list of players.
   */
  public List<Player> getPlayers() {
    return players;
  }
}