package wzm;

public class JumpGame2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 0 };

		System.out.println(jump(nums));
	}

	/**
	 * Solved by breadth first search. Every step is looked as a stage.
	 * Time complexity is O(n).
	 * @param nums
	 * @return
	 */

	public static int jump(int[] nums) {

		int currentBegin = 0;
		int currentEnd = 0;
		int nextEnd = 0;
		int step = 0;

		while (currentEnd < nums.length - 1) {
			for (int i = currentBegin; i <= currentEnd; i++) {
				nextEnd = Math.max(nums[i] + i, nextEnd);			// determine the farthest index in this step
			}
			currentBegin = currentEnd + 1;							// assign the begin of next step
			currentEnd = nextEnd;									// assign the end of next step
			step++;													// advance step
		}
		
		return step;
	}
}
