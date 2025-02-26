package edu.ntnu;

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
  public Board() {
    this.tiles = new HashMap<>();
  }

  /**
   * Metode for å legge til felt på brettet.
   *
   * @param tile feltet som skal legges til
   */
  public void addTile(Tile tile) {
    tiles.put(tile.getTileId(), tile);
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
}
