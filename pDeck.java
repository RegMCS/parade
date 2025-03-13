class pDeck {
    private ArrayList<Card> deck;

    public pDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public getParadeDeck() {
        return deck;
    }

    public getNumberOfCards() {
        return deck.size();
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Issues a Card object from the top of the deck.
     * 
     * @return Card object
     */
    public Card issueCard() {
        return deck.remove(deck.size() - 1);
    }
}