package wzm;

public class Implement_strStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String haystack = "abcdeeefg";
		String needle = "eef";

		System.out.println(strStr(haystack, needle));
	}

	/**
	 * Solved by KMP algorithm.
	 * prefix[i] means if the needle[i] does not match, the length of prefix can be re-use.
	 * Example
	 * Index  : 0 1 2 3 4 5 6 
	 * String : a b a b a c a
	 * prefix : 0 0 1 2 3 0 1
	 * Time complexity is O(m + n).
	 * @param haystack
	 * @param needle
	 * @return
	 */

	public static int strStr(String haystack, String needle) {

		if (needle.length() == 0) {
			return 0;
		}

		int firstOccurence = -1;
		int[] prefix = computePrefix(needle);
		int prefixLength = prefix[0];
		int expectedLength = needle.length();

		for (int i = 0; i < haystack.length(); i++) {
			while (prefixLength > 0 && haystack.charAt(i) != needle.charAt(prefixLength)) {
				prefixLength = prefix[prefixLength - 1];
			}
			if (haystack.charAt(i) == needle.charAt(prefixLength)) {
				prefixLength++;
			}
			if (prefixLength == expectedLength) {
				firstOccurence = i - expectedLength + 1;
				break;
			}
		}

		return firstOccurence;
	}

	private static int[] computePrefix(String needle) {

		int[] prefix = new int[needle.length()];
		prefix[0] = 0;
		int prefixLength = prefix[0];

		for (int i = 1; i < needle.length(); i++) {
			while (prefixLength > 0 && needle.charAt(i) != needle.charAt(prefixLength)) {
				prefixLength = prefix[prefixLength - 1];
			}
			if (needle.charAt(i) == needle.charAt(prefixLength)) {
				prefixLength++;
			}
			prefix[i] = prefixLength;
		}

		return prefix;
	}
}
