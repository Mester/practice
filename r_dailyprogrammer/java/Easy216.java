import java.util.ArrayList;
import java.util.Scanner;

public class Easy216 {

	public static void main(String[] args) {
		int nbrOfPlayers = choosePlayers();
		Deck deck = new Deck();
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

	private static void dealRestOfGame(Deck deck) {
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

	public static void dealCardsToPlayers(ArrayList<Player> players,
			Deck deck) {
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
			System.out.print("How many players? (2-8): ");
			players = sc.nextInt();
		} while (players < 2 || players > 8);
		sc.close();
		return players;
	}
}
