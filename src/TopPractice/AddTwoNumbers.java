package TopPractice;
import java.util.Arrays;

public class AddTwoNumbers {
    /** You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * */

    // Assumptions: the two numbers do not contain any leading zero, except the number 0 itself.
    // return indices of the sum pair
    // Time O(n)
    // Space O(n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int num = 0;
        while (l1 != null || l2 != null) {
            num *= 10;
            if (l1 != null) {
                num += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num += l2.val;
                l2 = l2.next;
            }
            tail.next = new ListNode(num % 10);
            tail = tail.next;
            num /= 10;
        }
        if (num != 0) {
            tail.next = new ListNode(num);
        }
        return dummy.next;
    }
}
