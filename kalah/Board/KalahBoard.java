package kalah.Board;

import kalah.Manager.GameManager;
import kalah.Manager.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KalahBoard {
    private final static List<Pit> houses = new ArrayList<Pit>();

    public KalahBoard(List<Player> players){
        generateBoard(players);
    }

    private void generateBoard(List<Player> players){
        for(int j = 0; j < players.size(); j++) {
            int houseNumber = 1;
            boolean isStore = false;
            Pit newHouse;
            for (int i = 0; i <= GameManager.NUMBER_OF_HOUSES; i++) {
                if(i == GameManager.NUMBER_OF_HOUSES) { // The last pit is a store
                    newHouse = new Store(players.get(j));
                } else {
                    newHouse = new House(players.get(j), houseNumber);
                }
                houses.add(newHouse);
                players.get(j).addHouse(newHouse); // Update a list of houses associated with the player
                houseNumber++;
            }
        }
    }
}



