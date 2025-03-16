import java.util.*;
import java.io.*;
import java.util.Random;

public class GameEngine {

    /*
     * let's create the decks first~
     */
    private ArrayList<ArrayList<Card>> deck;
    private Player parade;
    /*
     * I realised a problem with the draw card method, what about the cards in the
     * parade, how would that be considered?
     * then I realised that If I went and create a player called parade, then the
     * problem would probably be solved,
     * and when we count the points for the winnder, we aimply leave out counting
     * player parade.
     */

    public ArrayList<Card> createSubDeck(String colour) {
        ArrayList<Card> temp = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            // make new Card with value, for "player" we put null as we have not assigned
            // card to player yet
            Card current = new Card(i, colour, false, null);
            temp.add(current);
        }

        return temp;
    }

    public void createDeck() {
        ArrayList<Card> red = createSubDeck("red");
        deck.add(red);
        ArrayList<Card> blue = createSubDeck("blue");
        deck.add(blue);
        ArrayList<Card> green = createSubDeck("green");
        deck.add(green);
        ArrayList<Card> purple = createSubDeck("purple");
        deck.add(purple);
        ArrayList<Card> grey = createSubDeck("grey");
        deck.add(grey);
        ArrayList<Card> orange = createSubDeck("orange");
        deck.add(orange);
    }

    public boolean checkIfGotCardLeft() {
        boolean gotCardLeft = false;
        Card current = null;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 11; j++) {
                current = (deck.get(i)).get(j);
                if (current.getOwner() == null) {
                    gotCardLeft = true;
                }
            }
        }

        return gotCardLeft;
    }

    public Card drawCard() {
        if (!(checkIfGotCardLeft())) {
            System.out.println("No Cards Left! Returning NULL!");
            return null;
        }
        // create instance of random class
        Random rand = new Random();
        boolean isOwnedByPlayer = true;
        Card drawn = null;

        do {
            int randColour = rand.nextInt(6);
            /*
             * doing this will generate a random int from 0 to 5, hence we have to add 1 so
             * that the range is instead 1 to 6;
             * wait......
             * I just realised, for a set of six colours, the index start from 0, so..... do
             * not need to add 1.
             */
            int randValue = rand.nextInt(11);
            // this will generate a random value from 0 to 10
            drawn = (deck.get(randColour)).get(randValue);
            if (drawn.getOwner() == null) {
                isOwnedByPlayer = false;
            }
        } while (isOwnedByPlayer);
        /*
         * we will get a card at least once, if the card isNotOwnedByPlayer we can stop
         * the loop and simply return that card
         * else, we will continue the "drawing" process
         */

        return drawn;
    }

    public boolean have6Colours(Player temp) {
        ArrayList<Card> cardOwned = temp.getCardsOwner();
        int len = cardOwned.size();
        boolean haveAllColours = false;
        boolean haveRed = false;
        boolean haveGreen = false;
        boolean haveBlue = false;
        boolean havePurple = false;
        boolean haveGrey = false;
        boolean haveOrange = false;
        for (int i1 = 0; i1 < len; i1++) {
            Card current1 = cardOwned.get(i1);
            if ((current1.getColour()).equals("red")) {
                haveRed = true;
            }
            if ((current1.getColour()).equals("green")) {
                haveGreen = true;
            }
            if ((current1.getColour()).equals("blue")) {
                haveBlue = true;
            }
            if ((current1.getColour()).equals("purple")) {
                havePurple = true;
            }
            if ((current1.getColour()).equals("grey")) {
                haveGrey = true;
            }
            if ((current1.getColour()).equals("orange")) {
                haveOrange = true;
            }
        }

        haveAllColours = (haveRed && haveBlue && haveGreen && havePurple && haveGrey && haveOrange);

        return haveAllColours;
    }
}
