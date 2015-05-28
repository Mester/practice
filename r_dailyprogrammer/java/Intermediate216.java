import java.util.ArrayList;
import java.util.Collections;

public class Intermediate216 {

	public static void main(String[] args) {
		int nbrOfPlayers = Easy216.choosePlayers();
		Deck deck = new Deck();
		ArrayList<Player> players = generatePlayers(nbrOfPlayers, 2, deck);
		Easy216.printPlayers(players);
		System.out.println();

		ArrayList<Card> communityCards = new ArrayList<Card>();
		theFlop(players, deck, communityCards);
		aiFoldDecision(players, 2); // Fold if don't even have a pair
		
		theRiver(players, deck, communityCards);
		aiFoldDecision(players, 4); // At least three of a kind
		
		theTurn(players, deck, communityCards);

		theEnd(players, deck, communityCards);

		// test();
		// test2(players,deck);
	}

	private static void aiFoldDecision(ArrayList<Player> players, int threshold) {
		for (int i = 1; i < players.size(); i++) { // 0 is the player
			Player currentPlayer = players.get(i);
			int currentValue = currentPlayer.possibleHands.get(0).getPrimaryValue();
			if (currentValue < threshold) {
				currentPlayer.fold();
			}
		}
	}

	private static void test2(ArrayList<Player> players, Deck deck) {
		Card card1 = deck.pop();
		Card card2 = deck.pop();
		Card card3 = deck.pop();
		Card card4 = deck.pop();
		Card card5 = deck.pop();
		players.get(0).updatePossibleHands(card1, card2, card3);
		System.out.println(card1 + ", " + card2 + ", " + card3);
		players.get(0).possibleHands.get(0).display();
		players.get(0).updatePossibleHands(card4);
		System.out.println(card4);
		players.get(0).possibleHands.get(0).display();
		players.get(0).updatePossibleHands(card5);
		System.out.println(card5);
		players.get(0).possibleHands.get(0).display();
	}

	private static void test() {
		Deck deck = new Deck();
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(0, 1));
		cards.add(new Card(0, 2));
		cards.add(new Card(0, 3));
		cards.add(new Card(0, 4));
		cards.add(new Card(0, 5));
		Hand hand1 = new Hand(cards);
		cards.clear();
		cards.add(new Card(0, 3));
		cards.add(new Card(0, 4));
		cards.add(new Card(1, 5));
		cards.add(new Card(0, 6));
		cards.add(new Card(0, 7));
		Hand hand2 = new Hand(cards);
		hand1.display();
		hand2.display();
		System.out.println(hand1.compareTo(hand2));
	}

	public static void burnCard(Deck deck) {
		Card nextCard = deck.pop();
		System.out.println("Card burned: " + nextCard);
	}

	private static void theFlop(ArrayList<Player> players, Deck deck,
			ArrayList<Card> communityCards) {
		StringBuilder sb = new StringBuilder();
		burnCard(deck);
		sb.append("Flop: ");
		for (int i = 0; i < 3; i++) {
			Card nextCard = deck.pop();
			sb.append(nextCard);
			if (i < 2)
				sb.append(", ");
			communityCards.add(nextCard);
		}
		for (Player player : players) {
			player.updatePossibleHands(communityCards.get(0),
					communityCards.get(1), communityCards.get(2));
		}
		System.out.println(sb.toString());
	}

	private static void theRiver(ArrayList<Player> players, Deck deck,
			ArrayList<Card> communityCards) {
		burnCard(deck);
		Card nextCard = deck.pop();
		communityCards.add(nextCard);
		for (Player player : players) {
			player.updatePossibleHands(nextCard);
		}
		System.out.println("River: " + nextCard);
	}

	private static void theTurn(ArrayList<Player> players, Deck deck,
			ArrayList<Card> communityCards) {
		burnCard(deck);
		Card nextCard = deck.pop();
		communityCards.add(nextCard);
		for (Player player : players) {
			player.updatePossibleHands(nextCard);
		}
		System.out.println("Turn: " + nextCard);

	}

	private static void theEnd(ArrayList<Player> players, Deck deck,
			ArrayList<Card> communityCards) {
		ArrayList<Player> nonFoldedPlayers = new ArrayList<Player>();
		for (Player player : players) {
			if (!player.hasFolded()) {
				nonFoldedPlayers.add(player);
			}
		}
		Collections.sort(nonFoldedPlayers);
		Collections.sort(players);
		Collections.reverse(nonFoldedPlayers);
		Collections.reverse(players);
		Player winner = nonFoldedPlayers.get(0);
		System.out.println();
		System.out.println("Winner is player: " + winner.playerIndex);
		System.out.print("With: ");
		winner.possibleHands.get(0).display();
		System.out.println("His hand is: " + winner.possibleHands.get(0));
		
		winner = players.get(0);
		System.out.println();
		System.out.println("Best possible hand would belong to player: " + winner.playerIndex);
		System.out.print("With: ");
		winner.possibleHands.get(0).display();
		System.out.println("His hand is: " + winner.possibleHands.get(0));
	}

	private static ArrayList<Player> generatePlayers(int nbrOfPlayers,
			int nbrOfCards, Deck deck) {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < nbrOfPlayers; i++) {
			players.add(new Player(nbrOfCards, deck));
		}
		return players;
	}
}
