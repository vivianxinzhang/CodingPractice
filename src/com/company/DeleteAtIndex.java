package com.company;

public class DeleteAtIndex {
    public static void main(String[] args) {
        DeleteAtIndex s = new DeleteAtIndex();
        ListNode zero = null;
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode curr = s.deleteNode(one, 2);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    public ListNode deleteNode(ListNode head, int index) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int i = 0;
        while (prev.next != null) {
            if (i == index) {
                prev.next = prev.next.next;
                return dummy.next;
            }
            prev = prev.next;
            i++;
        }
        return head;
    }
}
