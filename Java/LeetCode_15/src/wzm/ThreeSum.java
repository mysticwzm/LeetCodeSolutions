package wzm;

import java.util.ArrayList;
import java.util.List;

public class ThreeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { -2,0,0,2,2 };
		System.out.println(threeSum(nums).toString());
	}

	/**
	 * Solved by order reduction, three sum --> two sum.
	 * Firstly, merge sort the array as an ascending sequence, from low to high.
	 * Secondly, enumerate an element, pick pairs which are equal to target - element out in its following sequence.
	 * Time complexity is O(nlogn)(merge sort) + O(n^2)(two pointers enumeration).
	 * @param nums
	 * @return
	 */
	
	public static List<List<Integer>> threeSum(int[] nums) {

		List<List<Integer>> tripletList = new ArrayList<List<Integer>>();

		if (nums.length > 2) {
			mergeSort(nums, 0, nums.length - 1);
			for (int i = 0; i < nums.length - 2; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				List<int[]> pairList = twoSum(nums, i + 1, -nums[i]);
				if (pairList.size() > 0) {
					for (int[] pair : pairList) {
						ArrayList<Integer> triplet = new ArrayList<Integer>();
						triplet.add(nums[i]);
						triplet.add(pair[0]);
						triplet.add(pair[1]);
						if (tripletList.indexOf(triplet) == -1) {				// check is this triplet already included in tripletList
							tripletList.add(triplet);
						}
					}
				}
			}
		}

		return tripletList;
	}

	public static List<int[]> twoSum(int[] nums, int startIndex, int target) {

		List<int[]> pairList = new ArrayList<int[]>();
		int leftIndex = startIndex;
		int rightIndex = nums.length - 1;

		while (leftIndex < rightIndex) {
			int sum = nums[leftIndex] + nums[rightIndex];
			if (sum == target) {
				int[] pair = { nums[leftIndex], nums[rightIndex] };
				pairList.add(pair);
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

		return pairList;
	}

	public static void mergeSort(int[] nums, int left, int right) {

		if (right == left) {												// if there is only one element, backtrack
			return;
		}

		int mid = (left + right) >> 1;
		mergeSort(nums, left, mid);											// recursively merge sort the left part of array
		mergeSort(nums, mid + 1, right);									// recursively merge sort the right part of array
		mergeTwoSortedArrays(nums, left, mid, right);						// merge two sorted arrays
	}

	public static void mergeTwoSortedArrays(int[] nums, int left, int mid, int right) {

		int leftIndex = 0;
		int rightIndex = 0;
		int[] leftArray = new int[mid - left + 1];							
		int[] rightArray = new int[right - mid];
		int mergedNumber = 0;

		for (int i = 0; i < leftArray.length; i++) {						// copy the left and right array
			leftArray[i] = nums[left + i];
		}
		for (int i = 0; i < rightArray.length; i++) {
			rightArray[i] = nums[mid + 1 + i];
		}

		while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
			if (leftArray[leftIndex] <= rightArray[rightIndex]) {
				nums[left + mergedNumber] = leftArray[leftIndex];			// left <= right
				leftIndex++;
			} else {
				nums[left + mergedNumber] = rightArray[rightIndex];			// left > right
				rightIndex++;
			}
			mergedNumber++;
		}
		if (leftIndex == leftArray.length) {
			for (int i = mergedNumber; i < right - left + 1; i++) {			// merge the remaining part
				nums[left + i] = rightArray[rightIndex];
				rightIndex++;
			}
		} else {
			for (int i = mergedNumber; i < right - left + 1; i++) {
				nums[left + i] = leftArray[leftIndex];
				leftIndex++;
			}
		}
	}
}
