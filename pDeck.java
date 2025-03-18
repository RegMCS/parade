import java.util.ArrayList;
import java.util.Collections;

public class pDeck {
    private ArrayList<Card> deck;

    public pDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getParadeDeck() {
        return deck;
    }

    public int getNumberOfCards() {
        return deck.size();
    }

  
    // Added shuffle deck method
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }



    /**
     * Issues a Card object from the top of the deck.
     * Added isEmpty method to check if the deck is empty
     * @return Card object
     */
    public Card issueCard() {
        if (deck.isEmpty()) {
        return null; // or throw an exception
    }
        return deck.remove(deck.size() - 1);
    }
}