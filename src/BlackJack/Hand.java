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
}
