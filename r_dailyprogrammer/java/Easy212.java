
public class Easy212 {
	static final String vowels = "AEIOUYÅÄÖaeiouyåäö!?.,' ";
	public static void main(String[] args) {
		String text = args[0];
		String encoded;
		String decoded;
		encoded = encode(text);
		decoded = decode(encoded);
		System.out.println(encoded);
		System.out.println(decoded);
	}
	
	private static String decode(String encoded) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < encoded.length(); i++){
			sb.append(encoded.charAt(i));
			if(!vowels.contains(""+encoded.charAt(i))){
				i+=2;
			}
		}
		return sb.toString();
	}

	private static String encode(String str){
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			String toAppend = !vowels.contains(""+c)? c + "o" + Character.toLowerCase(c): ""+c;
			sb.append(toAppend);
		}
		return sb.toString();
	}
}