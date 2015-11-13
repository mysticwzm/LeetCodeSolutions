package wzm;

import java.util.List;
import java.util.ArrayList;

public class FourSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int target = 0;
		int[] nums = { 0, 0, 0, 0, };

		System.out.println(fourSum(nums, target).toString());
	}

	/**
	 * Solved as twoSum/threeSum, but recursive order reduction is used in this solution.
	 * Time complexity is O(nlogn) (heap sort) + O(n ^ (k - 1)) (enumeration).
	 * @param nums
	 * @param target
	 * @return
	 */
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {

		if (nums.length > 3) {
			heapSort(nums);
			return recursiveKSum(nums, 0, 4, target);
		}

		return new ArrayList<List<Integer>>();
	}

	private static List<List<Integer>> recursiveKSum(int[] nums, int startIndex, int order, int target) {

		List<List<Integer>> resultList = new ArrayList<List<Integer>>();

		if (order > 2) {
			for (int i = startIndex; i < nums.length - order + 1; i++) {
				if (i > startIndex && nums[i] == nums[i - 1]) {
					continue;
				}

				List<List<Integer>> subResultList = recursiveKSum(nums, i + 1, order - 1, target - nums[i]);
				if (subResultList.size() > 0) {
					for (int j = 0; j < subResultList.size(); j++) {
						List<Integer> subResult = subResultList.get(j);
						List<Integer> result = new ArrayList<Integer>();
						result.add(nums[i]);
						for (int k = 0; k < subResult.size(); k++) {
							result.add(subResult.get(k));
						}
						resultList.add(result);
					}
				}
			}
		} else {
			int leftIndex = startIndex;
			int rightIndex = nums.length - 1;

			while (leftIndex < rightIndex) {
				int sum = nums[leftIndex] + nums[rightIndex];
				if (sum == target) {
					List<Integer> result = new ArrayList<Integer>();
					result.add(nums[leftIndex]);
					result.add(nums[rightIndex]);
					if (resultList.indexOf(result) == -1) {
						resultList.add(result);
					}
					leftIndex++;
					rightIndex--;
				} 
				if (sum < target) {
					leftIndex++;
				}
				if (sum > target) {
					rightIndex--;
				}
			}
		}

		return resultList;
	}

	private static void heapSort(int[] nums) {

		buildMaxRootHeap(nums);
		int end = nums.length - 1;
		for (int i = 0; i < nums.length - 1; i++) {
			swap(nums, 0, end);
			end--;
			maxRootHeapify(nums, 0, end);
		}
	}

	private static void maxRootHeapify(int[] nums, int node, int end) {

		int leftNode = (node << 1) + 1;
		int rightNode = (node << 1) + 2;
		int maxNode = node;

		if (leftNode <= end && nums[maxNode] < nums[leftNode]) {
			maxNode = leftNode;
		}
		if (rightNode <= end && nums[maxNode] < nums[rightNode]) {
			maxNode = rightNode;
		}
		if (maxNode != node) {
			swap(nums, node, maxNode);
			maxRootHeapify(nums, maxNode, end);
		}
	}

	private static void buildMaxRootHeap(int[] nums) {

		for (int i = (nums.length - 2) >> 1; i >= 0; i--) {
			maxRootHeapify(nums, i, nums.length - 1);
		}
	}

	private static void swap(int[] nums, int indexA, int indexB) {

		int temp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = temp;
	}
}
