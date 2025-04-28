package edu.ntnu.views.Ludo;

import edu.ntnu.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Build_Player {

    public PlayerWrapper[] players = new PlayerWrapper[4];
    int[][] initialX = {
            {1, 1, 3, 3},     // Red
            {10, 10, 12, 12}, // Green
            {10, 10, 12, 12}, // Orange
            {1, 1, 3, 3}      // Purple
    };
    int[][] initialY = {
            {1, 3, 1, 3},
            {1, 3, 1, 3},
            {10, 12, 10, 12},
            {10, 12, 10, 12}
    };

    private final HashMap<String, Integer> colorToIndex = new HashMap<>() {{
        put("#E76264", 0); // Red
        put("#719063", 1); // Green
        put("#E79A61", 2); // Orange
        put("#9D61E6", 3); // Purple
    }};

    public Build_Player(ArrayList<Player> logicPlayers, int height, int width) {
        for (Player p : logicPlayers) {
            String color = p.getColor();
            Integer index = colorToIndex.get(color);

            if (index != null && players[index] == null) {
                players[index] = new PlayerWrapper(p, height, width);
            } else {
                System.err.println("Invalid or duplicate color: " + color);
            }
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                players[i].draw(gc, initialX[i], initialY[i], i);
            }
        }
    }

    public ArrayList<Player> getLogicPlayers() {
        ArrayList<Player> result = new ArrayList<>();
        for (PlayerWrapper wrapper : players) {
            if (wrapper != null) {
                result.add(wrapper.getLogicPlayer());
            }
        }
        return result;
    }

}
