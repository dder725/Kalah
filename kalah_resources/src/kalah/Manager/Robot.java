package kalah.Manager;

import com.qualitascorpus.testsupport.IO;
import kalah.Board.House;
import kalah.Board.Pit;

public class Robot {
    private final Player player;
    private final Player opponent;
    private final int NUMBER_OF_HOUSES = GameManager.NUMBER_OF_HOUSES;

    public Robot(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        this.player.toggleRobotStatus();
    }

    private int chooseMove(IO io) {
        // Is there a house that leads to an additional move
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            if (NUMBER_OF_HOUSES - i == player.getHouse(i).getNumberOfSeeds()) {
                io.println(String.format("Player P2 (Robot) chooses house #%1d because it leads to an extra move", i + 1));
                return i + 1;
            }
        }
        // Is there a house that leads to a capture
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            int seeds = player.getHouse(i).getNumberOfSeeds();
            if (seeds != 0) {
                // The seeds lands on the player's board
                if ((seeds + i) % 13 < NUMBER_OF_HOUSES) {
                    Pit house = player.getHouse((((seeds + i) % 13)));
                    if (house instanceof House) {
                        if (house.getNumberOfSeeds() == 0) {
                            Pit opHouse = opponent.getHouse(((House) house).getOppositeHouseNumber());
                            // The opposite house has seeds in it OR the seeds fully wrap the opponents board on this turn
                            if (opHouse.getNumberOfSeeds() > 0 | (seeds + i) > NUMBER_OF_HOUSES) {
                                io.println(String.format("Player P2 (Robot) chooses house #%1d because it leads to a capture", i + 1));
                                return i + 1;
                            }
                        }
                    }
                }
            }
        }

        // Otherwise perform a legal move with a lowest-numbered house
        for (int i = 0; i < NUMBER_OF_HOUSES; i++) {
            if (player.getHouse(i).getNumberOfSeeds() != 0) {
                io.println(String.format("Player P2 (Robot) chooses house #%1d because it is the first legal move", i + 1));
                return i + 1;
            }
        }
        return NUMBER_OF_HOUSES + 1;
    }

    public int robotResponse(IO io) {
        return chooseMove(io);
    }
}