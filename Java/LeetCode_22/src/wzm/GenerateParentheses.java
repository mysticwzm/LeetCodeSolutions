package wzm;

import java.util.List;
import java.util.ArrayList;

public class GenerateParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n = 3;

		System.out.println(generateParenthesis(n).toString());
	}

	/**
	 * Solved by backtracking.
	 * Time complexity is O(?).
	 * @param n
	 * @return
	 */

	public static List<String> generateParenthesis(int n) {

		List<String> stringList = new ArrayList<String>();

		recursiveGenerateParenthesis(n, 0, 0, "", stringList);

		return stringList;
	}

	private static void recursiveGenerateParenthesis(int n, int numLeft, int pairs, String parenthesis,
			List<String> stringList) {

		if (n == pairs) {
			stringList.add(parenthesis);
			return;
		}
		if (numLeft < n - pairs) {		// if we can add more '(', then do it
			recursiveGenerateParenthesis(n, numLeft + 1, pairs, parenthesis + '(', stringList);
			if (numLeft > 0) {			// if there is '(' left ready for matching, then match it
				recursiveGenerateParenthesis(n, numLeft - 1, pairs + 1, parenthesis + ')', stringList);
			}
		}
		if (numLeft == n - pairs && numLeft > 0) {	//if there is no chance to add '(', but some '(' left, then match it
			recursiveGenerateParenthesis(n, numLeft - 1, pairs + 1, parenthesis + ')', stringList);
		}
	}
}
