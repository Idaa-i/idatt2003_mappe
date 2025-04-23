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
    private int consecutiveSixes = 0;

    public GameMoves(ArrayList<Player> logicPlayers) {
        la = new Layout(80, 50, logicPlayers);
        p = new Build_Player(logicPlayers, la.height, la.width);
        dice = 0;
        flag = 0;
        roll = 0;
        kill = 0;
        current_player = 0;
    }

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
        } else if (dice != 0 && p.players[current_player] != null) {
            gc.setFill(Color.WHITE);
            gc.fillRect(590, 100, 380, 130);
            gc.setFill(getPlayerColor(current_player));
            gc.setFont(new Font("serif", 40));
            gc.fillText(p.players[current_player].getName(), 600, 150);
            gc.fillText("Number on dice is " + dice, 600, 200);
        }

        if (flag == 0 && dice != 0 && kill == 0) {
            if (turnSkipped || roll >= 1 && dice != 6) {
                turnSkipped = false;
                nextPlayer();
            }
        }
    }

    public void handleKeyPress(KeyEvent e) {
        if (p.players[current_player] == null) {
            nextPlayer();
            return;
        }

        if (e.getCode().toString().equals("ENTER") && flag == 0) {
            dice = die.roll();

            if (dice == 6) {
                consecutiveSixes++;
                if (consecutiveSixes >= 3) {
                    consecutiveSixes = 0;
                    turnSkipped = true;
                    flag = 0;
                    return;
                }
            } else {
                consecutiveSixes = 0;
            }

            roll++;
            flag = checkForPlayableMove();
            turnSkipped = (flag == 0);
        }
    }

    public void handleMouseClick(MouseEvent e) {
        if (flag == 1 && p.players[current_player] != null) {
            int x = (int) ((e.getX() - 80) / la.width);
            int y = (int) ((e.getY() - 50) / la.height);

            for (int i = 0; i < 4; i++) {
                Pawn currentPawn = p.players[current_player].pawns[i];

                boolean canMoveFromHome = (currentPawn.current == -1 && dice == 6);
                boolean canMoveOnBoard = (currentPawn.current != -1 &&
                        currentPawn.current != 56 &&
                        (currentPawn.current + dice) <= 56);

                boolean isPawnClicked = (currentPawn.current == -1 &&
                        x == Path.initialX[current_player][i] &&
                        y == Path.initialY[current_player][i]) ||
                        (currentPawn.current != -1 &&
                                x == Path.ax[current_player][currentPawn.current] &&
                                y == Path.ay[current_player][currentPawn.current]);

                if (isPawnClicked && canMoveFromHome) {
                    currentPawn.current = 0;
                    flag = 0;
                    if (e.getSource() instanceof javafx.scene.canvas.Canvas canvas) {
                        canvas.getScene().getRoot().requestLayout();
                    }
                    break;
                } else if (isPawnClicked && canMoveOnBoard) {
                    currentPawn.current += dice;
                    if (currentPawn.current == 56) {
                        p.players[current_player].coin++;
                    }
                    handleCollision(i);
                    flag = 0;
                    if (e.getSource() instanceof javafx.scene.canvas.Canvas canvas) {
                        canvas.getScene().getRoot().requestLayout();
                    }
                    break;
                }
            }
        }
    }

    private Color getPlayerColor(int player) {
        return switch (player) {
            case 0 -> Color.web("E76264");
            case 1 -> Color.web("719063");
            case 2 -> Color.web("E79A61");
            case 3 -> Color.web("9D61E6");
            default -> Color.BLACK;
        };
    }

    private int handleMove(int x, int y) {
        int value = -1;
        for (int i = 0; i < 4; i++) {
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

    private void handleCollision(int value) {
        int k = 0;
        int currentPosition = p.players[current_player].pawns[value].current;
        if ((currentPosition % 13) != 0 && (currentPosition % 13) != 8 && currentPosition < 51) {
            for (int i = 0; i < 4; i++) {
                if (i != current_player && p.players[i] != null) {
                    for (int j = 0; j < 4; j++) {
                        int tem1 = Path.ax[current_player][currentPosition];
                        int tem2 = Path.ay[current_player][currentPosition];
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

    private int checkForPlayableMove() {
        if (p.players[current_player] == null) return 0;

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
        consecutiveSixes = 0;
    }

    private void nextPlayer() {
        do {
            current_player = (current_player + 1) % p.players.length;
        } while (p.players[current_player] == null);
        dice = 0;
        roll = 0;
        consecutiveSixes = 0;
    }
}
