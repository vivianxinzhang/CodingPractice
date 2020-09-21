package com.company;
import java.util.ArrayDeque;
import java.util.Deque;

public class InsertAtIndex {
    public static void main(String[] args) {
        InsertAtIndex s = new InsertAtIndex();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode curr = s.insert(one, 110, 4);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }

    // Time O(n)
    // Space O(1)
    public ListNode insert(ListNode head, int index, int value) {
        // Write your solution here
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int i = 0;
        while (prev != null) {
            if (i == index) {
                ListNode newNode = new ListNode(value);
                newNode.next = prev.next;
                prev.next = newNode;
                return dummy.next;
            }
            prev = prev.next;
            i++;
        }
        return head;
    }
}
