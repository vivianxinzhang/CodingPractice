package com.company;
import java.util.*;

public class RotateListByKplaces {
    public static void main(String[] args) {
        RotateListByKplaces s = new RotateListByKplaces();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        Printer.printLinkedList(one);
        System.out.println();
        // Input List:  1 -> 2 -> 3 -> 4 -> 5
        // Output List: 4 -> 5 -> 1 -> 2 -> 3
        ListNode curr = s.rotateKplace(one, 2);
        Printer.printLinkedList(curr);
    }

    // slow fast pointer
    // Step 1: find length
    // Step 2: move k %= length position
    // Step 3: find divider position, delink and relink
    // Time O(n)
    // Space O(1)
    public ListNode rotateKplace(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        int length = getLength(head);
        k %= length;
        if (k == 0) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 1 -> 2 -> 3  ->  4 -> 5
        // s         f
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // 1 -> 2 -> 3  ->  4 -> 5
        //           s           f
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 1 -> 2 -> 3      4 -> 5
        // head      s   newHead f
        ListNode newHead = slow.next;
        slow.next = null;
        fast .next = head;
        return newHead;
    }

    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}

