package kalah.Board;

import kalah.Manager.GameManager;
import kalah.Manager.Player;

public class House extends Pit {
    private static final int initialSeeds = 4;
    private final int houseNumber;

    public House(Player owner, int houseNumber) {
        super(owner, initialSeeds);
        this.houseNumber = houseNumber;
    }


    /*
    Collect all seeds from this house
     */
    public int collectSeeds() {
        int seedsToSow = super.seeds;
        super.seeds = 0;
        return seedsToSow;
    }

    /*
    Get the number of the opposite house
     */
    public int getOppositeHouseNumber() {
        return (GameManager.NUMBER_OF_HOUSES) - this.houseNumber;
    }

}