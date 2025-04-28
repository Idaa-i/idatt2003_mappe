package edu.ntnu.views.Ludo;

import edu.ntnu.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.HashMap;

public class Layout {

    int x, y, width, height;
    ArrayList<edu.ntnu.model.Player> logicPlayers;

    private static final Color COLOR_1 = Color.web("719063");  // Green-like color
    private static final Color COLOR_2 = Color.web("9D61E6");  // Purple-like color
    private static final Color COLOR_3 = Color.web("E79A61");  // Orange-like color
    private static final Color COLOR_4 = Color.web("E76264");  // Salmon/Red-like color

    private final HashMap<String, int[]> colorToNamePosition = new HashMap<>() {{
        put("#E76264", new int[]{90, 35});   // Red = Player 1 (top-left)
        put("#719063", new int[]{370, 35});  // Green = Player 2 (top-right)
        put("#E79A61", new int[]{370, 540}); // Orange = Player 3 (bottom-right)
        put("#9D61E6", new int[]{90, 540});  // Purple = Player 4 (bottom-left)
    }};

    public Layout(int xi, int yi, ArrayList<Player> logicPlayers) {
        x = xi;
        y = yi;
        width = 30;
        height = 30;
        this.logicPlayers = logicPlayers;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, 15 * width, 15 * height);

        for (int i = 0; i < 6; i++) {
            gc.setFill(COLOR_4);
            gc.fillRect(x + (i * width), y, width, height);
            gc.fillRect(x, y + (i * height), width, height);
            gc.fillRect(x + (i * width), y + (5 * height), width, height);
            gc.fillRect(x + (5 * width), y + (i * height), width, height);

            gc.setFill(COLOR_1);
            gc.fillRect(x + ((i + 9) * width), y, width, height);
            gc.fillRect(x + (9 * width), y + (i * height), width, height);
            gc.fillRect(x + ((i + 9) * width), y + (5 * height), width, height);
            gc.fillRect(x + (14 * width), y + (i * height), width, height);

            gc.setFill(COLOR_3);
            gc.fillRect(x + ((i + 9) * width), y + (9 * height), width, height);
            gc.fillRect(x + (9 * width), y + ((i + 9) * height), width, height);
            gc.fillRect(x + ((i + 9) * width), y + (14 * height), width, height);
            gc.fillRect(x + (14 * width), y + ((i + 9) * height), width, height);

            gc.setFill(COLOR_2);
            gc.fillRect(x + (i * width), y + (9 * height), width, height);
            gc.fillRect(x, y + ((i + 9) * height), width, height);
            gc.fillRect(x + (i * width), y + (14 * height), width, height);
            gc.fillRect(x + (5 * width), y + ((i + 9) * height), width, height);
        }

        for (int i = 1; i < 6; i++) {
            gc.setFill(COLOR_4);
            gc.fillRect(x + (i * width), y + (7 * height), width, height);
            gc.setFill(COLOR_3);
            gc.fillRect(x + ((8 + i) * width), y + (7 * height), width, height);
            gc.setFill(COLOR_1);
            gc.fillRect(x + (7 * width), y + (i * height), width, height);
            gc.setFill(COLOR_2);
            gc.fillRect(x + (7 * width), y + ((8 + i) * height), width, height);
        }

        gc.setFill(COLOR_4);
        gc.fillRect(x + (1 * width), y + (6 * height), width, height);
        gc.setFill(COLOR_3);
        gc.fillRect(x + ((13) * width), y + (8 * height), width, height);
        gc.setFill(COLOR_1);
        gc.fillRect(x + (8 * width), y + (1 * height), width, height);
        gc.setFill(COLOR_2);
        gc.fillRect(x + ((6) * width), y + ((13) * height), width, height);

