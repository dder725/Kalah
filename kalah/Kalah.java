package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Board.KalahBoard;
import kalah.Display.BoardDisplay;
import kalah.Display.CLIBoardDisplay;
import kalah.Manager.GameManager;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
    public static void main(String[] args) {
        new Kalah().play(new MockIO());
    }

    public void play(IO io) {
        GameManager gameManager = new GameManager();
        BoardDisplay printer = new CLIBoardDisplay(gameManager.getBoard(), io);
        printer.displayBoard(gameManager);

        String playerInput = gameManager.getInput(io);

        while (!playerInput.equals("q") && !playerInput.equals("f")) {
            gameManager.move(playerInput, io);
            printer.displayBoard(gameManager);
            playerInput = gameManager.getInput(io);
        }

        io.println("Game over");
        printer.displayBoard(gameManager);
        if(playerInput.equals("f")) {
            gameManager.printScore(io);
            gameManager.printResults(io);
        }
    }
}
