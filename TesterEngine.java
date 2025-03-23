import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TesterEngine {
    public static void main(String[] args) {
        pDeck deck = new pDeck();
        GameEngine game = new GameEngine();

        System.out.println("-------Test 1: issueCard()-------");
        System.out.printf("Starting number of cards: %s%n", deck.getDeck().size());
        System.out.printf("Issued card: %s%n", deck.issueCard());
        System.out.printf("Ending number of cards: %s%n", deck.getDeck().size());
        System.out.println("---------------------------------");

        System.out.println("");
        System.out.println("");

        System.out.println("-------Test 2: startGame()-------");
        System.out.println("Please key in 4 players");
        game.startGame();
        System.out.printf("Total number of players: %d%n", game.getPlayers().size());
        System.out.println("Player 1 Hand: " + game.getPlayers().get(0).getHand());
        System.out.println("Player 2 Hand: " + game.getPlayers().get(1).getHand());
        System.out.println("Player 3 Hand: " + game.getPlayers().get(2).getHand());
        System.out.println("Player 4 Hand: " + game.getPlayers().get(3).getHand());
        System.out.println("Parade: " + game.getParade());
        System.out.println("---------------------------------");

        System.out.println("");
        System.out.println("");

        System.out.println("-------Test 3: playerTurn()-------");
        Player player = new Player();
        player.drawCard(new Card(1, "red"));
        player.drawCard(new Card(2, "red"));
        player.drawCard(new Card(3, "red"));
        player.drawCard(new Card(4, "red"));
        player.drawCard(new Card(5, "red"));

        game.setParade(new ArrayList<Card>() {{
            add(new Card(0, "red"));
            add(new Card(3, "blue"));
            add(new Card(2, "red"));
            add(new Card(1, "purple"));
            add(new Card(0, "yellow"));
            add(new Card(7, "red"));
            add(new Card(4, "grey"));
        }});

        System.out.printf("Chosen Player: %s%n", player);
        System.out.printf("Hand: %s%n", player.getHand());
        System.out.println("Parade: " + game.getParade());
        Card playedCard = Collections.min(player.getHand(), Comparator.comparingInt(Card::getNumber));
        System.out.println("Please lay out card " + playedCard);
        game.playerTurn(player);
        System.out.println("");
        System.out.println("---------Test 3.1: Parade--------");
        // ArrayList<Card> tempList = new ArrayList<>(game.getParade().subList(Math.max(0, game.getParade().size() - (playedCard.getNumber())), game.getParade().size()));
        // ArrayList<Card> expectres31 = new ArrayList<>(game.getParade().subList(0, Math.max(0, game.getParade().size() - (playedCard.getNumber()))));
        // ArrayList<Card> expectres32 = new ArrayList<>(expectres31.stream()
        // .filter(card -> card.getColor().equals(playedCard.getColor()) || card.getNumber() < playedCard.getNumber())
        // .toList());
        // expectres31.addAll(tempList);
        // expectres31.add(playedCard);
        // expectres31.removeAll(expectres32);

        // System.out.println("Expected Parade: " + expectres31);
        System.out.println("Expected Parade: [blue 3, grey 4, red 1]");
        System.out.println("Actual Parade: " + game.getParade());
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("-------Test 3.2: Collected-------");
        // System.out.println("Expected Collected: " + expectres32);
        System.out.println("Expected Collected: [red 0, red 2, purple 1, yellow 0, red 7]");
        System.out.println("Actual Collected: " + player.getCollected());
        System.out.println("---------------------------------");







    }
}
