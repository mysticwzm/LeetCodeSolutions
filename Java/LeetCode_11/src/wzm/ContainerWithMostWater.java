package wzm;

public class ContainerWithMostWater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] height = { 1, 1 };
		System.out.println(maxArea(height));
	}

	/**
	 * We assume that the maximum area consists of a[i] and a[j].
	 * Therefore, a[0...i - 1] must be smaller than a[j] and similarly a[j + 1...end] must be smaller than a[i].
	 * As a result, the pair of a[i] and a[j] will be covered in the process.
	 * Time complexity is O(n).
	 * @param height
	 * @return
	 */
	
	public static int maxArea(int[] height) {

		int leftEnd = 0;
		int rightEnd = height.length - 1;
		int maxArea = 0;

		while (leftEnd < rightEnd) {
			maxArea = Math.max(maxArea, Math.min(height[leftEnd], height[rightEnd]) * (rightEnd - leftEnd));
			if (height[leftEnd] < height[rightEnd]) {			// we assume that the maximum area consists of a[i] and a[j] 
				leftEnd++;										// therefore, a[0...i - 1] must be smaller than a[j]
			} else {											// and similarly a[j + 1...end] must be smaller than a[i]
				rightEnd--;										// as a result, the pair of a[i] and a[j] will be covered in the process
			}
		}

		return maxArea;
	}
}
