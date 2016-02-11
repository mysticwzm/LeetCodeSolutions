package wzm;

public class MinimumPathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] grid = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		System.out.println(minPathSum(grid));
	}

	/**
	 * Solved by dynamic programming, still similar to UniquePath.
	 * Time complexity is O(mn).
	 * @param grid grid contains values
	 * @return minimum path sum
	 */

	public static int minPathSum(int[][] grid) {

		int m = grid.length;
		int n = grid[0].length;
		int[] minSum = new int[n];

		minSum[0] = grid[0][0];
		for (int i = 1; i < n; i++) {
			minSum[i] = minSum[i - 1] + grid[0][i];
		}
		for (int i = 1; i < m; i++) {
			minSum[0] = minSum[0] + grid[i][0];
			for (int j = 1; j < n; j++) {
				minSum[j] = Math.min(minSum[j], minSum[j - 1]) + grid[i][j];
			}
		}

		return minSum[n - 1];
	}
}
