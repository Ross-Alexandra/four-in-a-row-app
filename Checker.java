import java.util.Arrays;
import java.util.stream.*;

class Checker {
	
	public static int[][] get_sets(int[][] grid, int[] pos) {
		/*
		Compiles a position and a grid into a set of values
		in a grid around the position. If the sum of any of these
		set is 4 or -4, then there has been a winner.
		
		args:
			int[][] grid: The grid to check for a solution.
			int[] pos: A position to check around.
		
		returns:
			A set of grids to later sum.
		
		NOTE:
			This process can be improved on, and most likely
			will be after this file has been saved.
		*/
	
		// Create an array to hold these sets.
		int[][] sets = new int[256][4];
		
		// Used to count the number of sets.
		int num_sets = 0;
		
		// Check the row
		for (int i = 0; i < 4; i++) {
			if ((pos[1] - i >= 0)  && ((pos[1] - i) + 3 < 7)) {
				sets[num_sets][0] = grid[pos[0]][pos[1] - i];
				sets[num_sets][1] = grid[pos[0]][pos[1] - i + 1];
				sets[num_sets][2] = grid[pos[0]][pos[1] - i + 2];
				sets[num_sets++][3] = grid[pos[0]][pos[1] - i + 3];
			}
		}
		
		// Check the column
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && ((pos[0] - i + 3) < 6)) {
				sets[num_sets][0] = grid[pos[0] - i][pos[1]];
				sets[num_sets][1] = grid[pos[0] - i + 1][pos[1]];
				sets[num_sets][2] = grid[pos[0] - i + 2][pos[1]]; 
				sets[num_sets++][3] = grid[pos[0] - i + 3][pos[1]];
			}
		}
		
		// Check top left to bottom right diag.
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && (pos[1] - i >= 0) && (pos[0] - i + 3 < 6) && (pos[1] - i + 3 < 7)) {
				sets[num_sets][0] = grid[pos[0] - i][pos[1] - i];
				sets[num_sets][1] = grid[pos[0] - i + 1][pos[1] - i + 1];
				sets[num_sets][2] = grid[pos[0] - i + 2][pos[1] - i + 2]; 
				sets[num_sets++][3] = grid[pos[0] - i + 3][pos[1] - i + 3];
			}
		}
		
		// Check top right to bottom left diag.
		for (int i = 0; i < 4; i++) {
			if ((pos[0] - i >= 0) && (pos[1] + i < 7) && (pos[0] - i + 3) < 6 && (pos[1] + i - 3 >= 0)) {
				sets[num_sets][0] = grid[pos[0] - i][pos[1] + i];
				sets[num_sets][1] = grid[pos[0] - i + 1][pos[1] + i - 1];
				sets[num_sets][2] = grid[pos[0] - i + 2][pos[1] + i - 2]; 
				sets[num_sets++][3] = grid[pos[0] - i + 3][pos[1] + i - 3];
			}
		}
		
		//Fill the rest of the sets with 0.
		for (int i = num_sets; i < 108; i++) {
			sets[i][0] = 0;
			sets[i][1] = 0;
			sets[i][2] = 0;
			sets[i][3] = 0;
		}
		
		return sets;
	}
	
	public static int check(int[][] grid, int[] pos) {
		/*
		Gets the sets around pos in grid to see if any
		of them sum to 4 or -4. If so, then someone has won.
		
		args:
			int[][] grid: The grid to check for a solution.
			int[] pos: A position to check around.
		
		returns:
			1 if the 1's player has won,
			-1 if the -1's play has won,
			0 if no player has won.
		*/
	
		// Create an array to hold the sets in.
		int [][] value_sets = new int[256][4];
		
		// Get the sets.
		value_sets = get_sets(grid, pos);
		
		// Loop over each set, and sum them. If its
		//  - contents sum to 4, then return 1, 
		//  - contents sum to -4, then return -1
		// otherwise move onto the next set.
		for (int[] set: value_sets) {
			int sum = set[0] + set[1] + set[2] + set[3];
			
			if (sum == 4) {
				return 1;
			}
			else if (sum == -4) {
				return -1;
			}
		}
		
		// No one has won.
		return 0;
	}
	
	public static void main(String[] args) {
		
		//A Grid where there is a win in a row.
		int[][] row_grid = {
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0,-1, 0, 0}, 
			{0, 0, 0, 0,-1, 0, 0}, 
			{0, 0,-1, 1,-1, 0, 0}, 
			{0, 0, 1, 1, 1, 1, 0}
		};
		
		// A grid where every position is a winning position.
		int[][] full_grid = {
			{1, 1, 1, 1, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}, 
			{1, 1, 1, 1, 1, 1, 1}
		};
		
		// A grid where there is a \ win.
		int[][] tlbr_grid = {
			{0, 0, 0, 0, 0, 0, 0}, 
			{0,-1, 0, 0, 0, 0, 0}, 
			{0, 0,-1, 0,-1, 0, 0}, 
			{0, 0, 0,-1,-1, 0, 0}, 
			{0, 0,-1, 1,-1, 0, 0}, 
			{0, 0, 1, 1, 0, 1, 0}
		};
		
		// A grid where there is a / win.
		int[][] trbl_grid = {
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0,-1, 1, 0}, 
			{0, 0, 0, 0, 1, 0, 0}, 
			{0, 0,-1, 1,-1, 0, 0}, 
			{0, 0, 1, 1, 0, 1, 0}
		};
		
		// A grid with no values in it.
		int[][] empty_grid = {
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}
		};
		
		// A grid where there are no solutions.
		int[][] nosoln_grid = {
			{1, -1, 1, -1, 1, -1, 1}, 
			{1, -1, 1, -1, 1, -1, 1}, 
			{-1, 1, -1, 1, -1, 1, -1}, 
			{-1, 1, -1, 1, -1, 1, -1}, 
			{1, -1, 1, -1, 1, -1, 1}, 
			{1, -1, 1, -1, 1, -1, 1}
		};
		
		// Set the current grid to the grid to test.
		int[][] working_grid = nosoln_grid;

		// Test if any position has a win, and if so, print
		// that position.
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				int[] new_pos = {row, col};
				if (check(working_grid, new_pos) != 0) {
					System.out.print(new_pos[0]);
					System.out.print(", ");
					System.out.println(new_pos[1]);
				}
			}
		}
	}
}