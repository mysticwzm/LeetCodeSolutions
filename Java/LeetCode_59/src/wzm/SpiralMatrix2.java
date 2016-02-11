package wzm;

public class SpiralMatrix2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 4;

		System.out.println(generateMatrix(n));
	}
	
	/**
	 * Solved similarly to SpiralMatrix. The order of direction is RIGHT->DOWN->LEFT->UP.
	 * Therefore, we can assign RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3. 
	 * Once the direction needs to be switched to next direction, next direction is (direction + 1) & 3.
	 * Time complexity is O(n^2).
	 * @param n side length of matrix
	 * @return generated matrix
	 */
	
	public static int[][] generateMatrix(int n) {
		
		int[][] matrix = new int[n][n];
		final int RIGHT = 0;
		final int DOWN = 1;
		final int LEFT = 2;
		final int UP = 3;
		int direction = RIGHT;
		int x = 0;
		int y = 0;

		for (int step = 1; step <= n * n; step++) {
			matrix[x][y] = step;
			direction = nextDirection(matrix, x, y, direction);
			switch (direction) {
				case RIGHT:
					y++;
					break;
				case DOWN:
					x++;
					break;
				case LEFT:
					y--;
					break;
				case UP:
					x--;
					break;
				default:
			}
		}

		return matrix;
	}

	private static int nextDirection(int[][] matrix, int x, int y, int direction) {

		int n = matrix.length;
		final int RIGHT = 0;
		final int DOWN = 1;
		final int LEFT = 2;
		final int UP = 3;
		boolean next = false;

		if (direction == RIGHT) {
			if (y + 1 >= n || matrix[x][y + 1] != 0) {
				next = true;
			}
		}
		if (direction == DOWN) {
			if (x + 1 >= n || matrix[x + 1][y] != 0) {
				next = true;
			}
		}
		if (direction == LEFT) {
			if (y - 1 < 0 || matrix[x][y - 1] != 0) {
				next = true;
			}
		}
		if (direction == UP) {
			if (x - 1 < 0 || matrix[x - 1][y] != 0) {
				next = true;
			}
		}
		if (!next) {
			return direction;
		} else {
			return (direction + 1) & 3;
		}
	}
}
