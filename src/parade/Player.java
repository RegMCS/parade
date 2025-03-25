package parade;

import java.util.*;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> collected; 

    /**
     * initialise the player with empty hand and collected pile
     */
    public Player() {
        this.hand = new ArrayList<>();
        this.collected = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getCollected() {
        return collected;
    }
    
    public void drawCard(Card card) {
        hand.add(card);
    }

    public Card playCard(Card targetCard) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(targetCard)) {
                return hand.remove(i);
            }
        }
        return null;
    }

//     /**
//      * counts how many cards of a specific color the player has in the collected pile
//      */
    public int countColor(String color) {
        int count = 0;
        for (Card card : collected) {
            if (card.getColor().equals(color)) {
                count++;
            }
        }
        return count;
    }

    public int calculateScore(List<Player> allPlayers) {
        int totalScore = 0;
        for (Card card : collected) {
            String color = card.getColor();
            int colorCount = countColor(color); // How many of this color the player has

            // Determine the highest number of this color owned by any player
            int maxColorCount = colorCount; // Assume current player has the highest count
            for (Player otherPlayer : allPlayers) {
                if (otherPlayer != this) {
                    maxColorCount = Math.max(maxColorCount, otherPlayer.countColor(color));
                }
            }

            // If the player has the highest count (or is tied for highest), they have the
            // majority
            boolean hasMajority = colorCount >= maxColorCount;
            if(allPlayers.size() == 2){
            hasMajority = colorCount > maxColorCount + 1;
            }

            // Scoring rules
            if (hasMajority) {
                totalScore += 1; // Majority color cards are worth 1 point each
            } else {
                totalScore += card.getNumber(); // Otherwise, use printed value on card
            }
        }
        return totalScore;
    }
}
