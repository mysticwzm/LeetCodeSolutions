package wzm;

public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode head = new ListNode(1);
		ListNode node = head;

		for (int i = 2; i < 6; i++) {
			ListNode newNode = new ListNode(i);
			node.next = newNode;
			node = node.next;
		}

		head = removeNthNodeFromEnd(head, 1);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	/**
	 * Solved by two pointers.
	 * Two speical case: 1. only 1 node. 2. remove the last node.
	 * Time complexity O(n).
	 * @param head
	 * @param n
	 * @return
	 */

	public static ListNode removeNthNodeFromEnd(ListNode head, int n) {

		ListNode currentNode = head;
		ListNode advancedNode = head;
		
		if (head.next == null) {						// special case : only 1 node
			return null;					
		}
		
		for (int i = 0; i < n - 1; i++) {
			advancedNode = advancedNode.next;			// advance the second pointer
		}
		while (advancedNode.next != null) {				// advance the main pointer
			advancedNode = advancedNode.next;
			if (currentNode.next != null) {				// in case of remove last node
				if (currentNode.next.next == null) {	// delete the last node rather than step into last node
					currentNode.next = null;
				} else {
					currentNode = currentNode.next;
				}
			}
		}
		if (currentNode.next != null) {					// if current node is not last node 
			currentNode.val = currentNode.next.val;		// copy all attributes in the next node to current node
			currentNode.next = currentNode.next.next;	// and abandon the next node
		}

		return head;
	}
}
