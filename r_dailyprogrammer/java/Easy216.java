import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Easy216 {
	public static enum Suite {
		CLUBS, DIAMOND, HEARTS, SPADES
	}

	public static class Card {
		private int value;
		private Suite suite;

		public Card(int suite, int value) {
			this.suite = Suite.values()[suite];
			this.value = value;
		}

		public Card(Suite suite, int value) {
			this.suite = suite;
			this.value = value;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			switch (value) {
			case 11:
				sb.append("Jack");
				break;
			case 12:
				sb.append("Queen");
				break;
			case 13:
				sb.append("King");
				break;
			case 14:
				sb.append("Ace");
				break;
			default:
				sb.append(value);
			}
			sb.append(" of " + suite);
			return sb.toString();
		}
	}

	public static class Player {
		Stack<Card> hand = new Stack<Card>();

		public void giveCard(Card card) {
			hand.add(card);
		}

		public String toString() {
			return hand.get(0).toString() + ", " + hand.get(1).toString();
		}
	}

	public static void main(String[] args) {
		int nbrOfPlayers = choosePlayers();
		Stack<Card> deck = generateDeck();
		ArrayList<Player> players = generatePlayers(nbrOfPlayers);
		dealCardsToPlayers(players, deck);
		printPlayers(players);
		System.out.println();
		dealRestOfGame(deck);
	}

	public static void printPlayers(ArrayList<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			String player;
			if (i == 0)
				player = "Your ";
			else
				player = "CPU " + i + " ";
			System.out.println(player + "hand: " + players.get(i).toString());
		}
	}

	private static void dealRestOfGame(Stack<Card> deck) {
		StringBuilder sb = new StringBuilder();
		sb.append("Card burned: " + deck.pop().toString());
		sb.append("\n");
		sb.append("Flop: ");
		for (int i = 0; i < 3; i++) {
			sb.append(deck.pop().toString() + ", ");
		}
		sb.append("\n");
		sb.append("Card burned: " + deck.pop().toString());
		sb.append("\n");
		sb.append("River: " + deck.pop().toString());
		sb.append("\n");
		sb.append("Card burned: " + deck.pop().toString());
		sb.append("\n");
		sb.append("Turn: " + deck.pop().toString());
		sb.append("\n");
		System.out.println(sb.toString());
	}

	private static ArrayList<Player> generatePlayers(int nbrOfPlayers) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < nbrOfPlayers; i++) {
			players.add(new Player());
		}
		return players;
	}

	private static Stack<Card> generateDeck() {
		Stack<Card> deck = new Stack<Card>();
		for (int suite = 0; suite <= 3; suite++) {
			for (int value = 2; value <= 14; value++) {
				deck.push(new Card(suite, value));
			}
		}
		for (int i = 0; i < 10; i++) {
			Collections.shuffle(deck);
		}
		return deck;
	}

	public static void dealCardsToPlayers(ArrayList<Player> players,
			Stack<Card> deck) {
		for (int i = 0; i < 2; i++) {
			for (Player player : players) {
				player.giveCard(deck.pop());
			}
		}
	}

	public static int choosePlayers() {
		Scanner sc = new Scanner(System.in);
		int players;
		do {
			System.out.print("How many players? (2-8)");
			players = sc.nextInt();
		} while (players < 2 || players > 8);
		sc.close();
		return players;
	}
}
