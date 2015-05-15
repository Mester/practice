import java.io.*;
import java.util.*;

public class Hard214 {
	private static class Position implements Comparable<Position> {
		public double x, y;
		private Position toCompareTo; // Chester

		public Position(double x, double y, Position toCompareTo) {
			this.x = x;
			this.y = y;
			this.toCompareTo = toCompareTo;
		}

		public double distanceTo(Position other) {
			return Math.sqrt(Math.pow((x - other.x), 2)
					+ Math.pow((y - other.y), 2));
		}

		public void moveTo(Position other) {
			this.x = other.x;
			this.y = other.y;
		}

		@Override
		public int compareTo(Position other) {
			double thisDistance = this.distanceTo(toCompareTo);
			double otherDistance = other.distanceTo(toCompareTo);
			if (thisDistance < otherDistance) {
				return -1;
			} else if (thisDistance == otherDistance) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.exit(1);
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File("" + args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file.");
			e.printStackTrace();
			System.exit(1);
		}
		// sc.nextInt() also works, but this skips the rest of the empty line
		int n = Integer.parseInt(sc.nextLine());
		ArrayList<Position> treats = new ArrayList<Position>();
		Position chester = new Position(0.5, 0.5, null);
		for (int i = 0; i < n; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			treats.add(new Position(x, y, chester));
		}

		double totalDistance = 0;
		for (int i = 0; i < n; i++) {
			Collections.sort(treats);
			totalDistance += chester.distanceTo(treats.get(0));
			chester.moveTo(treats.remove(0));
//			if (i % 50 == 0) {
				System.out.println(treats.size());
//			}
		}
		System.out.println(totalDistance);
	}

}