        int temp1 = x + 45, temp2 = y + 45;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                gc.setFill(COLOR_4);
                gc.fillRect(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height);
                gc.setFill(COLOR_3);
                gc.fillRect(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 9 * height, width, height);
                gc.setFill(COLOR_1);
                gc.fillRect(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height), width, height);
                gc.setFill(COLOR_2);
                gc.fillRect(temp1 + (2 * i * width), temp2 + (2 * j * height) + 9 * height, width, height);
            }
        }

        // Drawing the polygons
        gc.setFill(COLOR_4);
        double[] xPoints0 = {x + (6 * width), x + (6 * width), x + 15 + (7 * width)};
        double[] yPoints0 = {y + (6 * height), y + (9 * height), y + 15 + (7 * width)};
        gc.fillPolygon(xPoints0, yPoints0, 3);

        gc.setFill(COLOR_3);
        double[] xPoints1 = {x + (9 * width), x + (9 * width), x + 15 + (7 * width)};
        double[] yPoints1 = {y + (6 * height), y + (9 * height), y + 15 + (7 * width)};
        gc.fillPolygon(xPoints1, yPoints1, 3);

        gc.setFill(COLOR_1);
        double[] xPoints2 = {x + (6 * width), x + (9 * width), x + 15 + (7 * width)};
        double[] yPoints2 = {y + (6 * height), y + (6 * height), y + 15 + (7 * width)};
        gc.fillPolygon(xPoints2, yPoints2, 3);

        gc.setFill(COLOR_2);
        double[] xPoints3 = {x + (6 * width), x + (9 * width), x + 15 + (7 * width)};
        double[] yPoints3 = {y + (9 * height), y + (9 * height), y + 15 + (7 * width)};
        gc.fillPolygon(xPoints3, yPoints3, 3);

        gc.setLineWidth(2);
        gc.setStroke(Color.BLACK);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                gc.strokeRect(x + ((i + 6) * width), y + (j * height), width, height);
                gc.strokeRect(x + ((j) * width), y + ((i + 6) * height), width, height);
                gc.strokeRect(x + ((i + 6) * width), y + ((j + 9) * height), width, height);
                gc.strokeRect(x + ((j + 9) * width), y + ((i + 6) * height), width, height);
            }
        }

        gc.strokeRect(x + ((1) * width), y + (1 * height), 4 * width, 4 * height);
        gc.strokeRect(x + ((10) * width), y + (1 * height), 4 * width, 4 * height);
        gc.strokeRect(x + ((1) * width), y + (10 * height), 4 * width, 4 * height);
        gc.strokeRect(x + ((10) * width), y + (10 * height), 4 * width, 4 * height);
        gc.strokeRect(x, y, 15 * width, 15 * height);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                gc.strokeRect(temp1 + (2 * i * width), temp2 + (2 * j * height), width, height);
                gc.strokeRect(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height) + 9 * height, width, height);
                gc.strokeRect(temp1 + (2 * i * width) + 9 * width, temp2 + (2 * j * height), width, height);
                gc.strokeRect(temp1 + (2 * i * width), temp2 + (2 * j * height) + 9 * height, width, height);
            }
        }

        gc.strokePolygon(xPoints0, yPoints0, 3);
        gc.strokePolygon(xPoints1, yPoints1, 3);
        gc.strokePolygon(xPoints2, yPoints2, 3);
        gc.strokePolygon(xPoints3, yPoints3, 3);

        // Drawing circles
        gc.strokeOval(x + 5 + (6 * width), y + 5 + (2 * height), width - 10, height - 10);
        gc.strokeOval(x + 5 + (12 * width), y + 5 + (6 * height), width - 10, height - 10);
        gc.strokeOval(x + 5 + (8 * width), y + 5 + (12 * height), width - 10, height - 10);
        gc.strokeOval(x + 5 + (2 * width), y + 5 + (8 * height), width - 10, height - 10);

        // Drawing text
        gc.setFont(Font.font("serif", 40));
        if (logicPlayers != null) {
            for (Player p : logicPlayers) {
                int[] pos = colorToNamePosition.get(p.getColor());
                if (pos != null) {
                    gc.setFill(Color.web(p.getColor()));
                    gc.fillText(p.getName(), pos[0], pos[1]);
                }
            }
        }

        gc.setFill(Color.BLACK);
        gc.fillText("Instruction:", 550, 300);
        gc.fillText("1.Press enter to roll dice.", 550, 350);
        gc.fillText("2.Click on coin to move.", 550, 400);
    }
}
