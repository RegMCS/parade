import java.util.*;
import java.io.*;

public class GameEngine {

    /*
     * One game object -> 1 list of players + 1 deck of parade + 1 pDeck
     */
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> parade = new ArrayList<>();
    private pDeck pdeck = new pDeck();
    private boolean pvp = false; // Stores whether game is player-vs-player or player-vs-bot
    private boolean lastround = false;

    public void startGame() {
        /**
         * Initializes and starts the game by performing the following setup:
         * 
         * 1. Prompts the user to input the number of players.
         * 2. Shuffles the deck before dealing cards.
         * 3. Creates player instances and deals each player.
         * 4. Places 6 cards in the middle of the table as the initial parade
         * participants.
         * 5. Closes the scanner to prevent resource leaks.
         */
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Please indicate player-vs-computer or player-vs-player [pvc/pvp]: ");
            
            String chosen_mode = sc.nextLine().trim().toLowerCase();
            
            if (chosen_mode.equals("pvp")) {
                pvp = true;
                break;

            }
            if (chosen_mode.equals("pvc")) {
                pvp = false;
                break;

            }
            System.out.println("Invalid input! Please enter 'pvc' or 'pvp'.");
        }
        
        int playercount = 0;

        while (true) {
            try {
                System.out.print("Please input number of players: ");
                playercount = sc.nextInt();
                
                if (playercount >= 2 && playercount <= 6) {
                    break;

                } else {
                    System.out.println("Invalid input! Number of players must be between 2 and 6.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.next();

            }
        }
        
        System.out.println("");

        if (pvp) {
            // Instantiate players and issue cards - IF PVP
            for (int i = 0; i < playercount; i++) {
                Player temp = new Player();
                for (int j = 0; j < 5; j++) {
                    temp.drawCard(pdeck.issueCard());
                }

                players.add(temp);
            }
        } else {
            // Instantiate player and issue cards
            Player temp = new Player();
            for (int j = 0; j < 5; j++) {
                temp.drawCard(pdeck.issueCard());
            }
            players.add(temp);

            // Instantiate bots and issue cards
            for (int i = 0; i < playercount - 1; i++) {
                Bot temp2 = new Bot();
                for (int j = 0; j < 5; j++) {
                    temp2.drawCard(pdeck.issueCard());
                }
                players.add(temp2);
            }
        }

        // Create parade
        for (int i = 0; i < 6; i++) {
            parade.add(pdeck.issueCard());

        }

        gameManager();
    }

    public void gameManager() {

        int currentPlayerTurn = 0;

        while (!gameEnd()) { // Replace with end game condition
            if (currentPlayerTurn > players.size() - 1) {
                currentPlayerTurn = 0;

            } else {
                System.out.printf("%n%nPlayer %d Turn:%n", currentPlayerTurn + 1);
                playerTurn(players.get(currentPlayerTurn));

                currentPlayerTurn++;
            }
        }

        lastround = true;

        for (int i = 0; i < players.size(); i++) {
            if (currentPlayerTurn > players.size() - 1) {
                currentPlayerTurn = 0;

            } else {
                System.out.printf("%n%nLAST ROUND%n");
                System.out.printf("Player %d Turn:%n", currentPlayerTurn + 1);
                playerTurn(players.get(currentPlayerTurn));

                currentPlayerTurn++;
            }
        }

        //Tabulate Scores
        
    }

    public void addCardToPlayer(Player player, Card card) {
        ArrayList<Card> theCollect = player.getCollected();
        theCollect.add(card);
    }

    public void playerTurn(Player player) {
        /*
         * 1. Take in the player/bot who's playing the current turn
         * 2. Give the player an option to select a card to put into the parade (Skipped if its a Bot)
         * 3. Add the selected card to the parade
         * 4. Based on card.getNumber(), separate cards into Removal Section (0, parade.size() - card.getNumber() - 1) and Untouched Section (parade.size() - card.getNumber() - 1 onwards)
         * 5. Remove the cards within the removal section that has the same COLOUR or a NUMBER lower or same as card.getNumber()
         * 6. Removed cards are added to the Player object, collected variable
        */
        
        // 2. Give the player an option to select a card to put into the parade
        Scanner sc = new Scanner(System.in);
        Integer chosenIndex = null;

        if (player instanceof Bot bot) {
            chosenIndex = bot.botTurn();
            System.out.printf("Current Parade: %s%n%n", parade);
            System.out.printf("Bot collected: %s%n", player.getCollected());
            System.out.printf("Bot chose %s", player.getHand().get(chosenIndex));

        } else {

            List<Card> hand = player.getHand();
            ArrayList<String> formattedList = new ArrayList<>();

            for (int i = 0; i < hand.size(); i++) {
                formattedList.add((i + 1) + ". " + hand.get(i).toString());
                }

            System.out.printf("pDeck remaining cards: %s%n", pdeck.getDeck().size());
            System.out.printf("Current Parade: %s%n%n", parade);
            System.out.printf("You collected: %s%n", player.getCollected());
            System.out.println("Please choose a card in hand to place face up at end of parade");
            System.out.printf("Options: %s \r\n", String.join(", ", formattedList));
            System.out.print("Please Type card chosen:");
            while (true) {
                try {
                    chosenIndex = sc.nextInt();
            
                    while (chosenIndex > hand.size() || chosenIndex <= 0) {
                        System.out.print("Invalid Selection! Please select again: ");
                        chosenIndex = sc.nextInt();
                    }
            
                    break;
                } catch (InputMismatchException e) {
                    System.out.print("Invalid Selection! Please select again: ");
                    sc.next();
                }
            }

            chosenIndex--;
        }

        //Remove chosen card from player/bot and add to the Parade
        Card chosen = player.playCard(player.getHand().get(chosenIndex));
        parade.add(chosen);

         //4. Based on card.getNumber(), separate cards into Removal Section (0, parade.size() - card.getNumber() - 1) 
         //and Untouched Section (parade.size() - card.getNumber() - 1 onwards)
         //&
         // 5. Remove the cards within the removal section that has the same COLOUR or a NUMBER lower or same as card.getNumber()
         //&
         //6. Removed cards are added to the Player object, collected variable
         int lenParade = parade.size();
         // we add 1 to the getNumber is cause we also added the new card to parade that is also not in removal mode
         int numCardsToLoop = lenParade - ((chosen.getNumber()) + 1);
         Iterator<Card> iterParade = parade.iterator();
         
        for(int i = 0 ; i < numCardsToLoop ; i++){
            //we make use of the iterator as we need to be able to "remove" card from parade.
            Card current = null;
            if(iterParade.hasNext()){
                current = iterParade.next();
                String currentColour = current.getColor();
                int currentValue = current.getNumber();

                if(currentColour.equals(chosen.getColor())){
                    addCardToPlayer(player, current);
                    iterParade.remove();
                    //go to next loop as card has been added and removed
                    continue;
                }
                if( (currentValue - (chosen.getNumber())) <= 0){
                    //this means current card has value less or equal to the chosencard
                    addCardToPlayer(player, current);
                    iterParade.remove();
                    //go to next loop as card has been added and removed
                    continue;
                }
            }
        }

        if (!lastround) {
            player.drawCard(pdeck.issueCard());
        }
    }

    public boolean gameEnd() {
        if (pdeck.getDeck().size() == 0) {
            return true;
        }
        for (Player player : players) {
          
            ArrayList<Card> currentCards = player.getCollected();
            HashSet<String> colorSet = new HashSet<>(); // or HashSet<Color> if using enum

            for (Card card : currentCards) {
                colorSet.add(card.getColor()); // collect unique colors
            }

            if (colorSet.size() == 6) {
                return true; // No need to continue checking others
            }
        }
        return false;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Card> getParade() {
        return parade;
    }

    public void setParade(ArrayList<Card> parade) {
        this.parade = parade;
    }

    public static void main(String[] args) {

        GameEngine session = new GameEngine();
        session.startGame(); // Starts the game

    }
}
