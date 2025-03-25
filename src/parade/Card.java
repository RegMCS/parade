package parade;

public class Card {
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
    public String toString() {
        return color + " " + number;
    }
}
