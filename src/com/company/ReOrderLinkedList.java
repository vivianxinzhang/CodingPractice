package com.company;

public class ReOrderLinkedList {
    public static void main(String[] args) {
        ReOrderLinkedList s = new ReOrderLinkedList();
        ListNode head = null;
        ListNode curr = s.reorder(head);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }

        // 1 -> 2 -> 3  -> 4 -> 5
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        curr = s.reorder(one);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // Time O(n)
    // Space O(1)
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode one = head, two = middle.next;
        middle.next = null;
        ListNode reverseTwo = reverse(two);
        return merge(one, reverseTwo);
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
//        if (head == null || head.next == null) {
//            return head;
//        }
        ListNode curr = head, prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode curr1 = one, curr2 = two;
        while (curr1 != null && curr2 != null) {
            curr.next = curr1;
            curr1 = curr1.next;
            curr = curr.next;
            curr.next = curr2;
            curr2 = curr2.next;
            curr = curr.next;
        }
        if (curr1 != null) {
            curr.next = curr1;
        }
        if (curr2 != null) {
            curr.next = curr2;
        }
        return dummy.next;
    }
}
