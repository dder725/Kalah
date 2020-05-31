package kalah.Manager;

import kalah.Board.Pit;
import kalah.Board.Store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class

Player {
    private final int playerNumber;
    private int score;
    private boolean isRobot = false;
    private final List<Pit> playerHouses = new ArrayList<Pit>();

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
    public void addHouse(Pit houseToAdd) {
        playerHouses.add(houseToAdd);
    }

    public Pit getHouse(int houseNumber) {
        return playerHouses.get(houseNumber);
    }

    public Pit getStore() {
        return playerHouses.get(GameManager.NUMBER_OF_HOUSES);
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    /**
     * Check if this player has any seeds left to continue playing
     *
     */
    public boolean hasSeedsLeft(){
        for(Pit house: playerHouses){
            if(house.getNumberOfSeeds() > 0 && !(house instanceof Store)){
                return true;
            }
        }
        return false;
    }

    /**
     * Collect seeds from the remaining houses at the end of the game
     */
    public void collectRemainingSeeds(){
        for(Pit house: playerHouses){
            int remainingSeeds = house.collectSeeds();
            getStore().sowSeeds(remainingSeeds);
            this.score = getStore().getNumberOfSeeds();
        }
    }

    /**
     * Change the robot flag of this player (signifies a computer-driven player)
     */
    public void toggleRobotStatus(){
        this.isRobot = !this.isRobot;
    }

    public boolean isRobot(){
        return isRobot;
    }
}
