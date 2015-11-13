package wzm;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] strs = { "aa", "abb" };
		System.out.println(longestCommonPrefix(strs));
	}

	/**
	 * Solved by constructing common prefix.
	 * Firstly, determine the minimum length of String in strs.
	 * Then, every time pick a character out to construct the common prefix,
	 * if every string contains this character, append this character as updating common prefix
	 * Time complexity is O(n) + O(minLength * strs.length).
	 * @param strs
	 * @return
	 */
	
	public static String longestCommonPrefix(String[] strs) {

		if (strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}

		String commonPrefix = "";
		int shortestLength = strs[0].length(); 
		for (int i = 1; i < strs.length; i++) {
			shortestLength = Math.min(shortestLength, strs[i].length());	// determine the minimum length of string in strs
		}
		for (int i = 0; i < shortestLength; i++) {
			char commonCharacter = strs[0].charAt(i);
			boolean unmatched = false;										// every time, append one character to commonPrefix 
			for (int j = 1; j < strs.length; j++) {
				if (commonCharacter != strs[j].charAt(i)) {
					unmatched = true;
					break;
				}
			}
			if (unmatched) {
				break;
			}
			commonPrefix = commonPrefix + commonCharacter;
		}

		return commonPrefix;
	}
}
