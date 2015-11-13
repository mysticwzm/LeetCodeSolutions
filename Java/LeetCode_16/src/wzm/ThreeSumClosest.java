package wzm;

public class ThreeSumClosest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = { 87, 6, -100, -19, 10, -8, -58, 56, 14, -1, -42, -45, -17, 10, 20, -4, 13, -17, 0, 11, -44, 65,
				74, -48, 30, -91, 13, -53, 76, -69, -19, -69, 16, 78, -56, 27, 41, 67, -79, -2, 30, -13, -60, 39, 95,
				64, -12, 45, -52, 45, -44, 73, 97, 100, -19, -16, -26, 58, -61, 53, 70, 1, -83, 11, -35, -7, 61, 30, 17,
				98, 29, 52, 75, -73, -73, -23, -75, 91, 3, -57, 91, 50, 42, 74, -7, 62, 17, -91, 55, 94, -21, -36, 73,
				19, -61, -82, 73, 1, -10, -40, 11, 54, -81, 20, 40, -29, 96, 89, 57, 10, -16, -34, -56, 69, 76, 49, 76,
				82, 80, 58, -47, 12, 17, 77, -75, -24, 11, -45, 60, 65, 55, -89, 49, -19, 4 };
		int target = -275;

		System.out.println(threeSumClosest(nums, target));
	}

	/** 
	 * The idea of this solution is similar to threeSum, please refer to ThreeSum.
	 * Time complexity is O(nlogn)(quick sort) + O(n^2)(two pointers enumeration).
	 * @param nums
	 * @param target
	 * @return
	 */
	
	public static int threeSumClosest(int[] nums, int target) {

		int closestNumber = nums[0] + nums[1] + nums[2];
		if (nums.length > 3) {
			quickSort(nums, 0, nums.length - 1);
			for (int i = 0; i < nums.length - 2; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				int currentClosest = nums[i] + twoSumClosest(nums, i + 1, target - nums[i]);
				closestNumber = closer(closestNumber, currentClosest, target);
				if (closestNumber == target) {
					return target;
				}
			}
		}

		return closestNumber;
	}

	private static int twoSumClosest(int[] nums, int startIndex, int target) {

		int leftIndex = startIndex;
		int rightIndex = nums.length - 1;
		int closestNumber = nums[startIndex] + nums[startIndex + 1];

		while (leftIndex < rightIndex) {
			int sum = nums[leftIndex] + nums[rightIndex];
			closestNumber = closer(closestNumber, sum, target);
			if (sum == target) {
				return sum;
			}
			if (sum < target) {
				leftIndex++;
			}
			if (sum > target) {
				rightIndex--;
			}
		}

		return closestNumber;
	}

	private static int closer(int closestNumber, int currentClosest, int target) {

		if (Math.abs(target - closestNumber) < Math.abs(target - currentClosest)) {
			return closestNumber;
		}
		return currentClosest;
	}

	private static void quickSort(int[] nums, int left, int right) {

		int leftIndex = left;
		int rightIndex = right;
		int mid = (nums[left] + nums[right]) >> 1;

		while (leftIndex <= rightIndex) {
			while (nums[leftIndex] < mid) {
				leftIndex++;
			}
			while (nums[rightIndex] > mid) {
				rightIndex--;
			}
			if (leftIndex <= rightIndex) {
				swap(nums, leftIndex, rightIndex);
				leftIndex++;
				rightIndex--;
			}
		}
		if (leftIndex < right) {
			quickSort(nums, leftIndex, right);
		}
		if (left < rightIndex) {
			quickSort(nums, left, rightIndex);
		}
	}

	private static void swap(int[] nums, int indexA, int indexB) {

		int temp = nums[indexA];
		nums[indexA] = nums[indexB];
		nums[indexB] = temp;
	}
}
