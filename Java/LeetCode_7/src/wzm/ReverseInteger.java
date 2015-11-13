package wzm;

public class ReverseInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int x = 123456789;
		
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(reverse(x));
	}

	/**
	 * @param x
	 * @return
	 */
	
	public static int reverse(int x) {

		int reversedNumber = 0;

		if (x > -10 && x < 10) {
			return x;
		}
		while (x != 0) {
			reversedNumber = reversedNumber * 10 + x % 10;
			x = x / 10;
			if (x != 0 && isStackOverflow(reversedNumber, x)) {
				return 0;
			}
		}
		return reversedNumber;
	}

	public static boolean isStackOverflow(int reversedNumber, int x) {

		if (reversedNumber > Integer.MAX_VALUE / 10) {
			return true;
		}
		if (reversedNumber == Integer.MAX_VALUE / 10 && x > Integer.MAX_VALUE % 10) {
			return true;
		}
		if (reversedNumber < Integer.MIN_VALUE / 10) {
			return true;
		}
		if (reversedNumber == Integer.MIN_VALUE / 10 && x < Integer.MIN_VALUE % 10) {
			return true;
		}
		return false;
	}
}
