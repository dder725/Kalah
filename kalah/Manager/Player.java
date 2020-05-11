package kalah.Manager;

import kalah.Board.House;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Player {
    private final int playerNumber;
    private int score;
    private final List<House> playerHouses = new ArrayList<House>();
    public Player(int number){
        this.playerNumber = number;
        this.score = 0;
    }

    public int getScore(){
        return this.score;
    }

    public void updateScore(int incrementBy){
        this.score = this.score + incrementBy;
    }

    /**
     * Assign a house to this player
     * @param houseToAdd
     */
    public void addHouse(House houseToAdd){
        playerHouses.add(houseToAdd);
    }

    public House getHouse(int houseIndex){
        return playerHouses.get(houseIndex);
    }

    public int getPlayerNumber(){
        return this.playerNumber;
    }
}
