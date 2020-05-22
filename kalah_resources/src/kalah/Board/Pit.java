package kalah.Board;

import kalah.Manager.Player;

public abstract class Pit {
    protected final Player owner;
    protected int seeds;

    protected Pit(Player owner, int seeds) {
        this.owner = owner;
        this.seeds = seeds;
    }

    /**
     * Place new seeds in this house
     **/
    public void sowSeeds(int seedsToSow) {
        this.seeds = this.seeds + seedsToSow;
    }


    /**
     * Query how many seeds are currently in the house
     *
     * @return The number of seeds in the house
     */
    public int getNumberOfSeeds() {
        return this.seeds;
    }

    public abstract int collectSeeds();

    public Player getOwner() {
        return this.owner;
    }
}
