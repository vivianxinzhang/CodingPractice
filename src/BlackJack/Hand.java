package BlackJack;
import java.util.*;

public class Hand {
    protected final List<Card> cards = new ArrayList<>();

    // or we can define an abstract method here, i.e., no implementation score()
    public int score() {
        int score = 0;

        for (Card card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCard(Card[] c) {
        Collections.addAll(cards,c);
    }

    public int size() {
        return cards.size();
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < cards.size() - 1; i++) {
            System.out.print(cards.get(i).value() + ", ");
        }
        System.out.print(cards.get(cards.size() - 1).value());
        System.out.println("]");
    }
}
