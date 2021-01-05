package DailyChallenge;
import com.company.ListNode;

public class RemoveDuplicatesFromSortedListII {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII s = new RemoveDuplicatesFromSortedListII();
        ListNode one = new ListNode(1);
        ListNode one1 = new ListNode(1);
        one.next = one1;
        ListNode head = s.deleteDuplicates(one);
    }

    /**Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list. Return the linked list sorted as well.
     * 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5       1 -> 2 -> 5
     * 1 -> 1 -> 1 -> 2 -> 3                 2 -> 3
     * */
    // Time O(n)
    // Space O(1)
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        prev.next = head;
        while(head != null) {
            if (head.next != null && head.next.value == head.value) {
                while(head.next != null && head.next.value == head.value) {
                    head = head.next;
                }
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
