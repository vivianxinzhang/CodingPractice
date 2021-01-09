package TopPractice;
import com.company.CheckIfLinkedListIsPalindrome;

import java.util.List;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        PalindromeLinkedList s = new PalindromeLinkedList();
        ListNode one = new ListNode(1);
        ListNode one2 = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode one3 = new ListNode(1);
        one.next = one2;
        one2.next = two;
        two.next = one3;
        System.out.println(s.isPalindrome(one));

    }

    // Step 1: find middle, middle node for odd # of nodes, last node of first half for even # of nodes
    // Step 2: reverse second part
    // Step 3: check if same length part are the same
    // Time O(n)
    // Space O(1)
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode middle = findMiddle(head);
        ListNode right = reverse(middle.next);
        return palindromeHelper(head, right);
    }

    private boolean palindromeHelper(ListNode one, ListNode two) {
        while (one != null && two != null) {
            if (one.val != two.val) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
