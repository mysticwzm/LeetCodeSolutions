package wzm;

public class RomanToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(romanToInteger("CMXX"));
	}
	
	/**
	 * This is a brute force solution, but there is a better solution.
	 * If the current character follows a bigger or same character (e.g. LX, XX, VI),
	 * add value of this current character to result.
	 * Else subtract 2 * value of the previous character (e.g. IX = 1 + 5 - 2).
	 * Beautiful O(n) solution.
	 * @param s
	 * @return
	 */
	
	public static int romanToInteger(String s) {

		int unitStarts = s.length();										// find the start position of unit bit
		if (s.indexOf('I') > -1 && s.indexOf('I') < unitStarts) {
			unitStarts = s.indexOf('I');
		}
		if (s.indexOf('V') > -1 && s.indexOf('V') < unitStarts) {
			unitStarts = s.indexOf('V');
		}
		int tenStarts = s.length();											// find the start position of ten bit
		if (s.indexOf('X') > -1 && s.indexOf('X') < tenStarts) {
			tenStarts = s.indexOf('X');
		}
		if (s.indexOf('L') > -1 && s.indexOf('L') < tenStarts) {
			tenStarts = s.indexOf('L');
		}
		int hundredStarts = s.length();										// find the start position of hundred bit
		if (s.indexOf('C') > -1 && s.indexOf('C') < hundredStarts) {
			hundredStarts = s.indexOf('C');
		}
		if (s.indexOf('D') > -1 && s.indexOf('D') < hundredStarts) {
			hundredStarts = s.indexOf('D');
		}
		int thousandStarts = s.length();									// find the start position of thousand bit
		if (s.indexOf('M') > -1 && s.indexOf('M') < thousandStarts) {
			thousandStarts = s.indexOf('M');
		}
		tenStarts = isExisted(tenStarts, unitStarts);						// handle the special conditions, like 9, 90, 900
		hundredStarts = isExisted(hundredStarts, tenStarts);				
		thousandStarts = isExisted(thousandStarts, hundredStarts);

		String thousandBit = s.substring(thousandStarts, hundredStarts);	// according to the positions above, cut out each
		String hundredBit = s.substring(hundredStarts, tenStarts);			// bit in form of roman number
		String tenBit = s.substring(tenStarts, unitStarts);
		String unitBit = s.substring(unitStarts, s.length());

		String[][] romanNumbers = {{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
								   { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },	// enumeration of roman numbers 
								   { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
								   { "", "M", "MM", "MMM" }};
		int result = 0;
		for (int i = 0; i < 4; i++) {										// according to the enumeration above, calculate the result
			if (thousandBit.equals(romanNumbers[3][i])) {
				result = result + i * 1000;
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (hundredBit.equals(romanNumbers[2][i])) {
				result = result + i *100;
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (tenBit.equals(romanNumbers[1][i])) {
				result = result + i * 10;
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			if (unitBit.equals(romanNumbers[0][i])) {
				result = result + i;
				break;
			}
		}

		return result;
	}

	public static int isExisted(int highBit, int lowBit) {

		return highBit < lowBit ? highBit : lowBit;
	}
}
