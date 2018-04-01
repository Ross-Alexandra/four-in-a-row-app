import java.util.Scanner;

class FourInARow {

	private int[][] puzzle;
	private static final int COL_MAX = 7;
	private static final int ROW_MAX = 6;
	private String player1;
	private String player2;
	private int difficulty;
	private int[] numInCols;
	private int player_num;
	private String winner;
	
	public FourInARow(String name1, String name2) {
		puzzle = new int[6][7];
		numInCols = new int[7];
		
		player1 = name1;
		player2 = name2;
		winner = null;
		
		player_num = 1;
	}
	
	public FourInARow(String name, int Difficulty) {
		puzzle = new int[6][7];
		numInCols = new int[7];
		
		player1 = name;
		winner = null;
		
		this.difficulty = Difficulty;
	}
	
	private int check(int[] pos) {
		/*
		Searches the this.puzzle to see if there is a win at position
		pos.
		
		args:
			int[][] this.puzzle: The this.puzzle to check for a win in.
			int[] pos: The position to find a win around.
			
		returns:
			1 if the 1's have a win.
			-1 if the -1's have a win.
			0 if there are no wins.
		*/
		
		// Check the row
		for (int i = 0; i < 4; i++) {
			if ((pos[1] - i >= 0)  && ((pos[1] - i) + 3 < this.COL_MAX)) {
				
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = this.puzzle[pos[0]][pos[1] - i] + this.puzzle[pos[0]][pos[1] - i + 1] + this.puzzle[pos[0]][pos[1] - i + 2] + this.puzzle[pos[0]][pos[1] - i + 3];
				
				if (sum == 4) {
					return 1;
				}
				else if (sum == -4) {
					return -1;
				}
			}
		}
		
		// Check the column
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && ((pos[0] - i + 3) < this.ROW_MAX)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = this.puzzle[pos[0] - i][pos[1]] + this.puzzle[pos[0] - i + 1][pos[1]] + this.puzzle[pos[0] - i + 2][pos[1]] + this.puzzle[pos[0] - i + 3][pos[1]];
			
				if (sum == 4) {
					return 1;
				}
				else if (sum == -4) {
					return -1;
				}
			}
		}
		
		// Check top left to bottom right diag.
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && (pos[1] - i >= 0) && (pos[0] - i + 3 < this.ROW_MAX) && (pos[1] - i + 3 < this.COL_MAX)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = this.puzzle[pos[0] - i][pos[1] - i] + this.puzzle[pos[0] - i + 1][pos[1] - i + 1] + this.puzzle[pos[0] - i + 2][pos[1] - i + 2] + this.puzzle[pos[0] - i + 3][pos[1] - i + 3];
				
				if (sum == 4) {
					return 1;
				}
				else if (sum == -4) {
					return -1;
				}
			}
		}
		
		// Check top right to bottom left diag.
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && (pos[1] + i < this.COL_MAX) && (pos[0] - i + 3) < this.ROW_MAX && (pos[1] + i - 3 >= 0)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = this.puzzle[pos[0] - i][pos[1] + i] + this.puzzle[pos[0] - i + 1][pos[1] + i - 1] + this.puzzle[pos[0] - i + 2][pos[1] + i - 2] + this.puzzle[pos[0] - i + 3][pos[1] + i - 3];
				
				if (sum == 4) {
					return 1;
				}
				else if (sum == -4) {
					return -1;
				}
			}
		}
		
		return 0;
	}
	
	public boolean playCol(int colNum) throws ColumnFullException {
		if (this.ROW_MAX - 1 < numInCols[colNum]) {
			throw new ColumnFullException(colNum);
		}
		
		// ROW_MAX - 1 gives 5. which is the index of the bottom-most row.
		this.puzzle[(this.ROW_MAX - 1) - numInCols[colNum]][colNum] = player_num;
		// Change the current player to the other player.
		// ie) if player_num is 1 (first player) set it to
		// -1 (second player) and visa versa.
		this.player_num *= -1;
		
		// Return whether this was a winning move or not.
		int[] pos = {(this.ROW_MAX - 1) - numInCols[colNum], colNum};
		
		int win = this.check(pos);
		if (win == 1) {
			this.winner = this.player1;
			return true;
		}
		else if (win == -1) {
			this.winner = this.player2;
			return true;
		}
		
		this.numInCols[colNum]++;
		return false;
	}
	
	public String get_winner() {
		return this.winner;
	}
	
	public int get_difficulty() {
		return this.difficulty;
	}
	
	public String curPlayer() {
		if (player_num == 1) {
			return this.player1;
		}
		
		return this.player2;
		
	}
	
	public String toString() {
		// Maximum of 4 characters per column, + 3 for "| " + 1 for "\n"
		// time the 6 rows. Plus 30 for the headers. Plus the length of
		// each player's name, plus 12 for showing those player's colors
		StringBuilder builder = new StringBuilder((((4 * 7) + 4) * 6) + 30 + this.player1.length() + this.player2.length() + 12);
		
		builder.append("\n");
		builder.append(this.player1 + " = b; " + this.player2 + " = r;\n");
		builder.append("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n");
		
		for (int [] row: this.puzzle) {
			builder.append("| ");
			for (int cell: row) {
				char player;
				if (cell == 1) {
					player = 'b';
				}
				else if (cell == -1) {
					player = 'r';
				}
				else {
					player = ' ';
				}
				
				//should be player !!!
				builder.append(player + " | ");
			}
			builder.append("\n");
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		
		// Create a 2 player game.
		FourInARow game = new FourInARow("Ross", "Evan");
		
		// Create a 1 player game with a computer of difficulty 1.
		FourInARow comp = new FourInARow("Ross", 1);
		
		Scanner input = new Scanner(System.in);
		boolean hasWon;
		
		do {
			System.out.println(game);
			System.out.println("Which Column would " + game.curPlayer() + " like to play?");
			int colNum = input.nextInt() - 1;
			
			if (colNum < 0 || colNum > 6) {
				System.out.println("Invalid Column number.");
				hasWon = false;
			}
			else {
				try {
					
					hasWon = game.playCol(colNum);
				}
				catch (ColumnFullException e) {
					System.out.println(e);
					hasWon = false;
				}
			}
		} while (!hasWon);
		
		System.out.println(game);
		System.out.println(game.winner + " Has won!");
	}
}