package wzm;

public class WildcardMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isMatch("c", "*?*"));
		System.out.println(isMatch("hi", "*?"));
		System.out.println(isMatch("abc", "c*b*a*"));
		System.out.println(isMatch("ac", "*ab"));
		System.out.println(isMatch("ab", "*"));
		System.out.println(isMatch("abcdef", "ab*ef"));
	}

	/**
	 * Solved by 3 methods, recursion/recurrence/dynamic programming.
	 * Time complexities are respectively:
	 * Recursion -- O(n! * m!)
	 * Recurrence -- O(n * m)
	 * Dynamic programming -- O(n * m)
	 * @param s
	 * @param p
	 * @return
	 */

	public static boolean isMatch(String s, String p) {

		while (p.indexOf("**") != -1) {								// remove all redundant '*'
			p = p.replace("**", "*");
		}
		if (s.equals("")) {											// special cases
			return p.equals("") || p.equals("*");
		}
		if (p.equals("")) {
			return s.equals("");
		}

		// return isMatchRecursiveEdition(s, 0, p, 0);
		// return isMatchRecurrentEdition(s, p);
		return isMatchDynamicProgrammingEdition(s, p);
	}

	/**
	 * This method is slowest, recursive edition. 
	 * @param s
	 * @param indexS
	 * @param p
	 * @param indexP
	 * @return
	 */
	
	private static boolean isMatchRecursiveEdition(String s, int indexS, String p, int indexP) {

		if (indexS == s.length() || indexP == p.length()) {
			return indexS == s.length() && indexP == p.length();			// if s ends up and also p ends up, matched, retnru true
		}

		if ('a' <= p.charAt(indexP) && p.charAt(indexP) <= 'z') {			// p[indexP] in [a..z], indexS++, indexP++ or return false
			return s.charAt(indexS) == p.charAt(indexP) ? isMatchRecursiveEdition(s, indexS + 1, p, indexP + 1) : false;
		} else if (p.charAt(indexP) == '?') {								// p[indexP] == '?', indexS++, indexP++
			return isMatchRecursiveEdition(s, indexS + 1, p, indexP + 1);
		} else {
			while (indexS < s.length()) {									// '*' stands for empty sequence
				if (isMatchRecursiveEdition(s, indexS, p, indexP + 1)) {	
					return true;
				}
				indexS++;													// '*' stands for 1..n letters sequence
			}
			return isMatchRecursiveEdition(s, indexS, p, indexP + 1);		// last check for '*', s.length and indexP + 1
		}																	// the indexP + 1 is p.length, return true
	}

	/**
	 * Recurrent edition, O(n * m).
	 * @param s
	 * @param p
	 * @return
	 */

	private static boolean isMatchRecurrentEdition(String s, String p) {

		int indexS = 0;
		int indexP = 0;
		int starP = -1;
		int starS = -1;

		while (indexS != s.length()) {
			char letterP = indexP < p.length() ? p.charAt(indexP) : '\0';	// if p ends up, assign letterP as '\0'
			char letterS = s.charAt(indexS);
			if (letterP == '?' || letterP == letterS) {						// if letterP is '?' or equals letters
				indexS++;													// advance indexS and indexP
				indexP++;
			} else if (letterP == '*') {									// if letterP is '*'
				starP = indexP++;											// record the indexP which is next to '*'
				starS = indexS;												// record the indexS
			} else if (starP != -1) {										// if letterP is not '*' and not equal to 
				indexS = ++starS;											// letterS, backtrack to last '*', if existed
				indexP = starP;												// advance starS and assign it to indexS
			} else {
				return false;												
			}
		}
		while (indexP < p.length() && p.charAt(indexP) == '*') {			// skip the '*'(s) in the end
			indexP++;														
		}
		return indexP == p.length();										// if indexP == p.length(), p matches s
	}

	/**
	 * Dynamic programming, O(n * m).
	 * @param s
	 * @param p
	 * @return
	 */

	private static boolean isMatchDynamicProgrammingEdition(String s, String p) {

		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];

		match[0][0] = true;													// match[0][0] stands for "" and ""
		if (p.charAt(0) == '*') {											// if p == '*', all s match
			for (int i = 0; i <= s.length(); i++) {
				match[i][1] = true; 
			}
		}

		for (int i = 1; i <= s.length(); i++) {
			char letterS = s.charAt(i - 1);
			for (int j = 1; j <= p.length(); j++) {
				char letterP = p.charAt(j - 1);
				if (letterP != '*') {										// if letterP is not '*'
					match[i][j] = match[i - 1][j - 1] && (letterP == '?' || letterP == letterS);	
				} else {													// if letterP is '*'
					match[i][j] = match[i - 1][j] || match[i][j - 1];		// match[i - 1][j] means s[:i - 2] matches p[:j - 1] and '*' matches s[i - 1]
				}															// match[i][j - 1] means s[:i - 1] matches p[:j - 1] and '*' matches nothing
			}
		}

		return match[s.length()][p.length()];
	}
}
