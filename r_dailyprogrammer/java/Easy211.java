public class Easy211 {

	public static void main(String[] args) {
		String name = args[0];
		char firstLetter = name.charAt(0);
		String tail = "";
		if ("AEIOU".contains(firstLetter + "")) {
			tail = name.toLowerCase();
		} else {
			tail = name.substring(1);
		}

		StringBuilder sb = new StringBuilder();
		boolean fbm = "FBM".contains(firstLetter + "");

		sb.append(name + ", " + name);
		if (!fbm) {
			sb.append(" bo B");
		} else {
			sb.append(" bo-");
		}
		sb.append(tail + "\n");

		sb.append("Bonana fanna fo");
		if (!fbm) {
			sb.append(" F");
		} else {
			sb.append("-");
		}
		sb.append(tail + ",\n");

		sb.append("Fee fy mo");
		if (!fbm) {
			sb.append(" M");
		} else {
			sb.append("-");
		}
		sb.append(tail + ",\n");

		sb.append(name + "!");

		System.out.println(sb);
	}
}
