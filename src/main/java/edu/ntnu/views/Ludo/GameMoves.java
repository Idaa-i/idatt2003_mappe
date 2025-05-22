package edu.ntnu.views.Ludo;
import edu.ntnu.model.dice.Die;
import edu.ntnu.model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

/**
 * GameMoves handles the primary interaction and gameplay loop for a
 * Ludo game session. It coordinates between layout rendering, player moves,
 * dice rolling, user input, and win conditions.
 */
public class GameMoves {
    private Layout la;
    private Build_Player p;

    private int currentPlayer;         // index in p.players
    private int dice;                  // last die value
    private int flag;                  // 1 = there is at least one legal move to play
    private int bonusTurns;            // 0 = no bonus, >0 = take that many extra turns
    private final Die die = new Die();

    /**
     * Constructs a new GameMoves instance for the given list of logical players
     * @param logicPlayers List of logical players in the game
     */
    public GameMoves(ArrayList<Player> logicPlayers) {
        la           = new Layout(80, 50, logicPlayers);
        p            = new Build_Player(logicPlayers, la.height, la.width);
        currentPlayer = 0;
        resetTurnState();
    }

    /**
     * Draws the current game state onto the screen
     * @param gc The graphic context to render to
     */
    public void draw(GraphicsContext gc) {
        la.draw(gc);
        p.draw(gc);
        if (p.players[currentPlayer] != null &&
                p.players[currentPlayer].coin == 4) {

            gc.setFill(Color.WHITE);
            gc.fillRect(590, 100, 380, 130);

            gc.setFill(getPlayerColor(currentPlayer));
            gc.setFont(new Font("serif", 40));
            gc.fillText(p.players[currentPlayer].getName(), 600, 150);
            gc.fillText("Congratulations.",              600, 200);

            resetGame();
            return;
        }
        if (dice != 0 && p.players[currentPlayer] != null) {
            gc.setFill(Color.WHITE);
            gc.fillRect(590, 100, 380, 130);

            gc.setFill(getPlayerColor(currentPlayer));
            gc.setFont(new Font("serif", 40));
            gc.fillText(p.players[currentPlayer].getName(), 600, 150);
            gc.fillText("Number on dice is " + dice,       600, 200);
        }
        if (flag == 0 && dice != 0) {           // the move just finished
            if (bonusTurns == 0) {              // no more bonuses, next player
                nextPlayer();
            } else {                            // consume ONE bonus turn
                bonusTurns--;
                dice = 0;                       // force a new roll
            }
        }
    }

    /**
     * Handles key press events such as rolling the die when enter is pressed
     * @param e The key event to process
     */
    public void handleKeyPress(KeyEvent e) {
        if (p.players[currentPlayer] == null) {
            nextPlayer();
            return;
        }
        if (e.getCode().toString().equals("ENTER") && flag == 0) {

            dice       = die.roll();
            flag       = checkForPlayableMove();     // 1 if any pawn can move

            if (dice == 6) {
                bonusTurns++;                       // one bonus turn for a six
            }
        }
    }

    /**
     * Handles mouse click events to activate pawn movement if a legal move exists
     * @param e The mouse event to process
     */
    public void handleMouseClick(MouseEvent e) {
        if (flag != 1 || p.players[currentPlayer] == null) return;

        int x = (int) ((e.getX() - 80) / la.width);
        int y = (int) ((e.getY() - 50) / la.height);

        for (int i = 0; i < 4; i++) {
            Pawn currentPawn = p.players[currentPlayer].pawns[i];
            boolean canMoveFromHome = (currentPawn.current == -1 && dice == 6);
            boolean canMoveOnBoard  =
                    (currentPawn.current != -1 &&
                            currentPawn.current != 56 &&
                            (currentPawn.current + dice) <= 56);
            boolean isPawnClicked =
                    (currentPawn.current == -1 &&
                            x == Path.initialX[currentPlayer][i] &&
                            y == Path.initialY[currentPlayer][i]) ||
                            (currentPawn.current != -1 &&
                                    x == Path.ax[currentPlayer][currentPawn.current] &&
                                    y == Path.ay[currentPlayer][currentPawn.current]);
            if (isPawnClicked && canMoveFromHome) {
                currentPawn.current = 0;                 // enter the board
                flag = 0;
                break;
            } else if (isPawnClicked && canMoveOnBoard) {
                currentPawn.current += dice;
                if (currentPawn.current == 56) {
                    p.players[currentPlayer].coin++;
                }
                handleCollision(i);                      // may add another bonus
                flag = 0;
                break;
            }
        }
    }

    /** Returns 1 if any pawn of the active player can move with the current dice value. */
    private int checkForPlayableMove() {

        if (p.players[currentPlayer] == null) return 0;

        // normal moves
        for (int i = 0; i < 4; i++) {
            Pawn pawn = p.players[currentPlayer].pawns[i];
            if (pawn.current != -1 && pawn.current != 56 &&
                    pawn.current + dice <= 56) return 1;
        }

        // leaving home with a six
        if (dice == 6) {
            for (int i = 0; i < 4; i++) {
                if (p.players[currentPlayer].pawns[i].current == -1) return 1;
            }
        }
        return 0;
    }

    /** Sends any opponent located on the destination square back home
     and grants *one* bonus turn for a successful capture. */
    private void handleCollision(int movedPawnIndex) {
        int pos = p.players[currentPlayer].pawns[movedPawnIndex].current;

        // safe squares: multiples of 13 and 8 (home stretch squares), or inside finish area
        if ((pos % 13) == 0 || (pos % 13) == 8 || pos >= 51) return;
        for (int i = 0; i < p.players.length; i++) {
            if (i == currentPlayer || p.players[i] == null) continue;
            for (int j = 0; j < 4; j++) {
                Pawn enemy = p.players[i].pawns[j];
                if (enemy.x == Path.ax[currentPlayer][pos] &&
                        enemy.y == Path.ay[currentPlayer][pos]) {
                    enemy.current = -1;                  // send home
                    enemy.x       = Path.initialX[i][j];
                    enemy.y       = Path.initialY[i][j];
                    bonusTurns++;                        // one extra turn for the capture
                    return;
                }
            }
        }
    }

    /**
     * Switches to the next active player in the game
     * Skips over null entries, so nonexistent players
     */
    private void nextPlayer() {
        do {
            currentPlayer = (currentPlayer + 1) % p.players.length;
        } while (p.players[currentPlayer] == null);
        resetTurnState();
    }

    /**
     * Resets the state for a new player's turn
     * Clears the die roll, any move flags, and bonus turns
     */
    private void resetTurnState() {
        dice       = 0;
        flag       = 0;
        bonusTurns = 0;
    }

    /**
     * Resets the game entirely with the same players
     * Rebuilds the layout and resets turn state
     */
    private void resetGame() {
        la            = new Layout(80, 50, p.getLogicPlayers());
        currentPlayer = 0;
        resetTurnState();
    }

    /**
     * Returns the color associated with the given player index
     * @param player The index of the player
     * @return Color object representing the players color
     */
    private Color getPlayerColor(int player) {
        return switch (player) {
            case 0 -> Color.web("E76264");
            case 1 -> Color.web("719063");
            case 2 -> Color.web("E79A61");
            case 3 -> Color.web("9D61E6");
            default -> Color.BLACK;
        };
    }
}
