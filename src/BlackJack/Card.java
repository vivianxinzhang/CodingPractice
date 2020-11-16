package BlackJack;

public class Card {
    private final int faceValue; // 1 for A, 11 for J, 12 for Q, 13 for K. Or we can use enum here.
    // private final FaceValue v;
    private final Suit suit;
    public Card(int c, Suit s) {
        faceValue = c;
        suit = s;
    }

    public int value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }
}

