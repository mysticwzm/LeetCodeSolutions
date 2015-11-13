package wzm;

import java.util.List;
import java.util.ArrayList;

public class NQueens2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 8;

		System.out.println(totalNQueens(n));
	}
	
	/**
	 * Solved by depth first search and backtracking.
	 * Time complexity is O(n^3).
	 * @param n number of queens
	 * @return number of distinct solutions
	 */
	
	public static int totalNQueens(int n) {
		
		List<Integer> queens = new ArrayList<Integer>();
		int[] count = new int[1];

		setNQueens(queens, n, count);

		return count[0];
	}

	private static void setNQueens(List<Integer> queens, int n, int[] count) {

		if (queens.size() == n) {
			count[0]++;
			return;
		}
		int rowNum = queens.size();
		for (int i = 0; i < n; i++) {
			boolean valid = true;
			for (int j = 0; j < rowNum; j++) {
				if (i == queens.get(j) || i + rowNum == j + queens.get(j) || rowNum - i == j - queens.get(j)) {
					valid = false;
					break;
				}
			}
			if (valid) {
				queens.add(i);
				setNQueens(queens, n, count);
				queens.remove(queens.size() - 1);
			}
		}
	}
}
