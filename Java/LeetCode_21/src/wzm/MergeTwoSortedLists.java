package wzm;

public class MergeTwoSortedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListNode l1 = new ListNode(1);
		ListNode node1 = l1;
		ListNode l2 = new ListNode(2);
		ListNode node2 = l2;

		for (int i = 1; i < 3; i++) {
			ListNode newNode1 = new ListNode((i << 1) + 1);
			ListNode newNode2 = new ListNode((i << 1) + 2);
			node1.next = newNode1;
			node2.next = newNode2;
			node1 = node1.next;
			node2 = node2.next;
		}

		ListNode head = mergeTwoLists(l1, l2);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	/**
	 * Easy.
	 * Time complexity is O(m + n).
	 * @param l1
	 * @param l2
	 * @return
	 */

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

		ListNode head = new ListNode(0);
		ListNode node = head;

		if (l1 == null && l2 == null) {
			return null;
		}

		while (l1 != null & l2 != null) {
			if (l1.val < l2.val) {
				node.val = l1.val;
				l1 = l1.next;
			} else {
				node.val = l2.val;
				l2 = l2.next;
			}
			ListNode nextNode = new ListNode(0);
			node.next = nextNode;
			node = node.next;
		}

		while (l1 != null) {
			node.val = l1.val;
			if (l1.next != null) {
				l1 = l1.next;
				ListNode nextNode = new ListNode(0);
				node.next = nextNode;
				node = node.next;
			} else {
				break;
			}
		}

		while (l2 != null) {
			node.val = l2.val;
			if (l2.next != null) {
				l2 = l2.next;
				ListNode nextNode = new ListNode(0);
				node.next = nextNode;
				node = node.next;
			} else {
				break;
			}
		}

		return head;
	}
}
