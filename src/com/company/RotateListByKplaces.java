package com.company;
import java.util.*;

public class RotateListByKplaces {
    public static void main(String[] args) {
        RotateListByKplaces s = new RotateListByKplaces();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode curr = s.rotateKplace(one, 2);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
        System.out.println();
        curr = s.rotateKplaceI(one, 6);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

    // slow fast pointer
    public ListNode rotateKplace(ListNode head, int k) {
        // Write your solution here
        if (head == null || head.next == null || k < 1) {
            return head;
        }
        int length = getLength(head);
        k %= length;
        if (k == 0) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
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


    // Time O(n)
    // Space O(1)
    // Step 1: find length
    // Step 2: move n %= length position
    // Step 3: find n - length, delink and relink
    public ListNode rotateKplaceII(ListNode head, int k) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, tail = null;
        int length = 0;
        while (curr != null) {
            length++;
            tail = curr;
            curr = curr.next;

        }
        k %= length;
        if (k == 0) {
            return head;
        }
        int count = length - k;
        ListNode newTail = null, newHead = head;
        for (int i = 0; i < count; i++) {
            newTail = newHead;
            newHead = newHead.next;
        }
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

    public ListNode rotateKplaceI(ListNode head, int k) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, tail = null;
        int length = 0;
        while (curr != null) {
            length++;
            tail = curr;
            curr = curr.next;

        }
        k %= length;
        if (k == 0) {
            return head;
        }
        int count = length - k;
        ListNode newTail = null, newHead = head;
        for (int i = 0; i < k; i++) {
            newTail = newHead;
            newHead = newHead.next;
        }
        newTail.next = null;
        tail.next = head;
        return newHead;
    }

}

