package kalah.Display;

import com.qualitascorpus.testsupport.IO;
import kalah.Board.KalahBoard;

/**
 * This class provides functionality of displaying Kalah board in command line
 */
public class CLIBoardDisplay implements BoardDisplay{
    private final KalahBoard board;
    private final IO io;

    public CLIBoardDisplay(KalahBoard board, IO io){
        this.board = board;
        this.io = io;
    }

    @Override
    public void displayBoard(){
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
        io.println("|    |-------+-------+-------+-------+-------+-------|    |");
        io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
        io.println("+----+-------+-------+-------+-------+-------+-------+----+");
        io.println("Player 1's turn - Specify house number or 'q' to quit: ");
    }
}
