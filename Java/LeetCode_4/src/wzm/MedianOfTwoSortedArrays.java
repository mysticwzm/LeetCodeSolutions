package wzm;

public class MedianOfTwoSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int nums1[] = {2,3,4};
		int nums2[] = {1};
		
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	
	/**
	 * Solved by binary search.
	 * Every time, enumerate two element, nums1[head1 + k / 2] and nums2[head2 + k / 2].
	 * If nums1[head1 + k / 2] < nums2[head2 + k / 2], abandon nums1[head1...head1 + k / 2 - 1]
	 * Otherwise if it is >, abandon num2[head2...head2 + k / 2 - 1]
	 * There is a special case, nums1[head1 + k / 2] == nums2[head2 + k / 2],
	 * each of these two reference is Kth value of two arrays.
	 * Time complexity is O(log(m + n)).
	 * @param nums1
	 * @param nums2
	 * @return
	 */

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		boolean twoMedian = (nums1.length + nums2.length) % 2 == 0 ? true : false;	// if the number of elements is even
		int k = (nums1.length + nums2.length + 1) / 2;								// the result is average of two central elements
		
		if (!twoMedian) {
			return (double)findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k);
		} else {
			return (double)(findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k) + findKth(nums1, 0, nums1.length, nums2, 0, nums2.length, k + 1)) / 2;
		}
	}
	
	public static int findKth(int[] nums1, int head1, int left1, int[] nums2, int head2, int left2, int k) {
		
		int k1 = Math.min(k / 2, left1);									// we assume that array1 is shorter than array2
		int k2 = k - k1;													// allocate K1 and K2
		int p1 = head1 + k1;												// the indices of references of array1 and array2
		int p2 = head2 + k2;
		
		if (left1 > left2) {												// swap array1 and array2 if array1 is longer than array2
			return findKth(nums2, head2, left2, nums1, head1, left1, k);
		}
		if (left1 == 0) {													// array1 is empty, return specific element in array2
			return nums2[head2 + k - 1];
		}
		if (left2 == 0) {													// array2 is empty, return specific element in array1
			return nums1[head1 + k - 1];
		}
		if (k == 1) {														// binary search based on k, last step
			return (Math.min(nums1[head1], nums2[head2]));					// return the smaller first value of array1 and array2
		}
		if (nums1[p1 - 1] < nums2[p2 - 1]) {								// if the reference of array2 is larger than array1
			return findKth(nums1, p1, left1 - k1, nums2, head2, left2, k2);	// cut down a chunk of k1 elements in array1
		} else if (nums1[p1 - 1] > nums2[p2 - 1]) {
			return findKth(nums1, head1, left1, nums2, p2, left2 - k2, k1);	// else cut down a chunk of k2 elements in array2
		} else {
			return nums1[p1 - 1];											// if equal, then either reference is the result
		}
	}
}
