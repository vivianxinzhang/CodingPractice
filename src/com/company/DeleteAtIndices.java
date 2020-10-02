package com.company;

public class DeleteAtIndices {
    public static void main(String[] args) {
        DeleteAtIndices s = new DeleteAtIndices();
        ListNode zero = null;
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode curr = s.deleteNodes(one, new int[] {0, 10});
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // Time O(n)
    // Space O(1)
    public ListNode deleteNodes(ListNode head, int[] indices) {
        // Write your solution here
        if (head == null || indices == null || indices.length == 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int i = 0, j = 0;
        while (prev.next != null && j < indices.length) {
            if (i == indices[j]) {
                prev.next = prev.next.next;
                j++;
            } else {
                prev = prev.next;
            }
            i++;
        }
        return dummy.next;
    }
}
