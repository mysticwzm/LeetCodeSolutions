package wzm;

public class ValidSudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] board = { "..9748...".toCharArray(), "7........".toCharArray(), ".2.1.9...".toCharArray(),
				"..7...24.".toCharArray(), ".64.1.59.".toCharArray(), ".98...3..".toCharArray(),
				"...8.3.2.".toCharArray(), "........6".toCharArray(), "...2759..".toCharArray() };

		System.out.println(isValidSudoku(board));
	}

	/**
	 * Solved by bit operation. The basic idea is every number(1 - 9) stands for one bit.
	 * For example, 000111000 means, 4, 5, 6 have appeared in this row/column/block.
	 * Therefore, row & number | column & number | block & number should be 0 if number never appears in
	 * that row/column/block.
	 * Time complexity is O(81), 9 ^ 2.
	 * @param board
	 * @return
	 */

	public static boolean isValidSudoku(char[][] board) {

		int[] row = new int[9];
		int[] column = new int[9];
		int[] block = new int[9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int number = convertToBinary(charToInt(board[i][j]));
				if (((row[i] & number) | (column[j] & number) | (block[locateBlock(i, j)] & number)) > 0) {
					return false;
				}
				row[i] |= number;
				column[j] |= number;
				block[locateBlock(i, j)] |= number;
			}
		}

		return true;
	}

	private static int locateBlock(int row, int column) {

		return row / 3 * 3 + column / 3;
	}

	private static int convertToBinary(int number) {

		return 1 << (number - 1);
	}

	private static int charToInt(char ch) {

		return ch - '0';
	}
}
