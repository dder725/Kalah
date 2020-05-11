package kalah.Display;

import com.qualitascorpus.testsupport.IO;
import kalah.Board.KalahBoard;
import kalah.Manager.GameManager;

/**
 * This class provides functionality of displaying Kalah board in command line
 */
public class CLIBoardDisplay implements BoardDisplay {
    private final KalahBoard board;
    private final IO io;

    public CLIBoardDisplay(KalahBoard board, IO io) {
        this.board = board;
        this.io = io;
    }

    @Override
    public void displayBoard() {
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println(String.format("| P2 | 6[%2d] | 5[%2d] | 4[%2d] | 3[%2d] | 2[%2d] | 1[%2d] | %2d |",

                // Get seed stats for player 2
                GameManager.getPlayer(1).getHouse(5).getNumberOfSeeds(),
                GameManager.getPlayer(1).getHouse(4).getNumberOfSeeds(),
                GameManager.getPlayer(1).getHouse(3).getNumberOfSeeds(),
                GameManager.getPlayer(1).getHouse(2).getNumberOfSeeds(),
                GameManager.getPlayer(1).getHouse(1).getNumberOfSeeds(),
                GameManager.getPlayer(1).getHouse(0).getNumberOfSeeds(),

                // Get player 1 score
                GameManager.getPlayer(0).getScore()
        ));

        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println(String.format("| %2d | 1[%2d] | 2[%2d] | 3[%2d] | 4[%2d] | 5[%2d] | 6[%2d] | P1 |",
                // Get player 1 score
                GameManager.getPlayer(1).getScore(),

                // Get seed stats for player 1
                GameManager.getPlayer(0).getHouse(0).getNumberOfSeeds(),
                GameManager.getPlayer(0).getHouse(1).getNumberOfSeeds(),
                GameManager.getPlayer(0).getHouse(2).getNumberOfSeeds(),
                GameManager.getPlayer(0).getHouse(3).getNumberOfSeeds(),
                GameManager.getPlayer(0).getHouse(4).getNumberOfSeeds(),
                GameManager.getPlayer(0).getHouse(5).getNumberOfSeeds()));
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }
}