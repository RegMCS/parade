package parade;

import java.util.*;

public class ConsoleUI {
    private Scanner sc = new Scanner(System.in);

    
    public String promptGameMode() {
        while (true) {
            System.out.print("Please indicate player-vs-computer or player-vs-player [pvc/pvp]: ");
            String chosen_mode = sc.nextLine().trim().toLowerCase();
            
            if (chosen_mode.equals("pvp") || chosen_mode.equals("pvc")) {
                return chosen_mode;
            }
            System.out.println("Invalid input! Please enter 'pvc' or 'pvp'.");
        }
    }

    
    public int promptPlayerCount() {
        while (true) {
            try {
                System.out.print("Please input number of players: ");
                int count = sc.nextInt();
                sc.nextLine(); // consume newline
                
                if (count >= 2 && count <= 6) {
                    return count;
                } else {
                    System.out.println("Invalid input! Number of players must be between 2 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.next(); // consume invalid input
            }
        }
    }

    
    public void displayParade(List<Card> parade) {
        System.out.printf("Current Parade: %s%n%n", parade);
    }

    
    public void displayPlayerHand(Player player) {
        List<Card> hand = player.getHand();
        List<String> formattedList = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            formattedList.add((i + 1) + ". " + hand.get(i).toString());
        }
        System.out.println("Your hand: " + String.join(", ", formattedList));
    }

    
    public void displayCollectedCards(Player player) {
        System.out.printf("Collected cards: %s%n", player.getCollected());
    }

    
    public void displayDeckRemaining(int remaining) {
        System.out.printf("Deck remaining cards: %s%n", remaining);
    }

    
    public int promptCardSelection(Player player) {
        System.out.println("Please choose a card in hand to place face up at end of parade");
        while (true) {
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline
                
                if (choice > 0 && choice <= player.getHand().size()) {
                    return choice - 1; // convert to 0-based index
                }
                System.out.print("Invalid Selection! Please select again: ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid Selection! Please select again: ");
                sc.next(); // consume invalid input
            }
        }
    }

    
    public void displayBotChoice(Card chosenCard) {
        System.out.printf("Bot chose %s%n", chosenCard);
    }

    
    public void displayMessage(String message) {
        System.out.println(message);
    }

    
    public void displayLastRound() {
        System.out.printf("LAST ROUND%n");
    }

    
    public void displayPlayerTurn(int playerNumber) {
        System.out.printf("%n%nPlayer %d Turn:%n", playerNumber);
    }

    public void displayFinalScores(List<Player> players) {
        System.out.println("\n====== Final Scores ======");
        for (Player player : players) {
            int score = player.calculateScore(players);
            System.out.printf("Player %d Score: %d%n", players.indexOf(player) + 1, score);
        }
    }
    
    public void displayCollectedCards(List<Player> players) {
        System.out.println("\n====== Collected Cards Per Player ======");
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            System.out.printf("Player %d Collected: %s%n", i + 1, player.getCollected());
        }
    }
    
    public void displayWinner(Player winner, List<Player> players) {
        int winnerScore = winner.calculateScore(players);
        System.out.printf("Winner is Player %d with a score of %d!%n",
                players.indexOf(winner) + 1, winnerScore);
    }
}