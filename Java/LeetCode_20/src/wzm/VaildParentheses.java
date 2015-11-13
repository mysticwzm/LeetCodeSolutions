package wzm;

import java.util.ArrayList;

public class VaildParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = ")}{({))[{{[}";

		System.out.println(isVaild(s));
	}

	/**
	 * Store all left brackets in an array list, if the right bracket does not match the last left bracket
	 * or after scanning whole string, there is at least 1 left bracket left in the array list, whole string unmatched.
	 * Time complexity is O(n).
	 * @param s
	 * @return
	 */

	public static boolean isVaild(String s) {

		ArrayList<Character> leftBrackets = new ArrayList<Character>();
		String leftBracket = "([{";

		if ((s.length() & 1) == 1) {
			return false;
		}

		for (int i = 0; i < s.length(); i++) {
			Character bracket = s.charAt(i);
			if (leftBracket.indexOf(bracket) != -1) {					
				leftBrackets.add(bracket);								// if this bracket belongs to left brackets, add it to array list
			} else if (leftBrackets.size() > 0 && isMatched(bracket, leftBrackets.get(leftBrackets.size() - 1))) {
				leftBrackets.remove(leftBrackets.size() - 1);			// if this bracket belongs to right brakcets
			} else {													// and it matchs the last left bracket, remove the last left bracket
				return false;											// if this character is not bracket, return false
			}
		}

		if (leftBrackets.size() > 0) {									// if there is at least 1 left bracket left, return false
			return false;
		}
		return true;
	}

	private static boolean isMatched(char rightBracket, char leftBracket) {

		if (leftBracket == '(' && rightBracket == ')') {
			return true;
		} else if (leftBracket == '[' && rightBracket == ']') {
			return true;
		} else if (leftBracket == '{' && rightBracket == '}') {
			return true;
		}

		return false;
	}
}
