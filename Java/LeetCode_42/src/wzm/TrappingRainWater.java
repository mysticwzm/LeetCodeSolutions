package wzm;

import java.util.Stack;

public class TrappingRainWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] height = { 5, 5, 1, 7, 1, 1, 5, 2, 7, 6 };

		System.out.println(trapStackEdition(height));
		System.out.println(trapTwoPointersEdition(height));
	}

	/**
	 * Solved with stack. Typeically, the left wall is in descending order, while right wall is in ascending order.
	 * Hence, store all height and indices of left walls in stack, once there is a right wall, begin to fill in water.
	 * Furthermore, we need a variable 'bottom' to maintain the current horizontal.
	 * Time complexity is O(n).
	 * @param height
	 * @return
	 */

	public static int trapStackEdition(int[] height) {

		Stack<Integer> heightStack = new Stack<Integer>();
		Stack<Integer> indexStack = new Stack<Integer>();
		boolean ascending = false;
		int bottom = 0;
		int volume = 0;

		for (int i = 1; i < height.length; i++) {
			ascending = height[i] > height[i - 1];									// determine ascending or descending order
			if (!ascending) {														// if descending, push left wall into stack
				if (!heightStack.empty() && heightStack.peek() == height[i - 1]) {	// if two left walls in same height
					heightStack.pop();												// pop the first one out, store the second one
					indexStack.pop();
				}
				heightStack.push(height[i - 1]);									// push the left wall into stack
				indexStack.push(i);
				bottom = height[i];													// update bottom as "digging container deeper"
			}
			if (ascending && !heightStack.empty()) {								// if ascending and there is left wall
				while (!heightStack.empty() && heightStack.peek() <= height[i]) {	// while right wall is higher than current left wall
					volume += (heightStack.peek() - bottom) * (i - indexStack.peek());
					bottom = heightStack.peek();									// accumulate volume and rise bottom to height of 
					heightStack.pop();												// current left wall as water is filling in
					indexStack.pop();												// pop this left wall out
				}
				if (!heightStack.empty()) {											// if the right wall is not higher than the 
					volume += (height[i] - bottom) * (i - indexStack.peek());		// highest left wall, fill water in up to height
					bottom = height[i];												// of right wall and bottom now is rised to 
				}																	// height of right wall
			}
		}

		return volume;
	}

	/**
	 * Solved with two pointers. The basic idea is maintain two pointers as the height of highest left and right walls.
	 * Then, fill in water up to the lower wall. For each lower wall, fill in water with volume maxLeft/RightHeight - height[i].
	 * Water will only be filled in concave part, e.g. water filled in 0, 0, in case of 1, 0, 0, 2.
	 * Therefore, fill in water when current height is lower than maxLeft/RightHeight, which is similar to the situation in 
	 * stack edition, when there is left wall left in stack, fill in water.
	 * When there is a new higher wall, update correspongding maxHeight util there is a new lower wall, then fill in water.
	 * It is also similar to the situation in stack edition: do not store any wall if it is ascending order, ever if there is 
	 * no left wall left in stack and new higher wall occurs. Only store wall in descending order.
	 * In summary, water only can be contained in concave part not in convex part.
	 * @param height
	 * @return
	 */
	
	public static int trapTwoPointersEdition(int[] height) {

		int left = 0;
		int right = height.length - 1;
		int volume = 0;
		int maxLeftHeight = 0;
		int maxRightHeight = 0;

		while (left < right) {
			if (height[left] <= height[right]) {
				if (height[left] >= maxLeftHeight) {
					maxLeftHeight = height[left];
				} else {
					volume += maxLeftHeight - height[left];
				}
				left++;
			} else {
				if (height[right] >= maxRightHeight) {
					maxRightHeight = height[right];
				} else {
					volume += maxRightHeight - height[right];
				}
				right--;
			}
		}

		return volume;
	}
}