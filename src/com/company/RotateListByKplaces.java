package com.company;

import java.util.ArrayDeque;
import java.util.Deque;

public class RotateListByKplaces {
    public static void main(String[] args) {
        RotateListByKplaces s = new RotateListByKplaces();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;
        ListNode curr = s.rotateKplace(one, 4);
        while (curr != null) {
            System.out.println(curr.value);
            curr = curr.next;
        }
    }
    // Time O(n)
    // Space O(1)
    // Step 1: find length
    // Step 2: move n %= length position
    // Step 3: find n - length, delink and relink
    public ListNode rotateKplace(ListNode head, int n) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        n %= length;
        if (n == 0) {
            return head;
        }
        int count = length - n;
        curr = head;
        for (int i = 0; i < count - 1; i++) {
            curr = curr.next;
        }
        ListNode newHead = curr.next;
        curr.next = null;
        ListNode tail = newHead;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;
        return newHead;
    }
}

