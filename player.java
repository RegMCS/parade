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
    

    // // this is if we are letting the user input an index for selection of card
    // public void playCard(int index, pDeck paradeDeck) {
    //     if (index < 0 || index >= hand.size()) {
    //         throw new IllegalArgumentException("Invalid card selection! Choose a valid card index.");
    //     }
    
    //     // remove selected card from hand and place it at the end of the parade
    //     Card playedCard = hand.remove(index);

    //     paradeDeck.addToParade(playedCard); // add a card to the parade //TODO: create an addToParade method
    
    //     // remove necessary cards from the parade based on game rules
    //     ArrayList<Card> removedCards = paradeDeck.removeCards(playedCard); //TODO: create a removeCards method
    //     collectCards(removedCards);
    // }

    /**
     * add removed cards to the player's collected pile.
     */
//     public void collectCards(ArrayList<Card> cards) {
//         for (Card card : cards) {
//             card.setOwner(this);  // set the card’s owner to this player
//             collected.add(card);
//         }
//     }

//     /**
//      * allows the player to draw a new card from the draw pile (if not the last round)
//      */
    public void drawCard(Card card) {
        hand.add(card);
    }

//     /**
//      * counts how many cards of a specific color the player has in the collected pile
//      */
//     public int countColor(String color) {
//         int count = 0;
//         for (Card card : collected) {
//             if (card.getColor().equals(color)) {
//                 count++;
//             }
//         }
//         return count;
//     }

//     /**
//      * calculates the player's final score based on game rules
//      * @param allPlayers - list of all players to determine majority for each color
//      * @return total score
//      */
//     public int calculateScore(List<Player> allPlayers) {
//         int totalScore = 0;

//         for (Card card : collected) {
//             String color = card.getColor();
//             int colorCount = countColor(color); // get the number of this color the player has

//             // check if this player has the majority for this color
//             boolean hasMajority = true;
//             for (Player otherPlayer : allPlayers) {
//                 if (otherPlayer != this && otherPlayer.countColor(color) >= colorCount) {
//                     hasMajority = false; // another player has the same or more
//                     break;
//                 }
//             }

//             // scoring rules
//             if (hasMajority) {
//                 totalScore += 1;  // majority color cards are worth 1 point each
//             } else {
//                 totalScore += card.getNumber();  // otherwise, use printed value on card
//             }
//         }

//         return totalScore;
//     }
}
