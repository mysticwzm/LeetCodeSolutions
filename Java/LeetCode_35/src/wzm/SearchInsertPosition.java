package wzm;

public class SearchInsertPosition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 1, 3, 5, 7, 9 };
		int target = 0;

		System.out.println(searchInsert(nums, target));
	}
	
	/**
	 * Solved by binary search, duplicates handled.
	 * Time complexity is O(logn).
	 * @param nums
	 * @param target
	 * @return
	 */
	
	public static int searchInsert(int[] nums, int target) {
		
		return binarySearch(nums, 0, nums.length - 1, target);
	}

	private static int binarySearch(int[] nums, int left, int right, int target) {

		int mid = (left + right) >> 1;
		mid = mid > 0 ? mid : 0;
		int pivot = nums[mid];

		if (left >= right) {
			return target <= pivot ? mid : mid + 1;
		}
		if (target <= pivot) {									// when target = pivot, always search the left part
			return binarySearch(nums, left, mid - 1, target);
		} else {
			return binarySearch(nums, mid + 1, right, target);
		}
	}
}
