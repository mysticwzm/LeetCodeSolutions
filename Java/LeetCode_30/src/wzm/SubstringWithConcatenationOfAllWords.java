package wzm;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class SubstringWithConcatenationOfAllWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "barfoofoobarthefoobarman";
		String[] words = { "foo", "bar", "the" };

		System.out.println(findSubstring(s, words));
	}

	/**
	 * Solved with hash map. 
	 * Store the occurrence frequency of each word in hash map.
	 * Enumerate an index, cut out k substrings with length of m.
	 * If all substrings match the occurrence frequency of each word in the hash map,
	 * Store this index in result list.
	 * Time complexity is O(kn), where k is the number of words and n is the length of s.
	 * @param s
	 * @param words
	 * @return
	 */
	
	public static List<Integer> findSubstring(String s, String[] words) {

		List<Integer> indices = new ArrayList<Integer>();
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();

		if (s.length() == 0 || words.length == 0) {
			return indices;
		}

		int numOfWords = words.length;
		int wordLength = words[0].length();
		int stringLength = numOfWords * wordLength;
		for (int i = 0; i < numOfWords; i++) {
			frequency.put(words[i], frequency.getOrDefault(words[i], 0) + 1);
		}

		for (int i = 0; i < s.length() - stringLength + 1; i++) {
			HashMap<String, Integer> tempFrequency = new HashMap<String, Integer>(frequency);
			for (int j = 0; j < numOfWords; j++) {
				String tempString = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
				if (!tempFrequency.containsKey(tempString)) {
					break;
				} else {
					int wordFrequency = tempFrequency.get(tempString);
					if (wordFrequency > 1) {
						tempFrequency.put(tempString, wordFrequency - 1);
					} else {
						tempFrequency.remove(tempString);
					}
				}
			}
			if (tempFrequency.size() == 0) {
				indices.add(i);
			}
		}

		return indices;
	}
}
