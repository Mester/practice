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
		sb = !fbm ? sb.append(" bo B") : sb.append(" bo-");
		sb.append(tail + "\n");

		sb.append("Bonana fanna fo");
		sb = !fbm ? sb.append(" F") : sb.append("-");
		sb.append(tail + ",\n");

		sb.append("Fee fy mo");
		sb = !fbm ? sb.append(" M") : sb.append("-");
		sb.append(tail + ",\n");

		sb.append(name + "!");

		System.out.println(sb);
	}
}
