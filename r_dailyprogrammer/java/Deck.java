import java.util.Collections;
import java.util.Stack;

// For week 216
public class Deck {
	Stack<Card> deck = new Stack<Card>();
	public Deck(){
		for (int suite = 0; suite <= 3; suite++) {
			for (int value = 0; value < 13; value++) {
				deck.push(new Card(suite, value));
			}
		}
		for (int i = 0; i < 100; i++) {
			Collections.shuffle(deck);
		}
	}
	
	public Stack<Card> getDeck() {
		return deck;
	}

	public Card pop() {
		return deck.pop();
	}
}
