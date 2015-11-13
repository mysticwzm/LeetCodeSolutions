package wzm;

public class SearchForARange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 1 };
		int target = 0;

		System.out.println(searchRange(nums, target).toString());
	}
	
	/**
	 * Solved by binary search. 
	 * Time complexity is O(logn).
	 * @param nums
	 * @param target
	 * @return
	 */
	
	public static int[] searchRange(int[] nums, int target) {
		
		int[] range = { nums.length , -1 };

		binarySearch(nums, range, 0, nums.length - 1, target);
		range[0] = range[0] > range[1] ? -1 : range[0];
		
		return range;
	}

	private static void binarySearch(int[] nums, int[] range, int leftBound, int rightBound, int target) {

		if (leftBound > rightBound || target < nums[leftBound] || target > nums[rightBound]) {
			return;
		}
		
		int mid = (leftBound + rightBound) >> 1;
		int pivot = nums[mid];

		if (target < pivot) {											// target < mid value, search left part
			binarySearch(nums, range, leftBound, mid - 1, target);
		}
		if (pivot < target) {											// mid value < target, search right part
			binarySearch(nums, range, mid + 1, rightBound, target);
		}
		if (pivot == target) {
			if (mid < range[0]) {										// mid ... range[0], search the left part
				range[0] = mid;											// to find out is there any target value in
				binarySearch(nums, range, leftBound, mid - 1, target);	// the left part
			}
			if (mid > range[1]) {										// range[1] ... mid, search the right part
				range[1] = mid;											// to find out is there any target value in
				binarySearch(nums, range, mid + 1, rightBound, target);	// the right part
			}
		}
	}
}
