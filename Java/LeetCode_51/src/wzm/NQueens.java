package wzm;

import java.util.List;
import java.util.ArrayList;

public class NQueens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 4;

		List<List<String>> boardConfigurations = solveNQueens(n);
		System.out.println(boardConfigurations.size());
		for (int i = 0; i < boardConfigurations.size(); i++) {
			for (int j = 0; j < n; j++) {
				System.out.println(boardConfigurations.get(i).get(j));
			}
			System.out.println();
		}
	}

	/**
	 * Solved by depth first search and backtracking.
	 * Time complexity is O(n^3).
	 * @param n	number of queens
	 * @return all board configurations
	 */

	public static List<List<String>> solveNQueens(int n) {

		List<List<String>> boardConfigurations = new ArrayList<List<String>>();
		List<Integer> queens = new ArrayList<Integer>();

		setNQueens(boardConfigurations, queens, n);

		return boardConfigurations;
	}

	private static void setNQueens(List<List<String>> boardConfigurations, List<Integer> queens, int n) {

		if (queens.size() == n) {
			addConfiguration(boardConfigurations, queens);
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
				setNQueens(boardConfigurations, queens, n);
				queens.remove(queens.size() - 1);
			}
		}
	}

	private static void addConfiguration(List<List<String>> boardConfigurations, List<Integer> queens) {

		List<String> boardConfiguration = new ArrayList<String>();
		for (int i = 0; i < queens.size(); i++) {
			boardConfiguration.add(generateLine(queens.get(i), queens.size()));
		}
		boardConfigurations.add(boardConfiguration);
	}

	private static String generateLine(int queen, int n) {

		char[] line = new char[n];
		for (int i = 0; i < n; i++) {
			line[i] = i == queen ? 'Q' : '.';
		}

		return String.valueOf(line);
	}
}
