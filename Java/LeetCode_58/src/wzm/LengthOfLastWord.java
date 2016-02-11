package wzm;

public class LengthOfLastWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "hello world";

		System.out.println(lengthOfLastWord(s));
	}
	
	/**
	 * Super easy question. Traverse from end to begin, skip the tailing whitespace.
	 * Then record the length of last word until the next whitespace occurs, break the loop and return the length.
	 * Time complexity is O(n).
	 * @param s original string which may contain many words
	 * @return the length of last word
	 */
	
	public static int lengthOfLastWord(String s) {
		
		int length = 0;

		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				if (length == 0) {
					continue;
				} else {
					break;
				}
			}
			length++;
		}

		return length;
	}
}
