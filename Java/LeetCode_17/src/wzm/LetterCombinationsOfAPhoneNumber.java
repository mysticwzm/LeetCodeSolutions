package wzm;

import java.util.List;
import java.util.ArrayList;

public class LetterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String digits = "";
		System.out.println(letterCombinations(digits).toString());
	}

	/**
	 * Solved by depth first search.
	 * Time complexity is approximately O(3^n), where n is the length of digits string.
	 * @param digits
	 * @return
	 */
	
	public static List<String> letterCombinations(String digits) {

		List<String> stringList = new ArrayList<String>();
		List<String> keyboard = new ArrayList<String>();
		keyboard.add(""); 									// keyboard 0
		keyboard.add(""); 									// keyboard 1
		keyboard.add("abc"); 								// keyboard 2
		keyboard.add("def"); 								// keyboard 3
		keyboard.add("ghi"); 								// keyboard 4
		keyboard.add("jkl"); 								// keyboard 5
		keyboard.add("mno"); 								// keyboard 6
		keyboard.add("pqrs"); 								// keyboard 7
		keyboard.add("tuv"); 								// keyboard 8
		keyboard.add("wxyz"); 								// keyboard 9

		if (digits.length() > 0) {
			combineOneMore(digits, 0, "", keyboard, stringList);
		}

		return stringList;
	}

	private static void combineOneMore(String digits, int depth, String currentString, List<String> keyboard,
			List<String> stringList) {

		if (depth == digits.length()) {
			stringList.add(currentString);
			return;
		}

		if ('0' <= digits.charAt(depth) && digits.charAt(depth) <= '9') {
			int digit = digits.charAt(depth) - '0';
			String button = keyboard.get(digit);
			for (int i = 0; i < button.length(); i++) {
				combineOneMore(digits, depth + 1, currentString + button.charAt(i), keyboard, stringList);
			}
		} else {
			combineOneMore(digits, depth + 1, currentString, keyboard, stringList);
		}
	}
}
