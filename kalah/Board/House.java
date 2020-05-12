package kalah.Board;

import kalah.Manager.GameManager;
import kalah.Manager.Player;

public class House {
    private static final int initialSeeds = 4;
    private final int houseNumber;
    private final boolean isStore;
    private final Player owner;
    private int seeds;

    public House(Player owner, int houseNumber, boolean isStore) {
        this.owner = owner;
        this.houseNumber = houseNumber;
        this.isStore = isStore;
        if(this.isStore){
            this.seeds = 0;
        } else {
            this.seeds = initialSeeds;
        }
    }

    /*
    Place new seeds in this house
     */
    public void sowSeeds(int seedsToSow){
        this.seeds = this.seeds + seedsToSow;
    }

    /*
    Collect all seeds from this house
     */
    public int collectSeeds(){
        int seedsToSow = seeds;
        seeds = 0;
        return seedsToSow;
    }

    /*
    Get the number of the opposite house
     */
    public int getOppositeHouseNumber(){
        return ( GameManager.NUMBER_OF_HOUSES) - this.houseNumber;
    }

    /**
     * Query how many seeds are currently in the house
     * @return The number of seeds in the house
     */
    public int getNumberOfSeeds(){
        return this.seeds;
    }

    public boolean isStore(){
        return isStore;
    }

    public Player getOwner() {
        return owner;
    }
}