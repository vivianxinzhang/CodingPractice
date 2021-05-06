package com.company;

import java.util.List;

public class ReverseAlternateNodes {
    public static void main(String[] args) {
        ReverseAlternateNodes s = new ReverseAlternateNodes();
        // Input List:  1 -> 2 -> 3 -> 4 -> 5 -> 6
        //              1    ->   3    ->   5
        //                   2   ->    4 -  ->   6
        // Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2

        // Input List:  1 -> 2 -> 3 -> 4 -> 5
        //              1    ->   3    ->   5
        //                   2   ->    4
        // Output List: 1 -> 3 -> 5 -> 4 -> 2
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
        ListNode res = s.reverseAlternate(one);
        Printer.printLinkedList(res);
    }

    // Time O(n)
    // Space O(1)
    public ListNode reverseAlternate(ListNode head) {
        ListNode oddDummy = new ListNode(0);
        ListNode evenDummy = new ListNode(0);
        ListNode oddTail = oddDummy;
        ListNode evenTail = evenDummy;
        while (head != null) {
            oddTail.next = head;
            oddTail = oddTail.next;
            head = head.next;
            if (head != null) {
                evenTail.next = head;
                evenTail = evenTail.next;
                head = head.next;
            }
        }
        oddTail.next = null;
        evenTail.next = null;
        ListNode reverseEven = reverse(evenDummy.next);
        oddTail.next = reverseEven;
        return oddDummy.next;
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
}
