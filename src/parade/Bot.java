package parade;

import java.util.*;

public class Bot extends Player{
    
    public Integer botTurn(List<Card> parade) {
        List<Card> hand = super.getHand();
    
        int maxNumber = Integer.MIN_VALUE;
        int indexCardChosen = -1;
    
        // First pass: Find max number and its index
        for (int i = 0; i < hand.size(); i++) {
            int num = hand.get(i).getNumber();
            if (num > maxNumber) {
                maxNumber = num;
                indexCardChosen = i;
            }
        }
    
        // Check if all cards in hand have the same number
        boolean allSame = true;
        for (Card c : hand) {
            if (c.getNumber() != maxNumber) {
                allSame = false;
                break;
            }
        }
    
        // If all values are the same, fallback to least frequent color
        if (allSame) {
            Map<String, Integer> colorCount = new HashMap<>();
            for (Card c : parade) {
                colorCount.put(c.getColor(), colorCount.getOrDefault(c.getColor(), 0) + 1);
            }
    
            int minCount = Integer.MAX_VALUE;
            for (int i = 0; i < hand.size(); i++) {
                String color = hand.get(i).getColor();
                int count = colorCount.getOrDefault(color, 0);
    
                if (count < minCount) {
                    minCount = count;
                    indexCardChosen = i;
                }
            }
        }
    
        return indexCardChosen;
    }
    
}
