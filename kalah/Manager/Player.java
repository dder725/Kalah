package kalah.Manager;

import kalah.Board.House;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {
    private final int playerNumber;
    private int score;
    private final List<House> playerHouses = new ArrayList<House>();

    public Player(int number) {
        this.playerNumber = number;
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void updateScore(int incrementBy) {
        this.score = this.score + incrementBy;
    }

    /**
     * Assign a house to this player
     *
     * @param houseToAdd
     */
    public void addHouse(House houseToAdd) {
        playerHouses.add(houseToAdd);
    }

    public House getHouse(int houseNumber) {
        return playerHouses.get(houseNumber);
    }

    public House getStore() {
        return playerHouses.get(GameManager.NUMBER_OF_HOUSES);
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    /**
     * Check if this player has any seeds left to continue playing
     * @return
     */
    public boolean hasSeedsLeft(){
        for(House house: playerHouses){
            if(house.getNumberOfSeeds() > 0 && !house.isStore()){
                return true;
            }
        }
        return false;
    }

    /**
     * Collect seeds from the remaining houses at the end of the game
     */
    public void collectRemainingSeeds(){
        for(House house: playerHouses){
            int remainingSeeds = house.collectSeeds();
            getStore().sowSeeds(remainingSeeds);
            this.score = getStore().getNumberOfSeeds();
        }
    }
}
