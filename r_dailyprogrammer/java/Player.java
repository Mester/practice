// For week 216

import java.util.ArrayList;
import java.util.Collections;

public class Player implements Comparable<Player>{
	private ArrayList<Card> personalCards = new ArrayList<Card>();
	public ArrayList<Hand> possibleHands = new ArrayList<Hand>();
	private boolean folded = false;
	private static int currentIndex = 0;
	public int playerIndex;

	public Player() {
		playerIndex = currentIndex++;
	}

	public Player(int amountOfCards, Deck deck) {
		for (int i = 0; i < amountOfCards; i++) {
			giveCard(deck.pop());
		}
		playerIndex = currentIndex++;
	}

	public void giveCard(Card card) {
		personalCards.add(card);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < personalCards.size(); i++) {
			sb.append(personalCards.get(i));
			if (i < personalCards.size() - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	public void fold() {
		folded = true;
	}

	public boolean hasFolded() {
		return folded;
	}

	public void updatePossibleHands(Card c1, Card c2, Card c3) {
		ArrayList<Card> temp = new ArrayList<Card>(personalCards);
		temp.add(c1);
		temp.add(c2);
		temp.add(c3);
		possibleHands.add(new Hand(temp));
	}

	public void updatePossibleHands(Card newCard) {
		ArrayList<Hand> tempHands = new ArrayList<Hand>(possibleHands);
		for (Hand hand : possibleHands) {
			for (int i = 0; i < 5; i++) {
				ArrayList<Card> cards = (ArrayList<Card>) hand.getCards()
						.clone();
				cards.set(i, newCard);
				tempHands.add(new Hand(cards));
			}
		}
		possibleHands = tempHands;
		Collections.sort(possibleHands);
		Collections.reverse(possibleHands); // So that highest value is first
	}

	@Override
	public int compareTo(Player other) {
		return this.possibleHands.get(0).compareTo(other.possibleHands.get(0));
	}
}