package wzm;

public class AddTwoNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ListNode l1 = new ListNode(0);
		ListNode l2 = new ListNode(1);
		ListNode l3 = new ListNode(8);
		l2.next = l3;
		
		ListNode l = addTwoNumbers(l1, l2);
		while (l != null) {
			System.out.println(l.val);
			l = l.next;
		}
		
	}

	/**
	 * Merge two linked list together.
	 * Time complexity is O(max(l1.length, l2.length)).
	 * @param l1
	 * @param l2
	 * @return
	 */
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
		ListNode headNode = new ListNode(0);
		ListNode currentNode = headNode;
		boolean carry = false;
		
		while (l1 != null || l2 != null) {
			int value1 = 0;
			int value2 = 0;
			if (l1 != null) {
				value1 = l1.val;
			}
			if (l2 != null) {
				value2 = l2.val;
			}
			currentNode.val = value1 + value2;
			if (carry) {											// if carry is true, current value++
				currentNode.val++;
				carry = false;										// reset carry to false
			}
			if (currentNode.val >= 10) {							// if current value >= 10
				carry = true;										// set carry to true
				currentNode.val = currentNode.val - 10;				// current value -= 10
			}
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
			if (l1 != null || l2 != null) {							
				ListNode nextNode = new ListNode(0);				// create a new node
				currentNode.next = nextNode;
				currentNode = nextNode;
			}
		}
		if (carry) {
			ListNode tailNode = new ListNode(1);					// last carry check, if carry is true, create a new node with value 1
			currentNode.next = tailNode;							// link this new node to the result linked list
		}
		
		return headNode;
	}
	
}
