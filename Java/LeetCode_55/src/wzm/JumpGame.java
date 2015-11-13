package wzm;

public class JumpGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums1 = { 2, 3, 1, 1, 4 };
		int[] nums2 = { 3, 2, 1, 0, 4 };

		System.out.println(canJump(nums1));
		System.out.println(canJump(nums2));
	}

	/**
	 * Solved by greedy strategy, similar to JumpGame2(LeetCode_45).
	 * Time complexity is O(n).
	 * @param nums
	 * @return
	 */

	public static boolean canJump(int[] nums) {

		int currentBegin = 0;
		int currentEnd = 0;
		int nextEnd = 0;

		while (currentBegin <= currentEnd) {
			for (int i = currentBegin; i <= currentEnd; i++) {
				nextEnd = nums[i] + i > nextEnd ? nums[i] + i : nextEnd;
			}
			if (nextEnd >= nums.length - 1) {
				break;
			}
			currentBegin = currentEnd + 1;
			currentEnd = nextEnd;
		}

		return nextEnd >= nums.length - 1;
	}
}
