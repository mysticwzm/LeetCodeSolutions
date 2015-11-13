package wzm;

public class PalindromeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int x = Integer.MIN_VALUE;
		System.out.println(isPalindrome(x));

	}

	/**
	 * Solved by converting integer to string.
	 * Time complexity is O(n).
	 * @param x
	 * @return
	 */
	
	public static boolean isPalindrome(int x) {

		String originalInteger = String.valueOf(x);
		String reversedInteger = "";								// convert the integer io string and reverse this string
		for (int i = originalInteger.length() - 1; i >= 0; i--) {	// if two strings are the small, then it is palindromic
			reversedInteger += originalInteger.charAt(i);
		}
		for (int i = 0; i < originalInteger.length(); i++) {
			if (originalInteger.charAt(i) != reversedInteger.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}
}
