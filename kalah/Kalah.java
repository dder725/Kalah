package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Board.KalahBoard;
import kalah.Display.BoardDisplay;
import kalah.Display.CLIBoardDisplay;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		// Replace what's below with your implementation
		KalahBoard board = new KalahBoard();
		BoardDisplay printer = new CLIBoardDisplay(board, io);
		printer.displayBoard();
	}
}
