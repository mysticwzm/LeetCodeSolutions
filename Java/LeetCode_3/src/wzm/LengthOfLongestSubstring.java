package wzm;

import java.util.HashMap;

public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(lengthOfLongestSubstring("abcabcbb"));
	}

	/**
	 * Solved with hash map which is used to record the last occurrence of every letter.
	 * Time complexity is O(n)(n is length of s).
	 * @param s
	 * @return
	 */
	
	public static int lengthOfLongestSubstring(String s) {
		
		HashMap<Character, Integer> dictionary = new HashMap<Character, Integer>();
		int head = 0;
		int currentLength = 0;
		int maxLength = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (dictionary.get(s.charAt(i)) == null || dictionary.get(s.charAt(i)) < head ) {	// if the current character never appears before
				dictionary.put(s.charAt(i), i);													// or it is not inclueded in the current substring
				currentLength++;																// record its position 
			}																					// and append it to the current substring
			else {
				currentLength = i - dictionary.get(s.charAt(i));	// if the current character is included in the current substring
				head = dictionary.get(s.charAt(i)) + 1;				// update the head of substring and position of this character
				dictionary.put(s.charAt(i), i);						// as well as the length of current substring
			}
			if (currentLength > maxLength) {
				maxLength = currentLength;							// update the maximum length
			}
		}
		
		return maxLength;
	}
	
}
