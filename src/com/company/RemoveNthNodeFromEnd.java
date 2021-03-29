package com.company;

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        RemoveNthNodeFromEnd s = new RemoveNthNodeFromEnd();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode curr = s.removeNthFromEnd(one, 5);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (n > 0 && head != null) {
            head = head.next;
            n--;
        }
        // if n > 0 here, the total number of nodes is smaller than n
        if (n == 0) {
            ListNode prev = dummy;
            while (head != null) {
                prev = prev.next;
                head = head.next;
            }
            // when head == null, prev.next is the node to remove
            prev.next = prev.next.next;
        }
        return dummy.next;
    }

    public ListNode removeNthFromEndI(ListNode head, int n) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return dummy.next;
            }
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
