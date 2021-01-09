package com.company;

public class CheckIfLinkedListIsPalindrome {
    public static void main(String[] args) {
        CheckIfLinkedListIsPalindrome s = new CheckIfLinkedListIsPalindrome();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode two2 = new ListNode(2);
        ListNode one2 = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = two2;
//        two2.next = one2;
        System.out.println(s.isPalindrome(one));

    }

    public boolean isPalindrome(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return true;
        }
        ListNode middle = findMiddle(head);
        ListNode right= reverse(middle.next);
        while (right != null) {
            if (head.value != right.value) {
                return false;
            }
            head = head.next;
            right = right.next;
        }
        return true;
    }

    public boolean isPalindromeI(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return true;
        }
        ListNode middle = findMiddle(head);
        ListNode leftHalf = head;
        ListNode rightHalf = middle.next;
        middle.next = null;
        ListNode reverseRight = reverse(rightHalf);
        return isPalindrome(leftHalf, reverseRight);
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private boolean isPalindrome(ListNode one, ListNode two) {
        while (one != null && two != null) {
            if (one.value != two.value) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return true;
    }
}
