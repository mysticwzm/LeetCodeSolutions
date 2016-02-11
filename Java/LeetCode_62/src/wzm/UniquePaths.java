package wzm;

public class UniquePaths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int m = 3;
		int n = 7;

		System.out.println(uniquePaths(m, n));
	}
	
	/**
	 * Solved by dynamic programming.
	 * The most straightforward way is dp[i][j] = dp[i - 1][j] + dp[i][j - 1], based on a 2D array.
	 * However, we can compress the array to 1D, Because the status dp[i][j] only related to dp[i - 1][j] and dp[i][j - 1].
	 * The state transition equation can be modified as dp[i] (new) = dp[i] (old) + dp[i - 1].
	 * The dp[i] at left side represents dp[i][j], dp[i] and dp[i - 1] at right side stands for dp[i - 1][j] and dp[i][j - 1] respectively.
	 * Time complexity is O(mn).
	 * @param m
	 * @param n
	 * @return
	 */
	
	public static int uniquePaths(int m, int n) {
		
		int[] paths = new int[n];

		for (int i = 0; i < n; i++) {
			paths[i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				paths[j] += paths[j - 1];
			}
		}

		return paths[n - 1];
	}
}
