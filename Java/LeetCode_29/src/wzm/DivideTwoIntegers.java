package wzm;

public class DivideTwoIntegers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int dividend = 2147483647;
		int divisor = -2147483648;
		
		System.out.println(divide(dividend, divisor));
	}

	/**
	 * Solved by enumeration. dividend == 2^k * divisor + ... + 2^0 * divisor + remainder.
	 * Be careful of Integer.MIN_VALUE, -2147483648, which cannot be converted to a positive integer(32bits).
	 * Time complexity is O(log(dividend / divisor)).
	 * @param dividend
	 * @param divisor
	 * @return
	 */

	public static int divide(int dividend, int divisor) {

		if (dividend == Integer.MIN_VALUE && divisor == -1) {		// special case 1: quotient is 2147483648, stack overflow
			return Integer.MAX_VALUE;
		}
		if (dividend == divisor) {									// special case 2: mainly for dividend == divisor == -2147483648 
			return 1;
		}
		if (divisor == 1) {											// special case 3: mainly for dividend == -2147483648 and
			return dividend;										// divisor == 1, but shift cannot be 2147483648
		}
		if (divisor == Integer.MIN_VALUE) {							// special case 4: divisor == -2147483648, dividend != -2147483648
			return 0;
		}

		boolean positive = (dividend > 0) ^ (divisor < 0);			// determine the sign of quotient, xor
		int quotient = 0;
		divisor = divisor > 0 ? divisor : -divisor;					
		if (dividend == Integer.MIN_VALUE) {						// because the case divisor == 1 has been filtered in special case
			dividend = dividend + divisor;							// 3, shift and quotient cannot be 2147483648 now. add 1 * divisor
			quotient++;												// to dividend when it is -2147483648 and the add 1 to quotient
		}
		dividend = dividend > 0 ? dividend : -dividend;				// dividend must be bigger than -2147483648, convert it to positive
		int shift = 1;												// if necessary

		while (divisor <= (dividend >> 1)) {						// find the maximum new divisor and shift
			divisor = divisor << 1;									// new divisor <= dividend < 2 * new divisor
			shift = shift << 1;
		}
		while (shift > 0) {											// dividend == 2^k * divisor + ... + 2^0 * divisor + remainder
			if (dividend >= divisor) {								// quotient == k + ... + 1 + 0
				dividend = dividend - divisor;
				quotient = quotient + shift;
			}
			shift = shift >> 1;
			divisor = divisor >> 1;
		}
		quotient = positive ? quotient : -quotient;					// convert quotient to negative, if sign is '-'

		return quotient;
	}
}
