package wzm;

public class MergeKSortedLists {

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

		ListNode[] lists = { l1, l2 };
		ListNode head = mergeKLists(lists);
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
	}

	/**
	 * Solved with min root heap/priority queue.
	 * Firstly, pick all non-null lists out.
	 * Secondly, build up a min root heap based on all first elements of non-null lists.
	 * Then, everytime, pop the root of heap(minimum) out, re-heapify.
	 * Time complexity is O(nlogk), where n is the length of final merged list, and k is number of non-null lists.
	 * There is another elegant solution: divide and conquer.
	 * Every run, merge two neighbouring lists togerther, until there is only one list left.
	 * @param lists
	 * @return
	 */

	public static ListNode mergeKLists(ListNode[] lists) {

		int end = 0;
		ListNode[] tempLists = new ListNode[lists.length];

		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				tempLists[end] = lists[i];					// temporarily store the non-null lists in tempLists
				end++;
			}
		}

		if (end == 0) {
			return null;
		} else {
			lists = tempLists;
			end--;
		}

		ListNode head = new ListNode(0);
		ListNode node = head;

		buildMinRootHeap(lists, end);						// build up min root heap
		while (end > 0) {									
			node.val = lists[0].val;						// pop the root of heap, which will be merged into final merged list
			if (lists[0].next != null) {					
				lists[0] = lists[0].next;
			} else {
				swap(lists, 0, end);
				end--;
			}
			minRootHeapify(lists, 0, end);
			ListNode newNode = new ListNode(0);
			node.next = newNode;
			node = node.next;
		}

		while (lists[0] != null) {							// merge the last non-null list
			node.val = lists[0].val;
			if (lists[0].next != null) {
				lists[0] = lists[0].next;
				ListNode nextNode = new ListNode(0);
				node.next = nextNode;
				node = node.next;
			} else {
				break;
			}
		}

		return head;
	}

	private static void buildMinRootHeap(ListNode[] lists, int end) {

		for (int i = (end - 1) >> 1; i >= 0; i--) {
			minRootHeapify(lists, i, end);
		}
	}

	private static void minRootHeapify(ListNode[] lists, int node, int end) {

		int leftNode = (node << 1) + 1;
		int rightNode = (node << 1) + 2;
		int minNode = node;

		if (leftNode <= end && lists[leftNode].val < lists[minNode].val) {
			minNode = leftNode;
		}
		if (rightNode <= end && lists[rightNode].val < lists[minNode].val) {
			minNode = rightNode;
		}
		if (minNode != node) {
			swap(lists, node, minNode);
			minRootHeapify(lists, minNode, end);
		}
	}

	private static void swap(ListNode[] lists, int indexA, int indexB) {

		ListNode temp = lists[indexA];
		lists[indexA] = lists[indexB];
		lists[indexB] = temp;
	}
}
