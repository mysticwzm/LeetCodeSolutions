package wzm;

public class FirstMissingPositive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 4, 3, -1, 1 };

		System.out.println(firstMissingPositive(nums));
	}

	/**
	 * Solved by an idea similar to bucket sort, storing value (i + 1) in nums[i].
	 * While nums[i] is not value (i + 1) and it is in range(0, nums.length], swap it with nums[nums[i] - 1],
	 * if nums[nums[i] - 1] is not nums[i] (nums[i] has not been found).
	 * After swapping, if the current nums[i] is not value (i + 1) yet, repeat previous steps.
	 * After the first traverse, traverse the array again, return the first i + 1, if nums[i] is not i + 1,
	 * or nums.length + 1, if [1..nums.length] all have occurred in nums.
	 * Time complexity is 2 * O(n).
	 * @param nums
	 * @return
	 */

	public static int firstMissingPositive(int[] nums) {

		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i + 1 && nums[i] <= nums.length && nums[i] > 0 && nums[nums[i] - 1] != nums[i]) {
				swap(nums, i, nums[i] - 1);
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}

		return nums.length + 1;
	}

	private static void swap(int[] nums, int a, int b) {

		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}
}
