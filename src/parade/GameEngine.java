package parade;

import java.util.*;
import java.io.*;

public class GameEngine {

    /*
     * One game object -> 1 list of players + 1 deck of parade + 1 pDeck
     */
    private ArrayList<Player> players = new ArrayList<>();
    private int playercount;
    private ArrayList<Card> parade = new ArrayList<>();
    private pDeck pdeck = new pDeck();
    private boolean pvp = false; // Stores whether game is player-vs-player or player-vs-bot
    private boolean lastround = false;
    private final ConsoleUI ui;

    public GameEngine(ConsoleUI ui) {
        this.ui = ui;
    }

    public void startGame() {
        /**
         * Initializes and starts the game by performing the following setup:
         * 
         * 1. Prompts the user to input the number of players.
         * 2. Shuffles the deck before dealing cards.
         * 3. Creates player instances and deals each player.
         * 4. Places 6 cards in the middle of the table as the initial parade
         * participants.
         * 5. Closes the scanner to prevent resource leaks.
         */
        initiatizeGameMode();
        initiatizePlayers();

        // Create parade
        for (int i = 0; i < 6; i++) {
            parade.add(pdeck.issueCard());

        }

        gameManager();
    }

    public void initiatizeGameMode() {
        String mode = ui.promptGameMode();
        pvp = mode.equals("pvp");
    }

    public void initiatizePlayers() {
        playercount = ui.promptPlayerCount();
        System.out.println("");

        if (pvp) {
            // Instantiate players and issue cards - IF PVP
            for (int i = 0; i < playercount; i++) {
                Player temp = new Player();
                for (int j = 0; j < 5; j++) {
                    temp.drawCard(pdeck.issueCard());
                }

                players.add(temp);
            }
        } else {
            // Instantiate player and issue cards
            Player temp = new Player();
            for (int j = 0; j < 5; j++) {
                temp.drawCard(pdeck.issueCard());
            }

            players.add(temp);

            // Instantiate bots and issue cards
            for (int i = 0; i < playercount - 1; i++) {
                Bot temp2 = new Bot();
                for (int j = 0; j < 5; j++) {
                    temp2.drawCard(pdeck.issueCard());
                }
                players.add(temp2);
            }
        }
    }

    public void gameManager() {

        int currentPlayerTurn = 0;

        while (!gameEnd()) {
            if (currentPlayerTurn > players.size() - 1) {
                currentPlayerTurn = 0;

            }

            System.out.printf("%n%nPlayer %d Turn:%n", currentPlayerTurn + 1);
            playerTurn(players.get(currentPlayerTurn));
            currentPlayerTurn++;
        }

        lastround = true;

        for (int i = 0; i < players.size(); i++) {
            if (currentPlayerTurn > players.size() - 1) {
                currentPlayerTurn = 0;
            }

            ui.displayPlayerTurn(currentPlayerTurn + 1);
            ui.displayLastRound();
            playerTurn(players.get(currentPlayerTurn));
            currentPlayerTurn++;
        }

        // Tabulate Scores
        ui.displayFinalScores(players);
        ui.displayCollectedCards(players);
        Player winner = tabulateScores(players);
        ui.displayWinner(winner, players);
    }

    public void addCardToPlayer(Player player, Card card) {
        ArrayList<Card> theCollect = player.getCollected();
        theCollect.add(card);
    }

    public void playerTurn(Player player) {
        /*
         * 1. Take in the player/bot who's playing the current turn
         * 2. Give the player an option to select a card to put into the parade (Skipped
         * if its a Bot)
         * 3. Add the selected card to the parade
         * 4. Based on card.getNumber(), separate cards into Removal Section (0,
         * parade.size() - card.getNumber() - 1) and Untouched Section (parade.size() -
         * card.getNumber() - 1 onwards)
         * 5. Remove the cards within the removal section that has the same COLOUR or a
         * NUMBER lower or same as card.getNumber()
         * 6. Removed cards are added to the Player object, collected variable
         */

        // 2. Give the player an option to select a card to put into the parade
        Integer chosenIndex = null;

        if (player instanceof Bot bot) {
            ui.displayParade(parade);
            ui.displayCollectedCards(player);
            chosenIndex = bot.botTurn(parade); // pass in parade
            ui.displayBotChoice(player.getHand().get(chosenIndex));
        } else {
            ui.displayDeckRemaining(pdeck.getDeck().size());
            ui.displayParade(parade);
            ui.displayCollectedCards(player);
            ui.displayPlayerHand(player);
            chosenIndex = ui.promptCardSelection(player);
        }

        // Remove chosen card from player/bot and add to the Parade
        Card chosen = player.playCard(player.getHand().get(chosenIndex));
        parade.add(chosen);

        // Determine the number of cards in the removal section
        int removalSectionIndex = parade.size() - chosen.getNumber() - 1;

        // Use an iterator to safely remove elements while iterating
        Iterator<Card> iterParade = parade.iterator();
        int index = 0;

        while (iterParade.hasNext() && index < removalSectionIndex) {
            Card current = iterParade.next();

            if (current.getColor().equals(chosen.getColor()) || current.getNumber() <= chosen.getNumber()) {
                addCardToPlayer(player, current);
                iterParade.remove();
            }

            index++;
        }

        if (!lastround) {
            player.drawCard(pdeck.issueCard());
        }
    }

    public boolean gameEnd() {
        if (pdeck.getDeck().size() == 0) {
            return true;
        }

        for (Player player : players) {

            ArrayList<Card> currentCards = player.getCollected();
            HashSet<String> colorSet = new HashSet<>();

            for (Card card : currentCards) {
                colorSet.add(card.getColor()); // collect unique colors
            }

            if (colorSet.size() == 6) {
                return true; // No need to continue checking others
            }
        }

        return false;
    }

    public Player tabulateScores(List<Player> allPlayers) {
        Player winner = null;
        int lowestScore = Integer.MAX_VALUE;

        for (Player player : allPlayers) {
            int score = player.calculateScore(allPlayers);
            if (score < lowestScore) {
                lowestScore = score;
                winner = player;
            } else if (score == lowestScore) {
                if (winner.getCollected() != null && player.getCollected() != null
                        && winner.getCollected().size() > player.getCollected().size()) {
                    winner = player;
                }
            }
        }
        return winner;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getParade() {
        return parade;
    }

    public void setParade(ArrayList<Card> parade) {
        this.parade = parade;
    }

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();
        GameEngine session = new GameEngine(ui);
        session.startGame(); // Starts the game
    }
}
