import java.util.HashMap;

public class Intermediate213 {
	public static final String[] keyRows = {	"qwertyuiop", 
												"asdfghjkl ",
												"^zxcvbnm ^", 
												"   #####  "};

	public static class Coord {
		public int x, y;

		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return "x:" + x + " y:" + y;
		}

		public int distance(Coord other) {
			int dx = Math.abs(x - other.x);
			int dy = Math.abs(y - other.y);
			return dx + dy;
		}
	}

	public static class Keyboard {
		public HashMap<Character, Coord> kb = new HashMap<Character, Coord>();

		public Keyboard() {
			for (int i = 0; i < keyRows.length; i++) {
				for (int j = 0; j < keyRows[i].length(); j++) {
					kb.put(keyRows[i].charAt(j), new Coord(j, i));
				}
			}
		}

		public Coord getClosestCoord(Hand hand, char ch) {
			int dx = 0;
			ch = Character.toLowerCase(ch);
			Coord charCoord = kb.get(ch);
			if (ch == ' ') {
				ch = '#';
				int[] distances = new int[4];
				charCoord = kb.get(ch);
				for (int i = 0; i < distances.length; i++) {
					Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);
					distances[i] = hand.coord.distance(tempCoord);
					if (i != 0) {
						dx = distances[i] < distances[i - 1] ? i : dx;
					}
				}
			} else if (ch == '^') {
				int[] distances = new int[10];
				for (int i = 0; i < distances.length; i += 9) {
					Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);
					distances[i] = hand.coord.distance(tempCoord);
					if (i != 0) {
						dx = distances[i] < distances[i - 9] ? i : dx;
					}
				}
			}

			return new Coord(charCoord.x - dx, charCoord.y);
		}

		public int getEffort(Hand hand, char ch) {
			ch = Character.toLowerCase(ch);
			ch = ch == ' ' ? '#' : ch;
			Coord charCoord = kb.get(ch);
			int distance = Integer.MAX_VALUE;
			if (ch == '#') {
				for (int i = 0; i < 4; i++) {
					Coord tempCoord = new Coord(charCoord.x - i, charCoord.y);
					distance = Math.min(distance,
							hand.coord.distance(tempCoord));
				}
			} else if (ch == '^') {
				distance = Math.min(hand.coord.distance(charCoord), hand.coord
						.distance(new Coord(charCoord.x - 9, charCoord.y)));
			} else {
				distance = hand.coord.distance(charCoord);
			}
			return distance;
		}
	}

	public static class Hand {
		public Coord coord;
		private String name;

		public Hand(int startingX, int startingY, String name) {
			this.coord = new Coord(startingX, startingY);
			this.name = name;
		}

		public void moveTo(int newX, int newY) {
			coord.x = newX;
			coord.y = newY;
		}

		public void moveTo(Coord newCoord) {
			this.coord = newCoord;
		}

		public String toString() {
			return name + " hand";
		}
	}

	public static void findPathToEnd(String str, Keyboard kb) {
		int startIndex = 0;
		char nextChar = str.charAt(startIndex);
		Hand leftHand, rightHand;
		if (Character.isUpperCase(nextChar)) {
			nextChar = Character.toLowerCase(nextChar);
			leftHand = new Hand(0, 2, "left");
			rightHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
					"right");
			System.out.println("^: Use " + leftHand);
			System.out.println(nextChar + ": Use " + rightHand);
			startIndex++;
		} else {
			leftHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
					"left");
			System.out.println(nextChar + ": Use " + leftHand);
			nextChar = str.charAt(++startIndex);
			rightHand = new Hand(kb.kb.get(nextChar).x, kb.kb.get(nextChar).y,
					"right");
			System.out.println(nextChar + ": Use " + rightHand);
			startIndex++;
		}
		int totalEffort = 0;
		for (int i = startIndex; i < str.length(); i++) {
			nextChar = str.charAt(i);
			int leftHandEffort = kb.getEffort(leftHand, nextChar);
			int rightHandEffort = kb.getEffort(rightHand, nextChar);
			if (Character.isUpperCase(nextChar)) {
				int leftHandToShift = kb.getEffort(leftHand, '^');
				int rightHandToShift = kb.getEffort(rightHand, '^');
				if (leftHandToShift+rightHandEffort < rightHandToShift+leftHandEffort) {
					leftHand.moveTo(kb.getClosestCoord(leftHand, '^'));
					rightHand.moveTo(kb.getClosestCoord(rightHand, nextChar));
					printMovement('^', leftHand, leftHandToShift);
					printMovement(nextChar, rightHand, rightHandEffort);
					totalEffort += leftHandToShift + rightHandEffort;
				} else {
					rightHand.moveTo(kb.getClosestCoord(rightHand, '^'));
					leftHand.moveTo(kb.getClosestCoord(leftHand, nextChar));
					printMovement('^', rightHand, rightHandToShift);
					printMovement(nextChar, leftHand, leftHandEffort);
					totalEffort += rightHandToShift + leftHandEffort;
				}
			} else {
				if (leftHandEffort < rightHandEffort) {
					leftHand.moveTo(kb.getClosestCoord(leftHand, nextChar));
					printMovement(nextChar, leftHand, leftHandEffort);
					totalEffort += leftHandEffort;
				} else {
					rightHand.moveTo(kb.getClosestCoord(rightHand, nextChar));
					printMovement(nextChar, rightHand, rightHandEffort);
					totalEffort += rightHandEffort;
				}
			}
		}
		System.out.println("Total effort: " + totalEffort);
	}

	private static void printMovement(char c, Hand hand, int effort) {
		System.out.println(c + ": Use " + hand + " (Effort: " + effort + ")");
	}

	public static void main(String[] args) {
		Keyboard kb = new Keyboard();
		for (int i = 0; i < args.length; i++) {
			System.out.println(args[i]);
			findPathToEnd(args[i], kb);
			System.out.println();
		}
	}
}
