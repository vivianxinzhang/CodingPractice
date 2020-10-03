package com.company;

public class QuickSortLinkedList {
    public static void main(String[] args) {
        QuickSortLinkedList s = new QuickSortLinkedList();
        ListNode zero = null;
        ListNode one = new ListNode(4);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(2);
        ListNode three2 = new ListNode(1);
        ListNode one2 = new ListNode(1);
        one.next = two;
        two.next = three;
        three.next = three2;
//        three2.next = one2;
        ListNode curr = one;
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
        System.out.println();
        curr = s.quickSort(one);
        while (curr != null) {
            System.out.print(curr.value);
            curr = curr.next;
        }
    }

    // Time O(nlogn)
    // Space O(n)
    public ListNode quickSort(ListNode head) {
        // Write your solution here
        if (head == null || head.next == null) {
            return head;
        }
        ListNode smallDummy = new ListNode(Integer.MIN_VALUE);
        ListNode largeDummy = new ListNode(Integer.MIN_VALUE);
        ListNode smallTail = smallDummy;
        ListNode largeTail = largeDummy;
        ListNode pivotNode = head;
        ListNode curr = head.next;
        head.next = null;
        while (curr != null) {
            if (curr.value < pivotNode.value) {
                smallTail.next = curr;
                smallTail = smallTail.next;
            } else {
                largeTail.next = curr;
                largeTail = largeTail.next;
            }
            curr = curr.next;
        }
        smallTail.next = null;
        largeTail.next = null;
        smallDummy.next = quickSort(smallDummy.next);
        largeDummy.next = quickSort(largeDummy.next);
        smallTail = findTail(smallDummy);
        smallTail.next = pivotNode;
        pivotNode.next = largeDummy.next;
        return smallDummy.next;
    }

    private ListNode findTail(ListNode head) {
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}
