package wzm;

public class SudokuSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] board = { "..9748...".toCharArray(), "7........".toCharArray(), ".2.1.9...".toCharArray(),
				"..7...24.".toCharArray(), ".64.1.59.".toCharArray(), ".98...3..".toCharArray(),
				"...8.3.2.".toCharArray(), "........6".toCharArray(), "...2759..".toCharArray() };

		solveSudoku(board);
		System.out.println("done");
	}

	/**
	 * Solved by depth first search. For every empty cell, enumerate its value from 1 to 9.
	 * Before setting board[i][j] by 'value', we need to check is 'value' suitable for this cell.
	 * Be more specific, we need to check has 'value' appeared in row[i]/column[j]/block[i][j].
	 * If this value is valid, search the next cell.
	 * Backtracking is realized by reset board[i][j] to '.'
	 * Time complexity could be O(9^4^81) in worst case. However, practically, as a few cells have been
	 * filled and the restriction of sudoku, the time cost will not be so enormous.
	 * @param board
	 */

	public static void solveSudoku(char[][] board) {

		solve(board);
	}

	private static boolean solve(char[][] board) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					for (int k = 1; k <= 9; k++) {
						char value = intToChar(k);
						if (isValid(board, i, j, value)) {
							board[i][j] = value;
							if (solve(board)) {
								return true;
							}
							board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}

		return true;
	}

	private static boolean isValid(char[][] board, int row, int column, char value) {

		for (int i = 0; i < 9; i++) {
			if (board[row][i] == value || board[i][column] == value) {
				return false;
			}
		}
		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
			for (int j = column / 3 * 3; j < column / 3 * 3 + 3; j++) {
				if (board[i][j] == value) {
					return false;
				}
			}
		}

		return true;
	}

	private static char intToChar(int value) {

		return (char) (value + '0');
	}
}
