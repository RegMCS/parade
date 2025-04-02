package parade;

import java.util.*;

public class TesterBot {
    public static void main(String[] args) {
        testHighestNumberSelection();
        testLeastMatchingColor();
        testLeastMatchingColor2();
    }

    public static void testHighestNumberSelection() {
        Bot bot = new Bot();

        // Bot's hand: highest number is 8 (should choose index 2)
        bot.drawCard(new Card(3, "blue"));
        bot.drawCard(new Card(5, "green"));
        bot.drawCard(new Card(8, "red")); // <-- highest

        List<Card> parade = Arrays.asList(
                new Card(2, "green"),
                new Card(5, "blue")
        );

        int chosenIndex = bot.botTurn(parade);
        Card chosenCard = bot.getHand().get(chosenIndex);

        System.out.println("=== Test: Highest Number Selection ===");
        System.out.println("Bot's hand: " + bot.getHand());
        System.out.println("Expected: red 8");
        System.out.println("Bot chose: " + chosenCard);
        System.out.println(chosenCard.getNumber() == 8 ? "Passed\n" : "Failed\n");
    }

    public static void testLeastMatchingColor() {
        Bot bot = new Bot();

        // All cards have same number → bot should choose least represented color
        bot.drawCard(new Card(3, "blue"));   // in parade 2x
        bot.drawCard(new Card(3, "green"));  // in parade 1x
        bot.drawCard(new Card(3, "orange")); // not in parade → should be chosen

        List<Card> parade = Arrays.asList(
                new Card(1, "blue"),
                new Card(4, "green"),
                new Card(5, "blue"),
                new Card(7, "red")
        );

        int chosenIndex = bot.botTurn(parade);
        Card chosenCard = bot.getHand().get(chosenIndex);

        System.out.println("=== Test: Least Matching Color ===");
        System.out.println("Parade: " + parade);
        System.out.println("Bot's hand: " + bot.getHand());
        System.out.println("Expected: orange 3");
        System.out.println("Bot chose: " + chosenCard);
        System.out.println(chosenCard.getColor().equals("orange") ? "Passed\n" : "Failed\n");
    }

    public static void testLeastMatchingColor2() {
        Bot bot = new Bot();

        // All cards have same number → bot should choose least represented color
        bot.drawCard(new Card(3, "blue"));   // in parade 2x
        bot.drawCard(new Card(3, "green"));  // in parade 1x
        bot.drawCard(new Card(3, "orange")); // in parade 1x
        List<Card> parade = Arrays.asList(
                new Card(1, "blue"),
                new Card(4, "green"),
                new Card(5, "blue"),
                new Card(7, "orange")
        );

        int chosenIndex = bot.botTurn(parade);
        Card chosenCard = bot.getHand().get(chosenIndex);

        System.out.println("=== Test: Least Matching Color ===");
        System.out.println("Parade: " + parade);
        System.out.println("Bot's hand: " + bot.getHand());
        System.out.println("Expected: green 3");
        System.out.println("Bot chose: " + chosenCard);
        System.out.println(chosenCard.getColor().equals("green") ? "Passed\n" : "Failed\n");
    }
}