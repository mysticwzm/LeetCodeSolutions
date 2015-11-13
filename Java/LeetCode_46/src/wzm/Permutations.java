package wzm;

import java.util.List;
import java.util.ArrayList;

public class Permutations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 1, 2, 3 };

		System.out.println(permute(nums));
		// System.out.println(permuteIterativeEdition(nums));
	}

	public static List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		boolean[] used = new boolean[nums.length];

		// permuteRecursiveEdition(resultList, result, nums, used, 0);
		permuteSwapEdition(resultList, nums, 0);

		return resultList;
	}

	/**
	 * Solved by depth first search.
	 * Time complexity is O(n!).
	 * @param resultList
	 * @param result
	 * @param nums
	 * @param used
	 * @param depth
	 */
	
	private static void permuteRecursiveEdition(List<List<Integer>> resultList, List<Integer> result, int[] nums,
			boolean[] used, int depth) {

		if (depth == nums.length) {
			resultList.add(new ArrayList<Integer>(result));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				result.add(nums[i]);
				used[i] = true;
				permuteRecursiveEdition(resultList, result, nums, used, depth + 1);
				result.remove(depth);
				used[i] = false;
			}
		}
	}
	
	/**
	 * Solved by iterative construction.
	 * Insert number from the beginning of sequence to the end of sequence, store all (sub)results.
	 * Time complexity is O(n!).
	 * @param nums
	 * @return
	 */
	
	public static List<List<Integer>> permuteIterativeEdition(int[] nums) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		resultList.add(new ArrayList<Integer>(result));

		for (int i = 0; i < nums.length; i++) {
			while (resultList.get(0).size() == i) {
				result = resultList.get(0);									// pick a sub-result out
				for (int j = i; j >= 0; j--) {								// insert from end to beginning in order to
					result.add(j, nums[i]);									// make result in ascending order
					resultList.add(new ArrayList<Integer>(result));			// store all (sub)results
					result.remove(j);										// remove the inserted number and insert in next position again
				}
				resultList.remove(0);										// remove the previous sub-result
			}
		}

		return resultList;
	}

	/**
	 * Solved by swap and backtracking.
	 * Time complexity is O(n!).
	 * @param resultList
	 * @param nums
	 * @param depth
	 */
	
	private static void permuteSwapEdition(List<List<Integer>> resultList, int[] nums, int depth) {

		if (depth == nums.length) {
			List<Integer> result = new ArrayList<Integer>(nums.length);
			for (int i = 0; i < nums.length; i++) {
				result.add(nums[i]);
			}
			resultList.add(result);
			return;
		}
		for (int i = depth; i < nums.length; i++) {				// the numbers before depth are looked as used and numbers from depth are unused
			swap(nums, depth, i);								// instead of probing which number is unused, we swap the number at depth with
			permuteSwapEdition(resultList, nums, depth + 1);	// itself(fill itself in) or numbers after it(fill in other unused numbers)
			swap(nums, depth, i);								// swap again as backtracking
		}
	}

	private static void swap(int[] nums, int a, int b) {

		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
