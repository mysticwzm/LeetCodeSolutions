package wzm;

public class RotateList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode head = new ListNode(1);
		ListNode node = head;
		for (int i = 2; i < 4; i++) {
			ListNode temp = new ListNode(i);
			node.next = temp;
			node = temp;
		}
		head = rotateRight(head, 2000000000);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	/**
	 * The description of this problem is quite misleading.
	 * The list actually is looked as an anti-clockwise circle, like
	 * 		1
	 * 2		 4
	 * 		3 
	 * Once rotate right, now head is at 4, and the output order is 4->1->2->3 (anti-clockwise).
	 * Therefore, the times that moving the first node to the end is (length - k % length).
	 * Time complexity is O(n). 
	 * @param head the head node of original node list
	 * @param k times of rotation
	 * @return the new head node of node list
	 */
	
	public static ListNode rotateRight(ListNode head, int k) {

		ListNode tail = head;

		if (head != null) {
			int length = 1;
			while (tail.next != null) {
				tail = tail.next;
				length++;
			}

			for (int i = 0; i < length - k % length; i++) {
				tail.next = head;
				head = head.next;
				tail = tail.next;
				tail.next = null;
			}
		}

		return head;
	}
}
