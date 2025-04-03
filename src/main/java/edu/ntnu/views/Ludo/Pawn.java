package edu.ntnu.views.Ludo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pawn {

    int x, y;       // Position of the pawn on the board
    int current;    // Index of the pawn's current position on the path
    int height, width;

    // Constructor to initialize the pawn's properties
    public Pawn(int h, int w) {
        this.current = -1;  // -1 means the pawn is not on the board yet
        this.x = -1;
        this.y = -1;
        this.height = h;
        this.width = w;
    }

    // Draw the pawn at its current position
    public void draw(GraphicsContext gc, int i, int j, int play) {
        // Initial position if pawn hasn't moved yet (current == -1)
        if (current == -1) {
            int temp1 = 80 + (height / 2), temp2 = 50 + (width / 2);
            x = i;
            y = j;

            // Set color based on player index using the new color scheme
            switch (play) {
                case 0: gc.setFill(Color.web("E76264")); break;  // Salmon/Red
                case 1: gc.setFill(Color.web("719063")); break;  // Green
                case 2: gc.setFill(Color.web("E79A61")); break;  // Orange
                case 3: gc.setFill(Color.web("9D61E6")); break;  // Purple
            }

            // Draw pawn as an oval
            gc.fillOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10);

            // Draw the outline of the pawn
            gc.setLineWidth(2);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(temp1 + 5 + (i * width), temp2 + 5 + (j * height), width - 10, height - 10);
        } else {
            // Draw pawn at the new position on the path
            int temp1 = 80, temp2 = 50;
            x = Path.ax[play][current];  // Get the x-coordinate of the pawn on the path
            y = Path.ay[play][current];  // Get the y-coordinate of the pawn on the path

            // Set color based on player index using the new color scheme
            switch (play) {
                case 0: gc.setFill(Color.web("E76264")); break;  // Salmon/Red
                case 1: gc.setFill(Color.web("719063")); break;  // Green
                case 2: gc.setFill(Color.web("E79A61")); break;  // Orange
                case 3: gc.setFill(Color.web("9D61E6")); break;  // Purple
            }

            // Draw pawn at the new position
            gc.fillOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10);

            // Draw the outline of the pawn
            gc.setLineWidth(2);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(temp1 + 5 + (x * width), temp2 + 5 + (y * height), width - 10, height - 10);
        }
    }
}
