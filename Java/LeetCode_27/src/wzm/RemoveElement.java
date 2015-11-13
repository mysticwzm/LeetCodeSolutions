package wzm;

public class RemoveElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 0, 1, 2, 3, 5, 5, 6 };
		int val = 7;

		System.out.println(removeElement(nums, val));
	}

	/**
	 * Solved by two pointers, the numbers before head and itself are not equal to val.
	 * On the other hand, the numbers after tail(tail not included) are all equal to val.
	 * Time complexity is O(n).
	 * @param nums
	 * @param val
	 * @return
	 */

	public static int removeElement(int[] nums, int val) {

		int head = 0;
		int tail = nums.length - 1;

		while (head <= tail) {									// this procedure is pretty like quick sort
			while (head <= tail && nums[head] != val) {
				head++;
			}
			while (head <= tail && nums[tail] == val) {
				tail--;
			}
			if (head < tail) {
				swap(nums, head, tail);
				head++;
				tail--;
			}
		}

		return head;
	}

	private static void swap(int[] nums, int indexA, int indexB) {

		int temp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = temp;
	}
}
