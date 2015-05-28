// For week 216
public class Card {
    private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
    private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", 
                   "8", "9", "10", "Jack", "Queen", "King" };
	
	private int rank;
	private int suit;

	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ranks[rank]);
		sb.append(" of ");
		sb.append(suits[suit]);
		return sb.toString();
	}

	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public static String rankAsString(int rank) {
		return ranks[rank];
	}
}