package edu.ntnu.model.board;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the game board.
 * The board consists of a collection of objects
 */
public class Board {
  private Map<Integer, Tile> tiles;

  /**
   * Constructor for the Board class.
   * Initializes the board with a given number of tiles
   *
   * @param size the number of tiles on the board
   */
  public Board(int size) {
    this.tiles = new HashMap<>();
    for (int i = 1; i <= size; i++) {
      tiles.put(i, new Tile(i));
    }
  }

  public Map<Integer, Tile> getTiles() {
    return tiles;
  }

  /**
   * Method for returning the starting tile of the board.
   *
   * @return the first tile on the baord
   */
  public Tile getStartTile() {
    return tiles.get(1);
  }

  /**
   * Method for adding a tile to the board.
   *
   * @param tile the tile to be added
   */
  public void addTile(Tile tile) {
    tiles.put(tile.getPosition(), tile);
  }

  /**
   * Method for retrieving a specific tile from the board based on its ID.
   *
   * @param tileId the ID of the tile to retrieve
   * @return the tile with the given ID
   */
  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }

  /**
   * Method for returning the total number of tiles on the board.
   *
   * @return the size of the board
   */
  public int getSize() {
    return tiles.size();
  }
}