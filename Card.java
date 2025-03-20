public class Card implements Comparable<Card> {
    private int number;
    private String color;

    public Card(int number, String color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(Card another) {
        int colorval = this.color.compareTo(another.color);
        if (colorval != 0) {
            return colorval;
        }
        return Integer.compare(this.number, another.number);
    }

    @Override
    public String toString() {
        return color + " " + number;
    }
    
}
