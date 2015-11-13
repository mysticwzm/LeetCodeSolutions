package wzm;

public class StringToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "   +0 123";
		System.out.println(myAtoi(str));
	}

	/**
	 * @param str
	 * @return
	 */
	
	public static int myAtoi(String str) {

		boolean positive = false;
		boolean negative = false;
		boolean anyDigit = false;
		int unit = 0;
		int convertedInteger = 0;

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '-' && !anyDigit && !negative) {		// first '-' continue, second '-' check
				negative = true;
				continue;
			}
			if (ch == '+' && !anyDigit && !positive) {		// first '+' continue, second '+' check
				positive = true;
				continue;
			}
			if (positive && negative) {						// both '-' and '+' appear, invaild input
				return 0;
			}
			if (!isDigitOrWhiteSpace(ch)) {					// invaild character, such as letter or second '-', '+'
				break;
			}
			if (ch == ' ') {								
				if (anyDigit || negative || positive) {		// the white space after any '-', '+' or digit is invaild
					break;
				} else {
					continue;
				}
			}
			anyDigit = true;
			unit = positive || !negative ? ch - '0' : -(ch - '0');	// ensure the integer is positve or negative
			if (!isStackOverflow(convertedInteger, unit)) {			// ensure the integer is not stackoverflowed
				convertedInteger = convertedInteger * 10 + unit;
			} else {
				if (positive || !negative) {						// if stack overflow
					return Integer.MAX_VALUE;						// positive integer, return maximum integer
				} else {
					return Integer.MIN_VALUE;						// negative integer, return minimum integer
				}
			}
		}

		return convertedInteger;
	}

	public static boolean isStackOverflow(int convertedInteger, int unit) {

		if (convertedInteger > Integer.MAX_VALUE / 10) {
			return true;
		}
		if (convertedInteger == Integer.MAX_VALUE / 10 && unit > Integer.MAX_VALUE % 10) {
			return true;
		}
		if (convertedInteger < Integer.MIN_VALUE / 10) {
			return true;
		}
		if (convertedInteger == Integer.MIN_VALUE / 10 && unit < Integer.MIN_VALUE % 10) {
			return true;
		}
		return false;
	}

	public static boolean isDigitOrWhiteSpace(char ch) {

		if (ch >= '0' && ch <= '9' || ch == ' ') {
			return true;
		}
		return false;
	}
}
