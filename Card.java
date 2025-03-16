public class Card implements Comparable<Card> {
    private int number;
    private String color;
    private boolean facedown;
    private player owner;

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

    public boolean isFacedown() {
        return facedown;
    }

    public player getOwner() {
        return owner;
    }

    public void setFace(boolean facedown) {
        this.facedown = facedown;
    }

    public void setOwner(player owner) {
        this.owner = owner;
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
