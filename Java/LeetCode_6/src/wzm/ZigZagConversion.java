package wzm;

public class ZigZagConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(convert("AB", 1));
	}

	/**
	 * @param s
	 * @param numRows
	 * @return
	 */
	
	public static String convert(String s, int numRows) {

		String stringArray[] = new String[numRows];
		int i = 0;
		for (i = 0; i < numRows; i++) {										// 1       9
			stringArray[i] = "";											// 2     8 10  
		}																	// 3   7   11
		int rowNumber = 0;													// 4 6     12 ...
		boolean downward = true;											// 5       13
		String finalString = "";

		if (numRows == 1) {													// exceptional case, only one row
			return s;
		}

		for (i = 0; i < s.length(); i++) {
			stringArray[rowNumber] = stringArray[rowNumber] + s.charAt(i);
			if (downward && rowNumber != numRows - 1) {
				rowNumber++;
			} else if (downward && rowNumber == numRows - 1) {
				rowNumber--;
				downward = !downward;
			} else if (!downward && rowNumber != 0) {
				rowNumber--;
			} else if (!downward && rowNumber == 0) {
				rowNumber++;
				downward = !downward;
			}
		}
		for (i = 0; i < numRows; i++) {
			finalString = finalString + stringArray[i];
		}
		
		return finalString;
	}
}
