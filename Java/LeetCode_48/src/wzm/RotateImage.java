package wzm;

public class RotateImage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		rotate(matrix);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Solved by 2 steps : 1. transpose by diagonal 2. up and down reversal
	 * Time complexity is O(n^2).
	 * @param matrix
	 */

	public static void rotate(int[][] matrix) {

		diagonalSymmetricTranspose(matrix);
		upAndDownReversal(matrix);
	}

	private static void diagonalSymmetricTranspose(int[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (i + j < matrix.length) {
					matrixSwap(matrix, i, j, matrix.length - 1 - j, matrix.length - 1 - i);
				} else {
					break;
				}
			}
		}
	}

	private static void upAndDownReversal(int[][] matrix) {

		int headLine = 0;
		int tailLine = matrix.length - 1;

		while (headLine < tailLine) {
			lineSwap(matrix, headLine, tailLine);
			headLine++;
			tailLine--;
		}
	}

	private static void matrixSwap(int[][] matrix, int i1, int j1, int i2, int j2) {

		int temp = matrix[i1][j1];
		matrix[i1][j1] = matrix[i2][j2];
		matrix[i2][j2] = temp;
	}

	private static void lineSwap(int[][] matrix, int line1, int line2) {

		int[] temp = matrix[line1];
		matrix[line1] = matrix[line2];
		matrix[line2] = temp;
	}
}
