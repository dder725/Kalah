package kalah.Board;

import kalah.Manager.Player;

public class Store extends Pit{
    public Store(Player owner) {
        super(owner, 0);
    }

    /**
     * Cannot collect seeds from a store
     * @return
     */
    public int collectSeeds(){
        return 0;
    }

}
