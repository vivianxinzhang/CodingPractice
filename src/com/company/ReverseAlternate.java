package com.company;

public class ReverseAlternate {
    public static void main(String[] args) {
        ReverseAlternate s = new ReverseAlternate();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        ListNode curr = s.reverseAlternate(one);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

    public ListNode reverseAlternate(ListNode head) {
        // Write your solution here
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
        evenTail.next = null;
        ListNode evenHead = reverse(evenDummy.next);
        oddTail.next = evenHead;
        return oddDummy.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
