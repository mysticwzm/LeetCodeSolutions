package wzm;

public class SearchInRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int nums[] = { 3, 5, 1 };
		int target = 1;

		System.out.println(search(nums, target));
	}

	/**
	 * Solved by binary search.
	 * 1. Find the pivot where the array is rotated, or the minimum value of the array.
	 * 	  The part which contains pivot has such property: the head is larger than tail.
	 * 2. Until the size of part is 2, return the element at right side, which is the pivot.
	 * 3. Determine which part target belongs to, before pivot(larger part) or after pivot(small part).
	 * Time complexity is 2 * O(logn).
	 * There is an one pass solution:
	 * After rotation, divide the array into 2 equal parts, one is always sorted, and the other one is not.
	 * Keep this kind of binary division, until the target is in a sorted part. Then, binary search.
	 * @param nums
	 * @param target
	 * @return
	 */

	public static int search(int[] nums, int target) {

		int length = nums.length;
		if (nums[0] < nums[length - 1] || length == 1) {						// not rotated or only 1 element
			return binarySearch(nums, 0, length - 1, target);
		}

		int indexOfPivot = findIndexOfPivot(nums, 0, length - 1);				// find the pivot
		if (nums[indexOfPivot] <= target && target <= nums[length - 1]) {		// binary search
			return binarySearch(nums, indexOfPivot, length - 1, target);
		}
		if (nums[0] <= target && target <= nums[indexOfPivot - 1]) {
			return binarySearch(nums, 0, indexOfPivot - 1, target);
		}
		return -1;
	}

	private static int findIndexOfPivot(int[] nums, int leftBound, int rightBound) {

		int mid = (leftBound + rightBound) >> 1;
		int pivot = nums[mid];
		int left = nums[leftBound];
		int right = nums[rightBound];

		if (rightBound - leftBound < 2) {								// pair(maximum, minimum), return right one
			return rightBound;
		}
		if (left > pivot) {												// left part is unsorted
			return findIndexOfPivot(nums, leftBound, mid);
		}
		if (pivot > right) {											// right part is unsorted
			return findIndexOfPivot(nums, mid, rightBound);
		}
		return 0;
	}

	private static int binarySearch(int[] nums, int leftBound, int rightBound, int target) {

		if (leftBound == rightBound || target < nums[leftBound] || target > nums[rightBound]) {
			return target == nums[leftBound] ? leftBound : -1;
		}

		int mid = (leftBound + rightBound) >> 1;
		int pivot = nums[mid];

		if (pivot < target) {												// search the left part
			return binarySearch(nums, mid + 1, rightBound, target);
		}											
		if (pivot > target) {												// search the right part
			return binarySearch(nums, leftBound, mid - 1, target);
		}
		return mid;
	}
}
