package wzm;

public class UniquePath2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] obstacleGrid = new int[][] { { 0 }, { 1 } };

		System.out.println(uniquePathsWithObstacles(obstacleGrid));
	}

	/**
	 * Solved by dynamic programming, similar to UniquePath.
	 * The only difference is if obstacleGrid[i][j] == 1, then dp[i][j] == 0.
	 * Time complexity is O(mn).
	 * @param obstacleGrid obstacleGrid which consists of 0 and 1
	 * @return the number of unique paths
	 */

	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[] paths = new int[n];

		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] == 0) {
				paths[i] = 1;
			} else {
				break;
			}
		}
		for (int i = 1; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				paths[0] = 0;
			}
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					paths[j] = 0;
				} else {
					paths[j] += paths[j - 1];
				}
			}
		}

		return paths[n - 1];
	}
}
