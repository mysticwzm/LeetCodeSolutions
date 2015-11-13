package wzm;

import java.util.List;
import java.util.ArrayList;

public class SpiralMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		System.out.println(spiralOrder(matrix));
	}

	/**
	 * Solved by walking simulation.
	 * Time complexity is O(mn).
	 * @param matrix the original matrix
	 * @return matrix in spiral order
	 */

	public static List<Integer> spiralOrder(int[][] matrix) {

		List<Integer> numberList = new ArrayList<Integer>();

		if (matrix.length == 0) {
			return numberList;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][] walked = new boolean[m][n];
		int i = 0;
		int j = 0;
		final int RIGHT = 0;
		final int DOWN = 1;
		final int LEFT = 2;
		final int UP = 3;
		int direction = RIGHT;
		while (numberList.size() < m * n) {
			numberList.add(matrix[i][j]);
			walked[i][j] = true;
			direction = nextDirection(direction, walked, i, j, m, n);
			if (direction == RIGHT) {
				j++;
			} else if (direction == DOWN) {
				i++;
			} else if (direction == LEFT) {
				j--;
			} else if (direction == UP) {
				i--;
			}
		}

		return numberList;
	}

	private static int nextDirection(int direction, boolean[][] walked, int i, int j, int m, int n) {

		final int RIGHT = 0;
		final int DOWN = 1;
		final int LEFT = 2;
		final int UP = 3;

		if (direction == RIGHT) {
			if (j + 1 < n && !walked[i][j + 1]) {
				return RIGHT;
			} else {
				return DOWN;
			}
		}
		if (direction == DOWN) {
			if (i + 1 < m && !walked[i + 1][j]) {
				return DOWN;
			} else {
				return LEFT;
			}
		}
		if (direction == LEFT) {
			if (j - 1 > -1 && !walked[i][j - 1]) {
				return LEFT;
			} else {
				return UP;
			}
		}
		if (direction == UP) {
			if (i - 1 > -1 && !walked[i - 1][j]) {
				return UP;
			} else {
				return RIGHT;
			}
		}

		return -1;
	}
}
