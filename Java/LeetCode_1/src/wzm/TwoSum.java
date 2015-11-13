package wzm;

import java.util.HashMap;

public class TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] nums = {2, 7, 11, 15};
		int target = 9;
		
		int[] indices = twoSum(nums, target);
		String output = "[" + indices[0] + ", " + indices[1] + "]";
		System.out.println(output);
		
	}

	/**
	 * Solved with hash map, hashmap.get(target - nums[i]) != null, return that pair.
	 * Time complexity is O(n).
	 * @param nums
	 * @param target
	 * @return
	 */
	
	public static int[] twoSum(int[] nums, int target) {
		
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < nums.length; i++) {
			if (hashMap.get(target - nums[i]) != null ) {
				return new int[] {hashMap.get(target - nums[i]), i + 1};	// if target - nums[i] is already in the hashMap, return two indices
			}
			else {
				hashMap.put(nums[i], i + 1);	// put this nums[i] into hashMap
			}
		}
		return new int[] {0, 0};
	}
}
