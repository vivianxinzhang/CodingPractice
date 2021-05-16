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
        // 1 -> 5 -> 2 -> 4 -> 3
        Printer.printLinkedList(curr);
    }

    // 1 -> 2               1 -> 2
    // 1 -> 2 -> 3          1 -> 3 -> 2
    // 1 -> 2 -> 3 ->4      1 -> 4 -> 2 -> 3
    // Step 1: find middle
    // Step 2: reverse right part
    // Step 3: merge together
    // Time O(n)
    // Space O(1)
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1. find the middle node
        ListNode mid = findMiddle(head);
        ListNode one = head;
        ListNode two = mid.next;
        // de-link the second half from the list
        mid.next = null;
        // 2. reverse the second half
        ListNode reverseTwo = reverse(two);
        // 3. merge the two halves
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
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (left != null) {
            tail.next = left;
            left = left.next;
            tail = tail.next;
            if (right != null) {
                tail.next = right;
                right = right.next;
                tail = tail.next;
            }
        }
        return dummy.next;
    }
}
