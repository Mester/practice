import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Intermediate211 {
	String[] mazes = {
			  "@@........\n"
			+ "@@O.......\n"
			+ ".....O.O..\n"
			+ "..........\n"
			+ "..O.O.....\n"
			+ "..O....O.O\n"
			+ ".O........\n"
			+ "..........\n"
			+ ".....OO...\n"
			+ ".........$",
			"@@........\n"
			+ "@@O.......\n"
			+ ".....O.O..\n"
			+ "..........\n"
			+ "..O.O.....\n"
			+ "..O....O.O\n"
			+ ".O........\n"
			+ "..........\n"
			+ ".....OO.O.\n"
			+ ".........$", 
			"$.O...O...\n"
			+ "...O......\n"
			+ "..........\n"
			+ "O..O..O...\n"
			+ "..........\n"
			+ "O..O..O...\n"
			+ "..........\n"
			+ "......OO..\n"
			+ "O..O....@@\n"
			+ "........@@",
			".@@.....O.\n"
			+ ".@@.......\n"
			+ "..O..O....\n"
			+ ".......O..\n"
			+ "...O......\n"
			+ "..........\n"
			+ ".......O.O\n"
			+ "...O.O....\n"
			+ ".......O..\n"
			+ ".........$"};
	private class Coord{
		public int x, y;
		public Coord(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	private class Node{
		public Coord c1, c2, c3, c4;
		public boolean valid = false;
		public boolean containsEnd = false;
		public Node(Maze maze, Coord c1){
			this.c1 = c1; 							// Upper left
			this.c2 = new Coord(c1.x, c1.y+1); 		// Upper right
			this.c3 = new Coord(c1.x+1, c1.y); 		// Lower left
			this.c4 = new Coord(c1.x+1, c1.y+1);	// Lower right
			try {
				char ch1 = maze.maze[c1.x][c1.y];
				char ch2 = maze.maze[c2.x][c2.y];
				char ch3 = maze.maze[c3.x][c3.y];
				char ch4 = maze.maze[c4.x][c4.y];
				String str = ""+ ch1 + ch2 + ch3 + ch4;
				if(!str.contains('O'+"")){
//					System.out.println(str);
					valid = true;
				}else{
//					System.out.println(str);
				}
				if(str.contains('$'+"")){
					containsEnd = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				valid = false;
			}
		}
		public boolean equals(Object x){
			if(x instanceof Node){
				return c1.x == ((Node)x).c1.x && c1.y == ((Node)x).c1.y;
			}
			return false;
		}
	}
	private class Vertex{
		Node from, to;
		public boolean traversed = false;
		public Vertex(Node n1, Node n2){
			this.from = n1;
			this.to = n2;
		}
		public boolean equals(Object x){
			if(x instanceof Vertex){
				return (from.equals(((Vertex) x).from) && to.equals(((Vertex) x).to))
						|| (from.equals(((Vertex) x).to) && to.equals(((Vertex) x).from));
			}
			return false;
		}
	}
	private class Maze {
		char[][] maze = new char[10][10];
		Node ogreNode;
		Set<Vertex> vertices = new HashSet<Vertex>();
		
		public Maze(int choice) {
			int row = 0, col = 0;
			for (char ch : mazes[choice].toCharArray()) {
				if(ch != '\n'){
					if(ogreNode == null && ch == '@'){
						ogreNode = 	new Node(this, 	new Coord(row, col));
					}
					maze[row][col++] = ch; 
				}else{
					row++;
					col=0;
				}
			}
		}
		
		public Node getStart(){
			return ogreNode;
		}
		
		public Vertex tryToMove(Node from, Node to){
			if(!from.valid || !to.valid){
				return null;
			}
			Vertex v = new Vertex(from, to);
			if(vertices.contains(v)){
				return null;
			}else{
				vertices.add(v);
			}
			return v;
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					sb.append(maze[i][j]);
				}
				sb.append('\n');
			}
			return sb.toString();
		}
		
	}
	
	public ArrayList<Vertex> findPath(Maze maze, Vertex v, ArrayList<Vertex> path){
		Node n;
		if(v == null && path == null){
			path = new ArrayList<Vertex>();
			n = maze.getStart();
		}else{
			n = v.to;
			System.out.println(n.c1.x + " " + n.c1.y);
		}
		if(n.containsEnd){
			return path;
		}
		Vertex left = maze.tryToMove(n, new Node(maze, new Coord(n.c1.x, n.c1.y-1)));
		Vertex right = maze.tryToMove(n, new Node(maze, new Coord(n.c1.x, n.c1.y+1)));
		Vertex up = maze.tryToMove(n, new Node(maze, new Coord(n.c1.x-1, n.c1.y)));
		Vertex down = maze.tryToMove(n, new Node(maze, new Coord(n.c1.x+1, n.c1.y)));
		ArrayList<Vertex> found = null;
		if(right != null && !path.contains(right) && found == null){
			ArrayList<Vertex> rpath = new ArrayList<Vertex>(path);
			rpath.add(right);
			found = findPath(maze, right, rpath);
		}
		if(left != null && !path.contains(left) && found == null){
			ArrayList<Vertex> lpath = new ArrayList<Vertex>(path);
			lpath.add(left);
			found = findPath(maze, left, lpath);
		}
		if(down != null && !path.contains(down) && found == null){
			ArrayList<Vertex> dpath = new ArrayList<Vertex>(path);
			dpath.add(down);
			found = findPath(maze, down, dpath);
		}
		if(up != null && !path.contains(up) && found == null){
			ArrayList<Vertex> upath = new ArrayList<Vertex>(path);
			upath.add(up);
			found = findPath(maze, up, upath);
		}
		return found;
	}

	public static void main(String[] args) {
		Maze maze = new Intermediate211().new Maze(2);
		ArrayList<Vertex> path = new Intermediate211().findPath(maze, null, null);
		if(path == null){
			System.out.println("No path");
			System.exit(0);
		}
		System.out.println(path.size());
		System.out.println(maze);
		System.out.println();

		for (Vertex v : path) {
			maze.maze[v.from.c1.x][v.from.c1.y] = '&';
			maze.maze[v.from.c2.x][v.from.c2.y] = '&';
			maze.maze[v.from.c3.x][v.from.c3.y] = '&';
			maze.maze[v.from.c4.x][v.from.c4.y] = '&';
			maze.maze[v.to.c1.x][v.to.c1.y] = '&';
			maze.maze[v.to.c2.x][v.to.c2.y] = '&';
			maze.maze[v.to.c3.x][v.to.c3.y] = '&';
			maze.maze[v.to.c4.x][v.to.c4.y] = '&';
			System.out.println(maze);
		}
		System.out.println(maze);
	}
}
