package wzm;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class GroupAnagrams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };

		System.out.println(groupAnagrams(strs));
	}

	/**
	 * Solved with hash map. 
	 * Aimming to keep each inner list's elements follow the lexicographic order, sorted the strs at first.
	 * Then sort every string internally, which will make all strings as its "first permutation".
	 * Use hash map to judge whether this anagrams is included or not.
	 * Time complexity is O(n^2).
	 * @param strs String array
	 * @return Anagrams group
	 */

	public static List<List<String>> groupAnagrams(String[] strs) {

		List<List<String>> stringList = new ArrayList<List<String>>();
		Arrays.sort(strs);

		Map<String, ArrayList<String>> anagramsMap = new HashMap<String, ArrayList<String>>();
		for (int i = 0; i < strs.length; i++) {
			anagramsGrouping(anagramsMap, strs[i]);
		}

		for (Map.Entry<String, ArrayList<String>> entry : anagramsMap.entrySet()) {
			stringList.add(entry.getValue());
		}

		return stringList;
	}

	private static void anagramsGrouping(Map<String, ArrayList<String>> anagramsMap, String s) {
		
		char[] sortedCharArray = s.toCharArray();
		Arrays.sort(sortedCharArray);
		String sortedS = String.valueOf(sortedCharArray);

		if (!anagramsMap.containsKey(sortedS)) {
			anagramsMap.put(sortedS, new ArrayList<String>());
		}
		anagramsMap.get(sortedS).add(s);
	}
}
