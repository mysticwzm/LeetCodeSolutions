package wzm;

public class CountAndSay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(countAndSay(10));
	}
	
	/**
	 * Follow the introduction, do it straightforward.
	 * It is better that replace String by StringBuilder.
	 * @param n
	 * @return
	 */
	
	public static String countAndSay(int n) {
		
		String s = "1";
		
		for (int i = 1; i < n; i++) {
			String temp = "";
			char prev = s.charAt(0);
			int count = 1;
			for (int j = 1; j < s.length(); j++) {
				if (s.charAt(j) == prev) {
					count++;
				} else {
					temp += say(count, prev);
					prev = s.charAt(j);
					count = 1;
				}
			}
			temp += say(count, prev);
			s = temp;
		}

		return s;
	}

	private static String say(int count, char number) {

		return "" + intToChar(count) + number;
	}

	private static char intToChar(int count) {

		return (char) (count + '0');
	}
}
