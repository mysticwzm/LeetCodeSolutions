package wzm;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Permutation2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 3, 2, 2, 1, 1 };

		System.out.println(permuteUnique(nums));
		System.out.println(permuteUniqueRecursiveEdition(nums));
	}

	public static List<List<Integer>> permuteUnique(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		// Arrays.sort(nums);
		quickSort(nums, 0, nums.length - 1);
		permuteUniqueSwapEdition(resultList, nums, 0);

		return resultList;
	}

	/**
	 * Solved by swap and backtracking.
	 * In this problem, we need to sort the array at first for skipping duplicates.
	 * After sorting, if the number going to be swap is equal to current number, skip this swap.
	 * To avoid the number which has been swap to be swap back again, we make a copy of current array and pass to the next step.
	 * In other word, the swap in future will only happen in the new copied array, not the original array.
	 * These swap on new copied array does not have any effect on the original array, which prevents numbers being swap back.
	 * Moreover, now, there is no need to swap back after recursion as backtracking because all following swap happen in new copied array.
	 * Time complexity is O(n!).
	 * @param resultList
	 * @param nums
	 * @param depth
	 */

	private static void permuteUniqueSwapEdition(List<List<Integer>> resultList, int[] nums, int depth) {

		if (depth == nums.length - 1) {
			List<Integer> result = new ArrayList<Integer>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				result.add(nums[i]);
			}
			resultList.add(result);
			return;
		}
		for (int i = depth; i < nums.length; i++) {
			if (i == depth || nums[depth] != nums[i]) {
				swap(nums, depth, i);
				int[] numsNextStep = new int[nums.length];
				System.arraycopy(nums, 0, numsNextStep, 0, nums.length);		// as Java passes array/object by reference, we need to 
				permuteUniqueSwapEdition(resultList, numsNextStep, depth + 1);	// make a copy of current array and pass it to next step
			}
		}
	}

	private static void swap(int[] nums, int a, int b) {

		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	private static void quickSort(int[] nums, int left, int right) {

		int l = left;
		int r = right;
		int pivot = nums[left + (right - left >> 1)];

		while (l <= r) {
			while (nums[l] < pivot) {
				l++;
			}
			while (pivot < nums[r]) {
				r--;
			}
			if (l <= r) {
				swap(nums, l, r);
				l++;
				r--;
			}
		}
		if (l < right) {
			quickSort(nums, l, right);
		}
		if (left < r) {
			quickSort(nums, left, r);
		}
	}
	
	/**
	 * Solved by hash map and recursion.
	 * Hash map is used to skip duplicates.
	 * Time complexity is O(n!).
	 * @param nums
	 * @return
	 */
	
	public static List<List<Integer>> permuteUniqueRecursiveEdition(int[] nums) {
		
		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}
		permuteUniqueRecursiveEdition(resultList, result, nums, map);

		return resultList;
	}

	private static void permuteUniqueRecursiveEdition(List<List<Integer>> resultList, List<Integer> result, int[] nums, 
			Map<Integer, Integer> map) {
			
		if (result.size() == nums.length) {
			resultList.add(new ArrayList<Integer>(result));
			return;
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();
			if (value > 0) {
				result.add(key);
				map.put(key, value - 1);
				permuteUniqueRecursiveEdition(resultList, result, nums, map);
				map.put(key, value);
				result.remove(result.size() - 1);
			}
		}
	}
}
