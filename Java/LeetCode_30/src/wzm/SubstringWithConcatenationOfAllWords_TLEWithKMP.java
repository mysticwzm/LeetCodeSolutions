package wzm;

import java.util.List;
import java.util.ArrayList;

public class SubstringWithConcatenationOfAllWords_TLEWithKMP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "barfoofoobarthefoobarman";
		String[] words = { "foo", "bar", "the" };

		System.out.println(findSubstring(s, words));
	}

	/**
	 * Unsolved by this method. Time limit exceeded with enormous problem size.
	 * The idea of this algorithm is:
	 * 1. find the occurrence indices of each word with KMP algorithm. k * O(m + n)
	 * 2. for every index of s which is the start index of a word, enumerate a couple of strings after that index.
	 *    if those strings match all words, then add this index to the result.
	 * There is a better solution, by using hash map.
	 * @param s
	 * @param words
	 * @return
	 */

	public static List<Integer> findSubstring(String s, String[] words) {

		List<Integer> indices = new ArrayList<Integer>();

		if (s.length() == 0 || words.length == 0) {
			return indices;
		}

		int wordLength = words[0].length();
		int numString = words.length;
		int stringLength = wordLength * numString;
		int[][] prefix = new int[numString][wordLength];

		for (int i = 0; i < numString; i++) {
			prefix[i] = computePrefix(words[i]);
		}

		List<List<Integer>> occurrence = new ArrayList<List<Integer>>();
		for (int i = 0; i < s.length(); i++) {
			List<Integer> temp = new ArrayList<Integer>();
			occurrence.add(temp);
		}

		for (int j = 0; j < numString; j++) {
			int prefixLength = 0;
			for (int i = 0; i < s.length(); i++) {
				while (prefixLength > 0 && s.charAt(i) != words[j].charAt(prefixLength)) {
					prefixLength = prefix[j][prefixLength - 1];
				}
				if (s.charAt(i) == words[j].charAt(prefixLength)) {
					prefixLength++;
				}
				if (prefixLength == wordLength) {
					int thisoccurrence = i - wordLength + 1;
					occurrence.get(thisoccurrence).add(j);
					prefixLength = prefix[j][prefixLength - 1];
				}
			}
		}

		for (int i = 0; i < s.length() - stringLength + 1; i++) {
			int size = occurrence.get(i).size();
			if (size > 0) {
				boolean[] appeared = new boolean[numString];
				for (int j = 0; j < numString; j++) {
					int start = i + j * wordLength;
					for (int k = 0; k < occurrence.get(start).size(); k++) {
						if (!appeared[occurrence.get(start).get(k)]) {
							appeared[occurrence.get(start).get(k)] = true;
							break;
						}
					}
				}
				boolean covered = true;
				for (int j = 0; j < numString; j++) {
					if (!appeared[j]) {
						covered = false;
						break;
					}
				}
				if (covered) {
					indices.add(i);
				}
			}
		}

		return indices;
	}

	private static int[] computePrefix(String word) {

		int[] prefix = new int[word.length()];
		prefix[0] = 0;
		int prefixLength = 0;

		for (int i = 1; i < word.length(); i++) {
			while (prefixLength > 0 && word.charAt(i) != word.charAt(prefixLength)) {
				prefixLength = prefix[prefixLength - 1];
			}
			if (word.charAt(i) == word.charAt(prefixLength)) {
				prefixLength++;
			}
			prefix[i] = prefixLength;
		}

		return prefix;
	}
}
