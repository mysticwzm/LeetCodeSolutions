package wzm;

public class MaximumSubarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

		System.out.println(maxSubarray(nums));
	}
	
	/**
	 * Solved by dynamic programming.
	 * max[i] = max[i - 1] + nums[i] (max[i - 1] >= 0)
	 *        = nums[i]              (max[i - 1] < 0)
	 * The basic idea is if the previous sum is positive, then add it to current number to achieve a greater sum.
	 * Otherwise, abandon the previous sum and assign nums[i] to max[i], which aims to avoid a lesser sum.
	 * @param nums
	 * @return
	 */
	
	public static int maxSubarray(int[] nums) {
		
		int[] max = new int[nums.length];
		max[0] = nums[0];
		int maxSum = max[0];
		for (int i = 1; i < nums.length; i++) {
			max[i] = max[i - 1] < 0 ? nums[i] : max[i - 1] + nums[i];
			maxSum = max[i] > maxSum ? max[i] : maxSum;
		}

		return maxSum;
	}
}
