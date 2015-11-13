package wzm;

public class PowXN {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double x = 3;
		int n = -1;

		System.out.println(myPow(x, n));
	}
	
	/**
	 * Solved by math...
	 * In order to avoid stack overflow, replace int n by its long type.
	 * Time complexity is O(logn).
	 * @param p base
	 * @param n exponential
	 * @return pow(x, n)
	 */
	
	public static double myPow(double x, int n) {
		
		long longN = n > 0 ? n : -n;
		x = n > 0 ? x : 1 / x;
		double pow = 1;
		
		while (longN > 0) {
			if ((longN & 1) == 1) {
				pow *= x;
			}
			x *= x;
			longN = longN >> 1;
		}

		return pow;
	}
}
