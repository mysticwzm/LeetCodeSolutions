package wzm;

public class NextPermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 2, 3, 1 };
		
		nextPermutation(nums);
		System.out.println(nums);
	}

	/**
	 * 1. traverse the array from end to start, to orientate the index i, where nums[i] > nums[i - 1].
	 * 2. find the nums[k], which is the minimum value larger than nums[i - 1] in nums[i ... n - 1].
	 * 3. swap nums[k] and nums[i - 1].
	 * 4. sort nums[i ... n - 1] from low to high, but in this case, nums[i ... n - 1] are already ranked
	 *    from high to low, just simply reverse nums[i ... n - 1].
	 * Time complexity is O(n), however, I use quick sort instead of reversing, mine is O(nlogn).
	 * In other words, this solution is not the best, could be better by using reversing in step 4.
	 * @param nums
	 */

	public static void nextPermutation(int[] nums) {

		int stopPoint = nums.length - 1;

		while (stopPoint > 0) {
			if (nums[stopPoint] > nums[stopPoint - 1]) {
				break;
			}
			stopPoint--;
		}

		if (stopPoint != 0) {
			int minValue = nums[stopPoint];
			int minIndex = stopPoint;
			for (int i = stopPoint; i < nums.length; i++) {
				if (nums[i] > nums[stopPoint - 1] && nums[i] < minValue) {
					minValue = nums[i];
					minIndex = i;
				}
			}
			swap(nums, minIndex, stopPoint - 1);
		}
		quickSort(nums, stopPoint, nums.length - 1);
	}

	private static void swap(int[] nums, int indexA, int indexB) {

		int temp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = temp;
	}

	private static void quickSort(int[] nums, int leftBound, int rightBound) {

		int left = leftBound;
		int right = rightBound;
		int pivot = nums[(left + right) >> 1];

		while (left <= right) {
			while (nums[left] < pivot) {
				left++;
			}
			while (nums[right] > pivot) {
				right--;
			}
			if (left <= right) {
				swap(nums, left, right);
				left++;
				right--;
			}
		}
		if (left < rightBound) {
			quickSort(nums, left, rightBound);
		}
		if (leftBound < right) {
			quickSort(nums, leftBound, right);
		}
	}
}
