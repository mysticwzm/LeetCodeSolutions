package wzm;

public class MultiplyStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String num1 = "99999999";
		String num2 = "999999999";

		System.out.println(multiply(num1, num2));
	}

	/**
	 * Solved without reversal. Every int bit in array will contain 4 digits instead of 1 digit.
	 * Time complexity is O(n^2).
	 * @param num1
	 * @param num2
	 * @return
	 */

	public static String multiply(String num1, String num2) {

		if (num1.length() < num2.length()) {
			return multiply(num2, num1);
		}

		final int THRESHOLD = 10000;
		int[] product = new int[(num1.length() + 3 >> 2) + (num2.length() + 3 >> 2)];		// determine the length of product

		for (int i = num2.length() - 1; i >= 0; i = i - 4) {
			for (int j = num1.length() - 1; j >= 0; j = j - 4) {
				int subNum2 = Integer.valueOf(num2.substring(Math.max(i - 3, 0), i + 1));	
				int subNum1 = Integer.valueOf(num1.substring(Math.max(j - 3, 0), j + 1));
				product[(i >> 2) + (j >> 2) + 1] += subNum1 * subNum2;							// raw product
				product[(i >> 2) + (j >> 2)] += product[(i >> 2) + (j >> 2) + 1] / THRESHOLD;	// add carry previous bit
				product[(i >> 2) + (j >> 2) + 1] %= THRESHOLD;									// remainder after carry
			}
		}

		int valid = 0;																		// determine the first valid number
		for (valid = 0; valid < product.length; valid++) {
			if (product[valid] > 0) {
				break;
			}
		}
		if (valid == product.length) {														// in case of any number * 0
			return "0";																		// return 0
		}

		StringBuilder finalProduct = new StringBuilder(String.valueOf(product[valid]));		// first number, no need to add '0'
		for (int i = valid + 1; i < product.length; i++) {
			StringBuilder subProduct = new StringBuilder(String.valueOf(product[i]));		
			while (subProduct.length() < 4) {												// add '0' if this number consists
				subProduct.insert(0, '0');													// of 1/2/3 digits, e.g. 123 -> 0123
			}
			finalProduct.append(subProduct);												// append to final product
		}

		return finalProduct.toString();
	}
}
