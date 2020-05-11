package kalah.Manager;

import com.qualitascorpus.testsupport.IO;
import kalah.Board.House;
import kalah.Board.KalahBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameManager {
    public static final int NUMBER_OF_HOUSES = 6;
    public static final int NUMBER_OF_PLAYERS = 2;
    private static final String INPUT_PROMPT = "Player P%d's turn - Specify house number or 'q' to quit: ";

    private final List<Player> players = new ArrayList<Player>();
    private KalahBoard board;
    private Player currentPlayer;


    public GameManager() {
        startGame();
    }

    /**
     * Set up the game for playing
     */
    public void startGame() {
        generatePlayers();
        board = new KalahBoard(players);
    }

    /**
     * Generate players
     */
    public void generatePlayers() {
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            players.add(new Player(i));
            currentPlayer = players.get(0);
        }
    }

    public KalahBoard getBoard() {
        return board;
    }

    public Player getPlayer(int playerIndex) {
        return players.get(playerIndex);
    }

    /**
     * Read user input for a current player
     *
     * @param io
     * @return User input as a string
     */
    public String getInput(IO io) {
        String playerInput = io.readFromKeyboard(String.format(INPUT_PROMPT, currentPlayer.getPlayerNumber())).trim();
        if(playerInput.equals("q")){
            return "q";
        }
        if (isValidInput(playerInput)) {
            return playerInput;
        } else {
            io.println("Invalid input");
            return "i"; // Return 'i' character to notify of an invalid input
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    private boolean isValidInput(String input) throws NumberFormatException {
        int inputNum = Integer.valueOf(input);
        try {
            return inputNum <= NUMBER_OF_HOUSES && inputNum > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Perform a move specified by the current player
     *
     * @param input - the move to perform
     */
    public void move(String input, IO io) {
        int startingHouse = Integer.valueOf(input);
        House currentHouse = currentPlayer.getHouse(startingHouse);
        int seedsToSow = currentPlayer.getHouse(startingHouse - 1).collectSeeds();
        // If the chosen house is empty the current player gets another go
        if (seedsToSow == 0) {
            io.println("House is empty. Move again.");
            move(getInput(io), io);
        }

        Player playerToUpdate = currentPlayer;
        // Move until there are no more seeds to sow
        while (seedsToSow != 0) {
            for (int i = startingHouse; i <= NUMBER_OF_HOUSES; i++) {
                if (seedsToSow > 0) {
                    playerToUpdate.getHouse(i).sowSeeds(1);
                    seedsToSow--;

                    if (playerToUpdate.getHouse(i).isStore()) {
                        playerToUpdate.updateScore(1);
                    }
                }

                currentHouse = playerToUpdate.getHouse(i); // Keep track of the house we are currently at
            }
            if (seedsToSow != 0) {
                playerToUpdate = switchPlayer(playerToUpdate); // Switch to the other player's houses
                startingHouse = 0;
            }

        }
        if (!(currentHouse.isStore() && currentHouse.getOwner().equals(currentPlayer))){
            this.currentPlayer = switchPlayer(this.currentPlayer);
        }
    }

    private Player switchPlayer(Player playerToSwitch) {
        if (playerToSwitch.getPlayerNumber() == 1) {
            return players.get(1);
        } else {
            return players.get(0);
        }
    }


}
