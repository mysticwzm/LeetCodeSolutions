package wzm;

public class LongestValidParentheses {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "(()))())(";

		System.out.println(longestValidParentheses(s));
	}

	/**
	 * Solved with stack. push the index of '(' into stack.
	 * For '(', if there is no extra ')' in the previous parentheses, it is still a continuous parentheses.
	 * Otherwise, the last extra ')' plays a role as a broken point between previous and new parentheses.
	 * For ')', if there is any '(' left in the stack, it is a continuous parentheses.
	 * Otherwise, the current paraentheses ends.
	 * Time complexity is O(n), one pass.
	 * There is another O(n) solution by using dynamic programming:
	 * if (S[i] == '(') open++;				// for all '('s, V[i] here is 0
     * if (S[i] == ')' && open > 0) {		// only ')' stands for sign of match
     *     V[i] = 2+ V[i-1]; 
     *     if(i-V[i]>0)
     *         V[i] += V[i - V[i]];
     *     open--;
     * }
     * open represents number of '(' left, V[i] is the same as longest[i]
     * Simple example : ()(())
     * for ')' at index 5:
     * V[i] = 2 + V[i - 1], (()) is matched.
     * V[i] += V[i - V[i]], previous () is matched.
     * for ')' at index 4:
     * V[i] = 2 + V[i - 1], () in (() is matched.
     * V[i] += V[i - V[i]], '(' at index 2 has not been mached yet, V[i - V[i]] is 0
	 * @param s
	 * @return
	 */

	public static int longestValidParentheses(String s) {

		if (s.length() == 0) {
			return 0;
		}

		int[] longest = new int[s.length()];
		int[] bracketStack = new int[s.length()];
		int top = -1;
		int leftBrackets = 0;
		int breakPoint = -1;
		int maxLength = 0;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {						// if s[i] is '(', push it into stack
				if (leftBrackets < 0) {						// if the parentheses has broken somewhere previously
					leftBrackets = 0;						// start a new parentheses
					breakPoint = i - 1;						// set the broken point as i - 1
				}											// example of broken case:
				leftBrackets++;								// "() ) ()", parentheses breaks at second ')'
				top++;
				bracketStack[top] = i;
				if (i > 0) {								// '(' means no match, longest[i] = longest[i - 1]
					longest[i] = longest[i - 1];
				}
			} else {
				leftBrackets--;								// if s[i] is ')', seek for matching
				if (top >= 0) {								// there is any '(' left in the stack, match it as a pair
					top--;									// pop a '(' out
					if (top >= 0) {							// after poping, if there is '(' still left
						longest[i] = i - bracketStack[top];	// this is a continuous parenthese
					} else {
						longest[i] = i - breakPoint;		// or all '('s have been matched
					}
				} else {
					if (i > 0) {
						longest[i] = longest[i - 1];		// this ')' breaks continuous parentheses
					}
				}
			}
			maxLength = Math.max(maxLength, longest[i]);
		}

		return maxLength;
	}
}
