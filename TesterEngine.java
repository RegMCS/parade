public class TesterEngine {
    public static void main(String[] args) {
        pDeck deck = new pDeck();
        GameEngine game = new GameEngine();

        System.out.println("-------Test 1: issueCard()-------");
        System.out.printf("Starting number of cards: %s%n", deck.getNumberOfCards());
        System.out.printf("Issued card: %s%n", deck.issueCard());
        System.out.printf("Ending number of cards: %s%n", deck.getNumberOfCards());
        System.out.println("---------------------------------");

        System.out.println("");
        System.out.println("");


        System.out.println("-------Test 2: startGame()-------");
        System.out.printf("Please key in 4 players%n");
        game.startGame();
        System.out.printf("Total number of players: %d%n", game.getPlayers().size());
        System.out.println("Player 1 Hand: " + game.getPlayers().get(0).getHand());
        System.out.println("Player 2 Hand: " + game.getPlayers().get(1).getHand());
        System.out.println("Player 3 Hand: " + game.getPlayers().get(2).getHand());
        System.out.println("Player 4 Hand: " + game.getPlayers().get(3).getHand());
        System.out.println("Parade: " + game.getParade());
        System.out.println("---------------------------------");      

    }
}
