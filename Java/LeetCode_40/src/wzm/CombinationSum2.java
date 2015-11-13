package wzm;

import java.util.List;
import java.util.ArrayList;

public class CombinationSum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;

		List<List<Integer>> resultList = combinationSum2(candidates, target);
	}

	/**
	 * Solved by recurse and backtracking.
	 * In order to make all combinations in ascending order, the candidates array need to be sorted first.
	 * Moreover, the index of the maximum value so far should be recorded to keep ascending order.
	 * In respect of removing duplicates, we always starts from the first of each number.
	 * For example, 111 could form 111, 11 and 1, but 11 is only able to form 11 and 1.
	 * Therefore, 111 will cover all situations of 1.
	 * @param candidates
	 * @param target
	 * @return
	 */

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> tempList = new ArrayList<Integer>();
		quickSort(candidates, 0, candidates.length - 1);

		constructSum(resultList, tempList, 0, 0, candidates, target);

		return resultList;
	}

	private static void constructSum(List<List<Integer>> resultList, List<Integer> tempList, int tempSum, int start,
			int[] candidates, int target) {

		if (tempSum == target) {
			resultList.add(new ArrayList<Integer>(tempList));
			return;
		}

		for (int i = start; i < candidates.length; i++) {
			if (i > start && candidates[i] == candidates[i - 1]) {			// remove all duplicates
				continue;
			}
			if (tempSum + candidates[i] <= target) {
				tempList.add(candidates[i]);
				constructSum(resultList, tempList, tempSum + candidates[i], i + 1, candidates, target);
				tempList.remove(tempList.size() - 1);
			} else {
				break;
			}
		}
	}

	private static void quickSort(int[] candidates, int left, int right) {

		int l = left;
		int r = right;
		int pivot = candidates[left + (right - left >> 1)];

		while (l <= r) {
			while (candidates[l] < pivot) {
				l++;
			}
			while (pivot < candidates[r]) {
				r--;
			}
			if (l <= r) {
				swap(candidates, l, r);
				l++;
				r--;
			}
		}
		if (l < right) {
			quickSort(candidates, l, right);
		}
		if (left < r) {
			quickSort(candidates, left, r);
		}
	}

	private static void swap(int[] candidates, int a, int b) {

		int temp = candidates[a];
		candidates[a] = candidates[b];
		candidates[b] = temp;
	}
}
