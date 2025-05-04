package edu.ntnu.model.board;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse som representerer selve spillbrettet.
 */
public class Board {
  private Map<Integer, Tile> tiles;

  /**
   * Konstruktor for Board-klassen.
   * Brettet starter tomt
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
   * Metode for å hente startfeltet på brettet.
   *
   * @return første felt på brettet
   */
  public Tile getStartTile() {
    return tiles.get(1);
  }

  /**
   * Metode for å legge til felt på brettet.
   *
   * @param tile feltet som skal legges til
   */
  public void addTile(Tile tile) {
    tiles.put(tile.getPosition(), tile);
  }

  /**
   * Metode for å hente ett felt fra spillbrette basert på feltets ID.
   *
   * @param tileId feltets ID
   * @return den tilsvarende felt-ID
   */
  public Tile getTile(int tileId) {
    return tiles.get(tileId);
  }

  public int getSize() {
    return tiles.size();
  }
}