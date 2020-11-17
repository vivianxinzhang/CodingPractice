package BlackJack;
import java.util.*;

public class Deck {
    private static final Random random = new Random();

    private final List<Card> cards = new ArrayList<>();
    private int dealtIndex = 0;

    public Deck() {
        for (int i = 0; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(i, suit));
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int j = random.nextInt(cards.size() - i) + i;
            Card card1 = cards.get(i);
            Card card2 = cards.get(j);
            cards.set(i, card2);
            cards.set(j, card1);
        }
    }

    private int remainingCards() {
        return cards.size() - dealtIndex;
    }

    public Card[] dealHand(int number) {
        if (remainingCards() < number) {
            return null;
        }
        Card[] cards = new Card[number];
        for (int i = 0; i < number; i++) {
            cards[i] = dealCard();
        }
        return cards;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards.get(dealtIndex);
    }

//    public void reset() {
//        dealtIndex = 0;
//        shuffle if necessary
//    }
}



















