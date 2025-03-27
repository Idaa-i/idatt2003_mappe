package edu.ntnu.board;

import java.util.HashMap;
import java.util.Map;

public class Tile {
    private int position;
    private TileAction action;
    private Map<Integer, TileAction> actions;


    public Tile(int position) {
        this.position = position;
        this.actions = new HashMap<>();

    }

    public int getPosition() {
        return position;
    }

    /**
     * Metode for å hente handlingen på et spesifikt felt.
     *
     * @param position posisjonen til feltet
     * @return handlingen på feltet
     */
    public TileAction getAction(int position) {
        return actions.get(position);
    }

    /**
     * Metode for å legge til en handling på et spesifikt felt.
     *
     * @param action   handlingen som skal legges til
     */
    public void setAction(TileAction action) {
        this.action = action;
    }

    public int executeAction(int currentPosition){
        if (action != null){
            return action.execute(currentPosition);
        }
        return currentPosition;
    }
}
