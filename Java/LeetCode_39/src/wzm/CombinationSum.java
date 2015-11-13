package wzm;

import java.util.List;
import java.util.ArrayList;

public class CombinationSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] candidates = { 8, 7, 4, 3 };
		int target = 11;

		List<List<Integer>> resultList = combinationSum(candidates, target);
	}

	/**
	 * Solved by recurse and backtracking.
	 * In order to make all combinations in ascending order, the candidates array need to be sorted first.
	 * Moreover, the index of the maximum value so far should be recorded to keep ascending order.
	 * @param candidates
	 * @param target
	 * @return
	 */

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> tempList = new ArrayList<Integer>();
		quickSort(candidates, 0, candidates.length - 1);

		constructSum(resultList, tempList, 0, 0, candidates, target);

		return resultList;
	}

	private static void constructSum(List<List<Integer>> resultList, List<Integer> tempList, int tempSum, int start,
			int[] candidates, int target) {

		if (tempSum == target) {
			resultList.add(new ArrayList<Integer>(tempList));	// we need to add a copy of tempList rather than the tempList itself
			return;												// because "add" method actually add the reference of object to 
		}														// ArrayList, it is a shallow clone.

		for (int i = start; i < candidates.length; i++) {
			if (tempSum + candidates[i] <= target) {
				tempList.add(candidates[i]);					// recurse to next depth
				constructSum(resultList, tempList, tempSum + candidates[i], i, candidates, target);
				tempList.remove(tempList.size() - 1);			// backtrack to last status
			} else {
				break;											// because candidates is in ascending order, if the current value
			}													// is too big, no need to verify the following values, as they are
		}														// bigger than current value. This makes program run much faster.
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
