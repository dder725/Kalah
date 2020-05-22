package kalah.Display;

import com.qualitascorpus.testsupport.IO;
import kalah.Board.KalahBoard;
import kalah.Manager.GameManager;

public class VerticalBoardDisplay implements BoardDisplay {

    private final KalahBoard board;
    private final IO io;

    public VerticalBoardDisplay(KalahBoard board, IO io) {
        this.board = board;
        this.io = io;
    }


    @Override
    public void displayBoard(GameManager gameManager) {
        io.println("+---------------+");
        io.println(String.format("|       | P2 %2d |", gameManager.getPlayer(1).getScore()));
        io.println("+-------+-------+");
        io.println(String.format("| 1[%2d] | 6[%2d] |",
                gameManager.getPlayer(0).getHouse(0).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(5).getNumberOfSeeds()));
        io.println(String.format("| 2[%2d] | 5[%2d] |",
                gameManager.getPlayer(0).getHouse(1).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(4).getNumberOfSeeds()));
        io.println(String.format("| 3[%2d] | 4[%2d] |",
                gameManager.getPlayer(0).getHouse(2).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(3).getNumberOfSeeds()));
        io.println(String.format("| 4[%2d] | 3[%2d] |",
                gameManager.getPlayer(0).getHouse(3).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(2).getNumberOfSeeds()));
        io.println(String.format("| 5[%2d] | 2[%2d] |",
                gameManager.getPlayer(0).getHouse(4).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(1).getNumberOfSeeds()));
        io.println(String.format("| 6[%2d] | 1[%2d] |",
                gameManager.getPlayer(0).getHouse(5).getNumberOfSeeds(),
                gameManager.getPlayer(1).getHouse(0).getNumberOfSeeds()));
        io.println("+-------+-------+");
        io.println(String.format("| P1 %2d |       |", gameManager.getPlayer(0).getScore()));
        io.println("+---------------+");

    }
}
