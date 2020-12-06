package com.company;

public class RemoveLinkedListElements {
    public static void main(String[] args) {
        RemoveLinkedListElements s = new RemoveLinkedListElements();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode one2 = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = one2;
        ListNode curr = s.removeElements(one, 1);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

    // Method 1:
    // Time O(n)
    // Space O(1)
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        // head is the element we are processing
        // when head == null, we finished traverse the linked list
        while (head != null) {
            if (head.value == val) {
                // find target val, prev points to head.next, skip head
                pre.next = head.next;
            } else {
                // not find target val, prev move one step
                pre = head;
            }
            head = head.next;
        }
        return dummy.next;
    }

    // Method 2:
    // Time O(n)
    // Space O(1)
    public ListNode removeElementsI(ListNode head, int val) {
        ListNode prev = null;
        ListNode newHead = null;
        while (head != null) {
            if (head.value == val) {
                if (prev != null) {
                    prev.next = head.next;
                }
            } else {
                if (newHead == null) {
                    newHead = head;
                }
                prev = head;
            }
            head = head.next;
        }
        return newHead;
    }
}
