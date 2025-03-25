package parade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class pDeck {
    private ArrayList<Card> deck = new ArrayList<>();

    public pDeck() {
        List<String> colors = new ArrayList<>(Arrays.asList("blue", "green", "red", "purple", "grey", "orange"));
        for (String color : colors) {
            for (int i = 0; i < 11; i++) {
                deck.add(new Card(i, color));
            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Issues a Card object from the top of the deck.
     * Added isEmpty method to check if the deck is empty
     * 
     * @return Card object
     */
    public Card issueCard() {
        if (deck.isEmpty()) {
            return null; // or throw an exception
        }
        return deck.remove(deck.size() - 1);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}