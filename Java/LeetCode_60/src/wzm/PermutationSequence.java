package wzm;

import java.util.List;
import java.util.ArrayList;

public class PermutationSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 9;
		int k = 6;
		
		System.out.println(getPermutation(n, k));
	}
	
	/**
	 * Solve by math. n numbers could have at most n! permutations.
	 * The idea is using k / (n - 1)! to determine the number in this bit.
	 * Then assign k % (n - 1)! to k, until k == 0 or 1.
	 * Time complexity is O(n).
	 * @param n the number of numbers
	 * @param k permutation index
	 * @return the Kth permutation
	 */
	
	public static String getPermutation(int n, int k) {
		
		int[] factorials = new int[n + 1];
		factorials[1] = 1;
		for (int i = 2; i <= n; i++) {
			factorials[i] = factorials[i - 1] * i;
		}
		List<Integer> available = new ArrayList<Integer>();
		for (int i = 0; i <= n; i++) {
			available.add(i);
		}
		StringBuilder permutation = new StringBuilder();
		for (int i = n - 1; i >= 0; i--) {
			if (k == 0) {											// the following numbers in reversed order
				for (int j = available.size() - 1; j > 0; j--) {	// k == 0 means this is some last permutation
					permutation.append(available.get(j));
				}
				break;
			}
			if (k == 1) {											// the following number in normal order
				for (int j = 1; j < available.size(); j++) {		// k == 1 means this is some first permutation
					permutation.append(available.get(j));
				}
				break;
			}
			if (k != 0) {
				int quotient = k / factorials[i];
				k %= factorials[i];
				if (k != 0) {
					quotient++;
				}
				permutation.append(available.remove(quotient));
			}
		}

		return permutation.toString();
	}
}
