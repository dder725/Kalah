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
    private static final String SCORE = "\tplayer %d:%d";
    private static final String RESULTS = "Player %d wins!";
    private static final String TIE = "A tie!";


    private final List<Player> players = new ArrayList<Player>();
    private KalahBoard board;
    private Player currentPlayer;
    private Player opponent;

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
        // If the current player has no more seeds, finish the game
        if (!playerHasSeeds(currentPlayer)) {
            return "f";
        }
        String playerInput = io.readFromKeyboard(String.format(INPUT_PROMPT, currentPlayer.getPlayerNumber())).trim();

        if (playerInput.equals("q")) {
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
        int seedsToSow = currentPlayer.getHouse(startingHouse - 1).collectSeeds();
        Player opponent = switchPlayer(currentPlayer);
        House currentHouse = currentPlayer.getHouse(startingHouse);
        // If the chosen house is empty the current player gets another go
        if (seedsToSow == 0) {
            io.println("House is empty. Move again.");
            return;
        }

        Player playerToUpdate = currentPlayer;
        // Move until there are no more seeds to sow
        while (seedsToSow != 0) {
            for (int i = startingHouse; i <= NUMBER_OF_HOUSES; i++) {
                if (seedsToSow > 0) {
                    currentHouse = playerToUpdate.getHouse(i); // Keep track of the house we are currently at
                    if (!(currentHouse.isStore() && currentHouse.getOwner().equals(opponent))) { // Do not sow seeds in the opponent's store
                        currentHouse.sowSeeds(1);
                        seedsToSow--;
                        if (playerToUpdate.getHouse(i).isStore()) {
                            playerToUpdate.updateScore(1);
                        }
                    }
                }
            }
            if (seedsToSow != 0) {
                playerToUpdate = switchPlayer(playerToUpdate); // Switch to the other player's houses
                startingHouse = 0;
            }
        }

        // Check if can capture the opponents house
        if (!currentHouse.isStore() &&
                currentHouse.getOwner().equals(currentPlayer) &&
                currentHouse.getNumberOfSeeds() == 1
                && countSeedsInOppositeHouse(opponent, currentHouse) > 0) {
            captureHouse(currentPlayer, opponent, currentHouse);
        }

        // Give turn to the next player
        if (!(currentHouse.isStore() && currentHouse.getOwner().equals(currentPlayer))) {
            this.currentPlayer = switchPlayer(this.currentPlayer);
        }
    }

    /**
     * Switch the player to the opponent
     *
     * @param playerToSwitch
     * @return
     */
    private Player switchPlayer(Player playerToSwitch) {
        if (playerToSwitch.getPlayerNumber() == 1) {
            return players.get(1);
        } else {
            return players.get(0);
        }
    }

    /**
     * Capture seed from the opponents house and move them to the current player's store
     *
     * @param currentPlayer
     * @param opponent
     * @param currentHouse
     */
    private void captureHouse(Player currentPlayer, Player opponent, House currentHouse) {
        int capturedSeeds = opponent.getHouse(currentHouse.getOppositeHouseNumber()).collectSeeds() + currentHouse.collectSeeds();
        currentPlayer.getStore().sowSeeds(capturedSeeds);
        currentPlayer.updateScore(capturedSeeds);
    }

    private int countSeedsInOppositeHouse(Player opponent, House currentHouse) {
        return opponent.getHouse(currentHouse.getOppositeHouseNumber()).getNumberOfSeeds();
    }

    /**
     * Check if both players still have seeds
     *
     * @return
     */
    public boolean playerHasSeeds(Player currentPlayer) {
        if (currentPlayer.hasSeedsLeft()) {
            return true;
        }
        return false;
    }

    /**
     * Finalize and display the score
     *
     * @param io
     */
    public void printScore(IO io) {
        for (Player player : players) {
            player.collectRemainingSeeds();
            io.println(String.format(SCORE, player.getPlayerNumber(), player.getScore()));
        }
    }

    /**
     * Print results
     */
    public void printResults(IO io) {
        if (players.get(0).getScore() > players.get(1).getScore()) {
            io.println(String.format(RESULTS, 1));
        } else if (players.get(0).getScore() == players.get(1).getScore()) {
            io.println(TIE);
        } else {
            io.println(String.format(RESULTS, 2));
        }
    }
}
