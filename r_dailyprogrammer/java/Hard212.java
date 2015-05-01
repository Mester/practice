import java.io.*;
import java.util.*;

public class Hard212 {
	public static class Maze {
		public boolean[][] maze; // true for empty/walkable and false for wall
		public int n, m; // N x M maze

		public Maze(int x, int y) {
			maze = new boolean[x][y];
			n = x;
			m = y;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[i].length; j++) {
					if (maze[i][j]) {
						sb.append(" ");
					} else {
						sb.append("x");
					}
				}
				sb.append("\n");
			}
			return sb.toString();
		}

	}
	
	public static enum Direction { 	/*Implementation details ideas for this
	 								taken from http://codereview.stackexchange.com/a/42828 */
	    NORTH(0, -1, 0),
	    EAST(1, 0, 1),
	    SOUTH(2, 1, 0),
	    WEST(3, 0, -1);
		public final int dx, dy;
		private final int turnRightIndex, turnLeftIndex;

		Direction(int ordinal, int x, int y) {
			this.dx = x;
			this.dy = y;
			this.turnRightIndex = (ordinal+1) % 4;
			this.turnLeftIndex = (ordinal+3) % 4;
		}
		
		public Direction turnRight(){
			return values()[turnRightIndex];
		}
		
		public Direction turnLeft(){
			return values()[turnLeftIndex];
		}
	}
	
	public static class Walker {
		private String path;
		private Maze maze;
		private int x, y;

		public Walker(String path, Maze maze) {
			this.path = path;
			this.maze = maze;
		}
		
		public String attemptToWalk(int startingX, int startingY){

			Direction startingDirection = Direction.NORTH;
			StringBuilder sb = new StringBuilder();
			if(!maze.maze[startingX][startingY]){
				return "";
			}
			for (int i = 0; i < 4; i++) { // Try all 4 directions
				boolean hitWall = false;
				Direction direction = startingDirection;
				x = startingX;
				y = startingY;
				for(char c : path.toCharArray()){
					if(c == 'r'){
						direction = direction.turnRight();
					}else if (c == 'l'){
						direction = direction.turnLeft();
					}else{
						int steps = Integer.parseInt(""+c);
						for (int j = 0; j < steps; j++) {
							x += direction.dx;
							y += direction.dy;
							try {
								if(!maze.maze[x][y]) {
									hitWall = true;
								}
							} catch (ArrayIndexOutOfBoundsException e) {
								hitWall = true;
								break;
							}
						}
					}
				}
				if(!hitWall){
					// Coordinates reversed to match the output of the challenge
					sb.append("From (" +startingY+ ", " +startingX+ ") to (" +y+ ", " +x+ ")\n");
				}
				startingDirection = startingDirection.turnRight();
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		File source = new File("hard212.data");
		Scanner sc = null;
		try {
			sc = new Scanner(source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		Maze m[] = new Maze[6];
		Walker w[] = new Walker[6];
		int mazeNbr = 0;
		while (sc.hasNext()) {
			String str = sc.nextLine();
			int n = Integer.parseInt(str);
			for (int i = 0; i < n; i++) {
				str = sc.nextLine();
				if (m[mazeNbr] == null) {
					m[mazeNbr] = new Maze(n, str.length());
				}
				for (int j = 0; j < str.length(); j++) {
					m[mazeNbr].maze[i][j] = str.charAt(j) == ' ';
				}
			}
			str = sc.nextLine();
			w[mazeNbr] = new Walker(str, m[mazeNbr]);
			if (sc.hasNext()) {
				sc.nextLine(); // just to advance the empty line in data file
			}
			mazeNbr++;
		}
		for(int currentMaze = 5; currentMaze < 6; currentMaze++){
			System.out.println("Results for maze "+(currentMaze+1));
			for(int i = 1; i < m[currentMaze].n; i++){
				for(int j = 1; j < m[currentMaze].m; j++){
					System.out.print(w[currentMaze].attemptToWalk(i, j));
				}
			}
		}
	}

}
