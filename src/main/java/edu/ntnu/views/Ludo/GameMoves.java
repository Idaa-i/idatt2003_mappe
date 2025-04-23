package edu.ntnu.views.Ludo;

import edu.ntnu.game.Die;
import edu.ntnu.game.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class GameMoves {

    private Layout la;
    private Build_Player p;
    private int delay = 10;
    private int current_player, dice;
    private int flag = 0, roll, kill = 0;
    private boolean turnSkipped = false;
    private Die die = new Die();

    public GameMoves(ArrayList<Player> logicPlayers) {
        la = new Layout(80, 50, logicPlayers);
        p = new Build_Player(logicPlayers, la.height, la.width);  // pass logic players here
        dice = 0;
        flag = 0;
        roll = 0;
        kill = 0;
        current_player = 0;
    }

    // Method to handle drawing logic
    public void draw(GraphicsContext gc) {
        la.draw(gc);
        p.draw(gc);

        if (p.players[current_player] != null && p.players[current_player].coin == 4) {
            gc.setFill(Color.WHITE);
            gc.fillRect(590, 100, 380, 130);
            gc.setFill(getPlayerColor(current_player));
            gc.setFont(new Font("serif", 40));
            gc.fillText(p.players[current_player].getName(), 600, 150);
            gc.fillText("Congratulations.", 600, 200);
            resetGame();
        } else if (dice != 0) {
            gc.setFill(Color.WHITE);
            gc.fillRect(590, 100, 380, 130);
            gc.setFill(getPlayerColor(current_player));
            gc.setFont(new Font("serif", 40));
            gc.fillText(p.players[current_player].getName(), 600, 150);
            gc.fillText("Number on dice is " + dice, 600, 200);
        }

        if (flag == 0 && dice != 0 && dice != 6 && kill == 0) {
            if (turnSkipped || (dice != 6 && roll >= 1)) {
                turnSkipped = false;
                current_player = (current_player + 1) % p.players.length;
                dice = 0;
                roll = 0;
            }
        }
    }

    public void handleKeyPress(KeyEvent e) {
        if (e.getCode().toString().equals("ENTER") && flag == 0) {
            roll++;
            dice = die.roll();
            flag = checkForPlayableMove();
            turnSkipped = (flag == 0);
        }
    }

    // Mouse click event handler (move coins)
    public void handleMouseClick(MouseEvent e) {
        if (flag == 1) {
            // Adjust coordinates relative to the board
            int x = (int) ((e.getX() - 80) / la.width);
            int y = (int) ((e.getY() - 50) / la.height);

            // Check if click is on a pawn
            for (int i = 0; i < 4; i++) {
                Pawn currentPawn = p.players[current_player].pawns[i];

                // Check if pawn can be moved
                boolean canMoveFromHome = (currentPawn.current == -1 && dice == 6);
                boolean canMoveOnBoard = (currentPawn.current != -1 &&
                        currentPawn.current != 56 &&
                        (currentPawn.current + dice) <= 56);

                // Check if clicked pawn matches current pawn's position
                boolean isPawnClicked = (currentPawn.current == -1 &&
                        x == Path.initialX[current_player][i] &&
                        y == Path.initialY[current_player][i]) ||
                        (currentPawn.current != -1 &&
                                x == Path.ax[current_player][currentPawn.current] &&
                                y == Path.ay[current_player][currentPawn.current]);

                // If pawn can be moved from home and is clicked
                if (isPawnClicked && canMoveFromHome) {
                    // Set the pawn to -5 to start 5 tiles back from the original start
                    currentPawn.current = 0;

                    // Reset dice and flag
                    flag = 0;

                    // Trigger canvas redraw
                    if (e.getSource() instanceof javafx.scene.canvas.Canvas) {
                        javafx.scene.canvas.Canvas canvas = (javafx.scene.canvas.Canvas) e.getSource();
                        canvas.getScene().getRoot().requestLayout();
                    }

                    break;
                }
                // Existing logic for moving pawns already on the board
                else if (isPawnClicked && canMoveOnBoard) {
                    // Existing move logic remains the same
                    currentPawn.current += dice;

                    // Check if pawn reached home
                    if (currentPawn.current == 56) {
                        p.players[current_player].coin++;
                    }

                    // Handle collision
                    handleCollision(i);

                    // Reset dice and flag
                    flag = 0;

                    // Trigger canvas redraw
                    if (e.getSource() instanceof javafx.scene.canvas.Canvas) {
                        javafx.scene.canvas.Canvas canvas = (javafx.scene.canvas.Canvas) e.getSource();
                        canvas.getScene().getRoot().requestLayout();
                    }

                    break;
                }
            }
        }
    }

    // Helper method to get player color
    private Color getPlayerColor(int player) {
        return switch (player) {
            case 0 -> Color.web("E76264");
            case 1 -> Color.web("719063");
            case 2 -> Color.web("E79A61");
            case 3 -> Color.web("9D61E6");
            default -> Color.BLACK;
        };
    }

    // Helper method to check if a move is possible
    private int handleMove(int x, int y) {
        int value = -1;
        for (int i = 0; i < 4; i++) {
            // Check if this pawn can be moved
            if ((p.players[current_player].pawns[i].current == -1 && dice == 6) ||
                    (p.players[current_player].pawns[i].current != -1 &&
                            p.players[current_player].pawns[i].current != 56 &&
                            (p.players[current_player].pawns[i].current + dice) <= 56)) {
                value = i;
                flag = 0;
                break;
            }
        }
        if (value != -1) {
            p.players[current_player].pawns[value].current += dice;
            if (p.players[current_player].pawns[value].current == 56) {
                p.players[current_player].coin++;
            }
            handleCollision(value);
        }
        return value;
    }

    // Helper method to handle collision with another player's piece
    private void handleCollision(int value) {
        int k = 0;
        int currentPosition = p.players[current_player].pawns[value].current;
        if ((currentPosition % 13) != 0 && (currentPosition % 13) != 8 && currentPosition < 51) {
            for (int i = 0; i < 4; i++) {
                if (i != current_player) {
                    for (int j = 0; j < 4; j++) {
                        int tem1 = Path.ax[current_player][currentPosition], tem2 = Path.ay[current_player][currentPosition];
                        if (p.players[i].pawns[j].x == tem1 && p.players[i].pawns[j].y == tem2) {
                            p.players[i].pawns[j].current = -1;
                            kill = 1;
                            k = 1;
                            break;
                        }
                    }
                }
                if (k == 1) break;
            }
        }
    }

    // Helper method to check for possible moves
    private int checkForPlayableMove() {
        for (int i = 0; i < 4; i++) {
            if (p.players[current_player].pawns[i].current != -1 &&
                    p.players[current_player].pawns[i].current != 56 &&
                    (p.players[current_player].pawns[i].current + dice) <= 56) {
                return 1;
            }
        }
        if (dice == 6) {
            for (int i = 0; i < 4; i++) {
                if (p.players[current_player].pawns[i].current == -1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private void resetGame() {
        current_player = 0;
        la = new Layout(80, 50, p.getLogicPlayers());
        dice = 0;
        flag = 0;
        roll = 0;
        kill = 0;
    }
}