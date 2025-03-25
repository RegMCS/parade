import java.util.*;

public class Bot extends Player{

    /**
     * 1. choose least matching colours with the parade
     * 2. collect low-value cards
     * 3. control a colour (to get majority so cards are worth 1 point)
     */

    public Integer botTurn(){
        List<Card> hand = super.getHand();
        int maxNumber = 0;
        int indexCardChosen = -1;

        for (int i = 0; i < hand.size(); i++){
            Card option = hand.get(i);
            int cardNum = option.getNumber();
            if (cardNum > maxNumber){
                maxNumber = cardNum;
                indexCardChosen = i;
            }

        }

        return indexCardChosen;
    }
}
