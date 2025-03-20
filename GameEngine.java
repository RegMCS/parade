import java.util.*;
import java.io.*;

public class GameEngine {

    /*
     * One game object -> 1 list of players + 1 deck of parade + 1 pDeck
     */
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> parade = new ArrayList<>();
    private pDeck pdeck = new pDeck();

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

        Scanner sc = new Scanner(System.in);
        System.out.print("Please input number of players: ");
        Integer playercount = sc.nextInt();
        System.out.println("");

        // Instantiate players and issue cards
        for (int i = 0; i < playercount; i++) {
            Player temp = new Player();
            for (int j = 0; j < 5; j++) {
                temp.drawCard(pdeck.issueCard());
            }

            players.add(temp);
        }

        // Create parade
        for (int i = 0; i < 6; i++) {
            parade.add(pdeck.issueCard());
        }

        sc.close();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getParade() {
        return parade;
    }

    // public ArrayList<Card> createSubDeck(String colour) {
    // ArrayList<Card> temp = new ArrayList<>();
    // for (int i = 0; i < 11; i++) {
    // // make new Card with value, for "player" we put null as we have not assigned
    // // card to player yet
    // Card current = new Card(i, colour, false, null);
    // temp.add(current);
    // }

    // return temp;
    // }

    // public boolean have6Colours(Player temp) {
    // ArrayList<Card> cardOwned = temp.getCardsOwner();
    // int len = cardOwned.size();
    // boolean haveAllColours = false;
    // boolean haveRed = false;
    // boolean haveGreen = false;
    // boolean haveBlue = false;
    // boolean havePurple = false;
    // boolean haveGrey = false;
    // boolean haveOrange = false;
    // for (int i1 = 0; i1 < len; i1++) {
    // Card current1 = cardOwned.get(i1);
    // if ((current1.getColour()).equals("red")) {
    // haveRed = true;
    // }
    // if ((current1.getColour()).equals("green")) {
    // haveGreen = true;
    // }
    // if ((current1.getColour()).equals("blue")) {
    // haveBlue = true;
    // }
    // if ((current1.getColour()).equals("purple")) {
    // havePurple = true;
    // }
    // if ((current1.getColour()).equals("grey")) {
    // haveGrey = true;
    // }
    // if ((current1.getColour()).equals("orange")) {
    // haveOrange = true;
    // }
    // }

    // haveAllColours = (haveRed && haveBlue && haveGreen && havePurple && haveGrey
    // && haveOrange);

    // return haveAllColours;
    // }
}
