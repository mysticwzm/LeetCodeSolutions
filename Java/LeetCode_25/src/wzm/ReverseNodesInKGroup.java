package wzm;

public class ReverseNodesInKGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int k = 3;
		ListNode head = new ListNode(0);
		ListNode node = head;

		for (int i = 1; i < 10; i++) {
			ListNode newNode = new ListNode(i);
			node.next = newNode;
			node = node.next;
		}
		
		head = reverseKGroup(head, k);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}
	
	/**
	 * Solved with stack.
	 * In the stack, stack[i] --> stack[i - 1] --> ... ---> stack[0].
	 * Out of the stack, previous node --> group --> next node.
	 * Time complexity is O(n), and space complexity is O(k) (size of stack).
	 * @param head
	 * @param k
	 * @return
	 */
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		
		ListNode[] nodeStack = new ListNode[k];
		ListNode node = head;
		ListNode nextNode = null;
		ListNode previousNode = null;
		int groupSize = 0;
		
		if (head != null) {
			while (node != null) {
				nodeStack[groupSize] = node;
				node = node.next;
				groupSize++;
				if (groupSize == k) {
					if (previousNode == null) {
						head = nodeStack[groupSize - 1];				// for the first group, assign the new head pointer
					}
					if (previousNode != null) {
						previousNode.next = nodeStack[groupSize - 1];	// link this group to its previous node
					}
					nextNode = nodeStack[groupSize - 1].next;			// assign the next node of this group
					previousNode = nodeStack[0];						// the last node in this group is the previous node for next group
					reverseGroup(nodeStack, nextNode);					// reverse this group
					groupSize = 0;										// clear the node stack
				}
			}
		}

		return head;
	}

	private static void reverseGroup(ListNode[] nodeStack, ListNode nextNode) {

		int k = nodeStack.length - 1;
		
		for (int i = k; i > 0; i--) {
			nodeStack[i].next = nodeStack[i - 1];						// stack[i] --> stack[i - 1] --> ... ---> stack[0]
		}
		nodeStack[0].next = nextNode;									// link this group to its next node
	}
}
