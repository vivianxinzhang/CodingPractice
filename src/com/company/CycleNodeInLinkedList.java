package com.company;

public class CycleNodeInLinkedList {
    public static void main(String[] args) {
        CycleNodeInLinkedList s = new CycleNodeInLinkedList();
        ListNode five = new ListNode(5);
        ListNode four = new ListNode(4);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode six = new ListNode(6);
        ListNode three = new ListNode(3);
        five.next = four;
        four.next = one;
        one.next = two;
        two.next = six;
        six.next = three;
        three.next = four;
        // 541263
        System.out.println();
        ListNode result = s.cycleNode(five);
        int key = result == null ? 0 : result.value;
        System.out.print(key);  // 4
    }

    // Time O(n)
    // Space O(1)
    public ListNode cycleNode(ListNode head) {
        // write your solution here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return findcycleNode(head, slow);
            }
        }
        return null;
    }

    private ListNode findcycleNode(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
