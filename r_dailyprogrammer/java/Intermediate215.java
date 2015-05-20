import java.io.*;
import java.util.*;

public class Intermediate215 {

	public static class Pair{
		public int one;
		public int two;
	}
	
	// Courtesy of Dukeling at http://stackoverflow.com/a/20035759, modified
	public static void allBitSetPermutations(int k, int n, ArrayList<BitSet> bitSets, BitSet bs)
	{
	   if (k == n)
	   {
	      return;
	   }
	   bs.set(k, false);
	   bitSets.add((BitSet) bs.clone());
	   allBitSetPermutations(k+1, n, bitSets, bs);
	   bs.set(k, true);
	   bitSets.add((BitSet) bs.clone());
	   allBitSetPermutations(k+1, n, bitSets, bs);
	}
	
	private static boolean isValidNetwork(int wires,
			ArrayList<Pair> comparators, ArrayList<BitSet> bitSets) {
		boolean validNetwork = true;
		for (BitSet bitSet : bitSets) {
			for (Pair pair : comparators) {
				boolean bitOne = bitSet.get(pair.one);
				boolean bitTwo = bitSet.get(pair.two);
				if(!(bitOne == bitTwo) && bitOne){
					bitSet.flip(pair.one);
					bitSet.flip(pair.two);
				}
			}
			validNetwork = isOrdered(wires, bitSet);
			if(!validNetwork){
				break;
			}
		}
		return validNetwork;
	}
	
	private static boolean isOrdered(int wires, BitSet bitSet){
		boolean isOrdered = true;
		for (int i = 0; i < wires-1; i++) {
			boolean bitOne = bitSet.get(i);
			boolean bitTwo = bitSet.get(i+1);
			// Only time they're not ordered is when bitOne=true and bitTwo=false
			isOrdered = isOrdered && !(bitOne && !bitTwo);
		}
		return isOrdered;
	}
	
	public static void main(String[] args) {
		if(args.length < 1){
			System.exit(1);
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File("java/"+ args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file " + args[0]);
			e.printStackTrace();
		}
		int wires = sc.nextInt();
		int nbrOfComparators = sc.nextInt();
		
		ArrayList<Pair> comparators = new ArrayList<Pair>();
		for (int i = 0; i < nbrOfComparators; i++) {
			Pair pair = new Pair();
			pair.one = sc.nextInt();
			pair.two = sc.nextInt();
			comparators.add(pair);
		}
		
		ArrayList<BitSet> bitSets = new ArrayList<BitSet>();
		allBitSetPermutations(0, wires, bitSets, new BitSet(wires));
		
		System.out.println("Is valid network: " + isValidNetwork(wires, comparators, bitSets));
	}



	private static void printBitSets(int wires, ArrayList<BitSet> bitSets) {
		for (BitSet bitSet : bitSets) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < wires; i++) {
				String str = bitSet.get(i)? "1": "0";
				sb.append(str);
			}
			System.out.println(sb.toString() + "");
		}
	}

}
