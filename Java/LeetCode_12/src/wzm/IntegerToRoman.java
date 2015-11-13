package wzm;

public class IntegerToRoman {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(intToRoman(3999));
	}

	/**
	 * Solved with enumeration of roman numbers.
	 * Time complexity is O(lgn);
	 * @param num
	 * @return
	 */
	
	public static String intToRoman(int num) {

		String[][] romanNumbers = {{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
								   { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },	// enumeration of roman numbers 
								   { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
								   { "", "M", "MM", "MMM" }};
		String romanNumber = "";
		int bits = 0;

		while (num != 0) {
			romanNumber = romanNumbers[bits][num % 10] + romanNumber;		// combine from right to left, units to thousands
			bits++;
			num = num / 10;
		}

		return romanNumber;
	}

}
