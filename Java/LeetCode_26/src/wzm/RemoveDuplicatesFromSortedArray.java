package wzm;

public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 1, 1, 2, 2, 3, 5 };

		System.out.println(removeDuplicates(nums));
	}
	
	/**
	 * Solved by two pointers, one for current index and one for index of next unique number.
	 * Once, there is a new unique number, swap number in these two pointers,
	 * and advance the index of next unique number.
	 * Time complexity is O(n).
	 * @param nums
	 * @return
	 */
	
	public static int removeDuplicates(int[] nums) {
	
		int uniqueNumber = 0;
		int previousNumber = 0;

		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				previousNumber = nums[i];
				uniqueNumber++;
			} else {
				if (nums[i] == previousNumber) {
					continue;
				} else {
					previousNumber = nums[i];
					swap(nums, uniqueNumber, i);
					uniqueNumber++;
				}
			}
		}

		return uniqueNumber;	
	}

	private static void swap(int[] nums, int indexA, int indexB) {

		int temp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = temp;
	}
}
