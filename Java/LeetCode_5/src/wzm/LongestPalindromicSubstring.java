package wzm;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(longestPalindrome("bb"));
	}

	/**
	 * Solved by palindrome extension.
	 * Two possible cases: palindrome with odd or ever length.
	 * Time complexity is O(n^2).
	 * @param s
	 * @return
	 */
	
	public static String longestPalindrome(String s) {
		
		int beginIndex = 0;
		int maxSubstringLength = 1;
		
		for (int i = 0; i < s.length(); i++) {
		
			//String with odd length
			for (int extension = 1; extension <= Math.min(i, s.length() - 1 - i); extension++) {
				if (s.charAt(i - extension) == s.charAt(i + extension)) {
					if (2 * extension + 1 > maxSubstringLength) {
						maxSubstringLength = 2 * extension + 1;
						beginIndex = i - extension;
					}
				} else {
					break;
				}
			}
			//String with even length
			if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
				if (maxSubstringLength < 2) {
					beginIndex = i;
					maxSubstringLength = 2;
				}
				for (int extension = 1; extension <= Math.min(i, s.length() - 1 - (i + 1)); extension++) {
					if (s.charAt(i - extension) == s.charAt(i + 1 + extension)) {
						if (2 * extension + 2 > maxSubstringLength) {
							maxSubstringLength = 2 * extension + 2;
							beginIndex = i - extension;
						}
					} else {
						break;
					}
				}
			}
		}
		
		return s.substring(beginIndex, beginIndex + maxSubstringLength);
	}
}
