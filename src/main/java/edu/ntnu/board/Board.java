package edu.ntnu.board;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse som representerer selve spillbrettet.
 */
public class Board {
  private Map<Integer, Tile> tiles;
  private Map<Integer, TileAction> actions;

  /**
   * Konstruktor for Board-klassen.
   * Brettet starter tomt
   */
  public Board(int size) {
    this.tiles = new HashMap<>();
    this.actions = new HashMap<>();
    for (int i = 1; i <= size; i++) {
      tiles.put(i, new Tile(i));
    }

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

  /**
   * Metode for å legge til en handling på et spesifikt felt.
   *
   * @param action   handlingen som skal legges til
   * @param position posisjonen til feltet
   */
  public void addAction(TileAction action, int position) {
    actions.put(position, action);
  }

  /**
   * Metode for å hente handlingen på et spesifikt felt.
   *
   * @param position posisjonen til feltet
   * @return handlingen på flisen
   */
  public TileAction getAction(int position) {
    return actions.get(position);
  }
}
