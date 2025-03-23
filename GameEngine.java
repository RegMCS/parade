import java.util.*;
import java.io.*;

public class GameEngine {

    /*
     * One game object -> 1 list of players + 1 deck of parade + 1 pDeck
     */
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Card> parade = new ArrayList<>();
    private pDeck pdeck = new pDeck();

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
        System.out.print("Please input number of players: ");
        Integer playercount = sc.nextInt();
        System.out.println("");

        // Instantiate players and issue cards
        for (int i = 0; i < playercount; i++) {
            Player temp = new Player();
            for (int j = 0; j < 5; j++) {
                temp.drawCard(pdeck.issueCard());
            }

            players.add(temp);
        }

        // Create parade
        for (int i = 0; i < 6; i++) {
            parade.add(pdeck.issueCard());
        }

        sc.close();
    }

    public Card getCardFromHand(Player player, String colour, int value){

        ArrayList<Card> theHand = player.getHand();
        //initialise card result.
        Card result = null;
        boolean gotColour = false;
        boolean gotValue = false;
        int len = theHand.size();
        Iterator<Card> iter = theHand.iterator();
        while(iter.hasNext()){
            gotColour = false;
            gotValue = false;

            Card current = iter.next();
            if(colour.equals(current.getColor())){
                gotColour = true;
            }
            if( ( (current.getNumber()) - value) == 0  ){
                gotValue = true;
            }

            if(gotColour && gotValue){
                result = current;
                //we also have to remove the card we "take" for theHand and also break out of the loop to ensure we do not "take" 
                //more than one card
                iter.remove();
                break;
            }
            //we have to reset gotColour and GotValue to be false at the stary of every loop as that current result do no interfere next one
        }

        return result;
    }

    public boolean cardIsInHand(Player player,String colour, int value){
        boolean isInHand = false;
        ArrayList<Card> theHand = player.getHand();
        boolean gotColour = false;
        boolean gotValue = false;
        int len = theHand.size();
        for(int i = 0 ; i < len ; i++){
            gotColour = false;
            gotValue = false;

            Card current = theHand.get(i);
            if(colour.equals(current.getColor())){
                gotColour = true;
            }
            if( ( (current.getNumber()) - value) == 0  ){
                gotValue = true;
            }

            if(gotColour && gotValue){
                isInHand = true;
            }
            //we have to reset gotColour and GotValue to be false at the stary of every loop as that current result do no interfere next one
        }
        return isInHand;
    }

    public void playerTurn(Player player) {
        /*
         * Please implement this function, this should:
         * 1. Take in the player who's playing the current turn
         * 2. Give the player an option to select a card to put into the parade
         * 3. Add the selected card to the parade
         * 4. Based on card.getNumber(), separate cards into Removal Section (0, parade.size() - card.getNumber() - 1) and Untouched Section (parade.size() - card.getNumber() - 1 onwards)
         * 5. Remove the cards within the removal section that has the same COLOUR or a NUMBER lower or same as card.getNumber()
         * 6. Removed cards are added to the Player object, collected variable
         */
        
         // 2. Give the player an option to select a card to put into the parade
         System.out.println("Please choose a card in hand to place face up at end of parade");
         System.out.printf("Options: %s", player.getHand());
         Scanner sc = new Scanner(System.in);
         
         String colour = null;
         int value = 20;

         boolean isNotOkCard = true;
         do{

         System.out.print("Please Type card chosen:");
         String cardChosed = sc.nextLine();
         //next we split to the colours and value
         String[] part = cardChosed.split(" ");
         //assuming first element always "colour" and second always "value"
         colour = part[0];
         //arbitrarily assign value to be 20, we know that there is no way for value to be 20, this is just so value is initialised
         value = 20;
         try{
            value = Integer.parseInt(part[1]);
         }catch (NumberFormatException e){
            e.getMessage();
         }
        //  //if value is not between 0 - 10, we will reject value
        //  boolean isBetweenAcceptedValues = false;
        //  for(int i = 0; i < 11 ; i++){
        //     if( (value - i) == 0){
        //         isBetweenAcceptedValues = true;
        //     }
        //  }
        //  if(!(isBetweenAcceptedValues)){
        //     System.out.println("Value is not between accepted range of values.");
        //  }

        //I realised it might be easier to just make a method to tell whther the "card" is in the players hand
        if( !(cardIsInHand(player, colour, value))){
            System.out.println("The card chosen is not in the player's hand, please try again");
        }

        isNotOkCard = (! (cardIsInHand(player, colour, value)));
        //when the player chooses a card that is in the hand, the bool is now false and will not loop again

         }while(isNotOkCard);

         //3. Add the selected card to the parade
         Card chosen = getCardFromHand(player, colour, value)
         parade.add(chosen);
         //now we have removed the card from playerHand and added to paradedeck

         //4. Based on card.getNumber(), separate cards into Removal Section (0, parade.size() - card.getNumber() - 1) 
         //and Untouched Section (parade.size() - card.getNumber() - 1 onwards)
         int lenParade = parade.size();
         // we add 1 to the getNumber is cause we also added the new card to parade that is also not in removal mode
         int numCardsToLoop = lenParade - ((chosen.getNumber()) + 1);
         for(int i = 0 ; i < numCardsToLoop ; i++){
            
         }



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

    

    // public ArrayList<Card> createSubDeck(String colour) {
    // ArrayList<Card> temp = new ArrayList<>();
    // for (int i = 0; i < 11; i++) {
    // // make new Card with value, for "player" we put null as we have not assigned
    // // card to player yet
    // Card current = new Card(i, colour, false, null);
    // temp.add(current);
    // }

    // return temp;
    // }

    // public boolean have6Colours(Player temp) {
    // ArrayList<Card> cardOwned = temp.getCardsOwner();
    // int len = cardOwned.size();
    // boolean haveAllColours = false;
    // boolean haveRed = false;
    // boolean haveGreen = false;
    // boolean haveBlue = false;
    // boolean havePurple = false;
    // boolean haveGrey = false;
    // boolean haveOrange = false;
    // for (int i1 = 0; i1 < len; i1++) {
    // Card current1 = cardOwned.get(i1);
    // if ((current1.getColour()).equals("red")) {
    // haveRed = true;
    // }
    // if ((current1.getColour()).equals("green")) {
    // haveGreen = true;
    // }
    // if ((current1.getColour()).equals("blue")) {
    // haveBlue = true;
    // }
    // if ((current1.getColour()).equals("purple")) {
    // havePurple = true;
    // }
    // if ((current1.getColour()).equals("grey")) {
    // haveGrey = true;
    // }
    // if ((current1.getColour()).equals("orange")) {
    // haveOrange = true;
    // }
    // }

    // haveAllColours = (haveRed && haveBlue && haveGreen && havePurple && haveGrey
    // && haveOrange);

    // return haveAllColours;
    // }
}
