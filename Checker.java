import java.util.Arrays;
import java.util.stream.*;

class Checker {
	
	public static int check(int[][] grid, int[] pos) {
		/*
		Searches the grid to see if there is a win at position
		pos.
		
		args:
			int[][] grid: The grid to check for a win in.
			int[] pos: The position to find a win around.
			
		returns:
			1 if the 1's have a win.
			-1 if the -1's have a win.
			0 if there are no wins.
		*/
		
		// Check the row
		for (int i = 0; i < 4; i++) {
			if ((pos[1] - i >= 0)  && ((pos[1] - i) + 3 < 7)) {
				
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = grid[pos[0]][pos[1] - i] + grid[pos[0]][pos[1] - i + 1] + grid[pos[0]][pos[1] - i + 2] + grid[pos[0]][pos[1] - i + 3];
				
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
			if ((pos[0] - i >= 0) && ((pos[0] - i + 3) < 6)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = grid[pos[0] - i][pos[1]] + grid[pos[0] - i + 1][pos[1]] + grid[pos[0] - i + 2][pos[1]] + grid[pos[0] - i + 3][pos[1]];
			
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
			if ((pos[0] - i >= 0) && (pos[1] - i >= 0) && (pos[0] - i + 3 < 6) && (pos[1] - i + 3 < 7)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = grid[pos[0] - i][pos[1] - i] + grid[pos[0] - i + 1][pos[1] - i + 1] + grid[pos[0] - i + 2][pos[1] - i + 2] + grid[pos[0] - i + 3][pos[1] - i + 3];
				
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
			if ((pos[0] - i >= 0) && (pos[1] + i < 7) && (pos[0] - i + 3) < 6 && (pos[1] + i - 3 >= 0)) {
				// Sum the values so that we can check if this is a 4, -4, or otherwise
				int sum = grid[pos[0] - i][pos[1] + i] + grid[pos[0] - i + 1][pos[1] + i - 1] + grid[pos[0] - i + 2][pos[1] + i - 2] + grid[pos[0] - i + 3][pos[1] + i - 3];
				
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
		
		// A grid where there is a col win.
		int[][] col_grid = {
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0,-1, 0, 0}, 
			{0, 0, 0, 0,-1, 0, 0}, 
			{0, 0,-1, 1,-1, 0, 0}, 
			{0, 0, 1, 1, -1, 1, 0}
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