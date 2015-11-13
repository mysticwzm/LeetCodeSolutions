package wzm;

public class RegularExpressionMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(isMatch("aa", "a"));
		System.out.println(isMatch("aa", "aa"));
		System.out.println(isMatch("aaa", "aa"));
		System.out.println(isMatch("aa", "a*"));
		System.out.println(isMatch("aa", ".*"));
		System.out.println(isMatch("ab", ".*"));
		System.out.println(isMatch("aab", "c*a*b*"));
		System.out.println(isMatch("", ".*"));
		System.out.println(isMatch("aaa", "ab*a"));
		System.out.println(isMatch("aaa", "aaaa"));
		System.out.println(isMatch("aaa", "ab*a*c*a"));
	}

	/**
	 * Solved with dynamic programming.
	 * match[i][j] = match[i][j] || match[i - 1][j - 1] || match[i - 1][j - 2], if p[j] is not '*'.
	 * match[i][j] = match[i][j] || match[i][j - 1] || match[i][j - 2], if p[j] is '*', then extend '*' (line 73-80).
	 * Time complexity is O(mn), m is the length of s, n is the length of p.
	 * @param s
	 * @param p
	 * @return
	 */
	
	public static boolean isMatch(String s, String p) {

		if (s.equals("")) {											// if s is an empty string, check is there enough '*' to offset letters
			return checkMinimum(p);
		}
		if (p.equals("")) {											// if p is an empty string, return false
			return false;
		}

		boolean match[][] = new boolean[s.length()][p.length()];	// match[i][j] stands for matched status up to s[i] and p[j]

		int count = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) != '*') {
				count++;
				if (count > 1) {
					break;
				}
			}
			if (s.charAt(0) == p.charAt(i) || p.charAt(i) == '.') {	// some preparation, how to match the first character in s
				match[0][i] = true;
			}
			if (p.charAt(i) == '*') {
				count--;
				match[0][i] = match[0][i - 1];
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {		// if p[j] is not '*' and s[i] == p[j]
					if (i > 0 && j > 1 && p.charAt(j - 1) == '*') {			// p[j - 1] is '*' but '*' does not make any sense
						match[i][j] = match[i][j] || match[i - 1][j - 2];	// in other words, '*' is skipped
					}
					if (i > 0 && j > 0) {									// p[j - 1] is not '*'
						match[i][j] = match[i][j] || match[i - 1][j - 1];
					}
				}
				if (p.charAt(j) == '*') {									// if p[j] is '*', note that first element in p cannot be '*'
					match[i][j] = match[i][j] || match[i][j - 1];			// '*' does not make any sense
					if (j > 1) {
						match[i][j] = match[i][j] || match[i][j - 2];		// '*' offsets p[j - 1]
					}
					int k = 1;												// try to extend '*'
					while (i + k < s.length()) {							// which means '*' stands for at least 1 preceding character
						if ((s.charAt(i) == p.charAt(j - 1) && s.charAt(i) == s.charAt(i + k)) || p.charAt(j - 1) == '.') {
							match[i + k][j - 1] = match[i][j - 1] || match[i + k][j - 1];
							k++;
						} else {
							break;
						}
					}
				}
			}
		}

		return match[s.length() - 1][p.length() - 1];
	}

	public static boolean checkMinimum(String p) {

		int count = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) != '*') {
				count++;
			} else {
				count--;
			}
		}

		return (count == 0);
	}
}
