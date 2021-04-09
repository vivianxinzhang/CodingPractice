package com.company;

public class CycleNodeInLinkedList {
    public static void main(String[] args) {
        CycleNodeInLinkedList s = new CycleNodeInLinkedList();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);
        ListNode nine = new ListNode(9);
        ListNode ten = new ListNode(10);


        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = five;

        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        //                      <--------
        System.out.println();
        ListNode result = s.cycleNode(five);
        int key = result == null ? 0 : result.value;
        System.out.print(key);  //
    }

    // Proof:
    // Cycle is big:
    //               slow move a + b, fast move a + b + c + b
    //               => a + b + c + b = 2 * (a + b)
    //               => c + b = a + b
    //               c = a
    // Cycle is very small:
    //               slow move a + b, fast move a + b + k(c + b)
    //               => a + b + k(c + b) = 2 * (a + b)
    //               => K(c + b) = a + b
    //               thus a = k(c + b) - b = kc + kb - b = (k - 1 + 1)c + (k - 1)b = (k - 1)(c + b) + c
    // Time O(n)
    // Space O(1)
    public ListNode cycleNode(ListNode head) {
        // write your solution here
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            System.out.println(fast.value);
            if (slow == fast) {
                return findcycleNode(head, slow);
            }
        }
        return null;
    }

    private ListNode findcycleNode(ListNode head, ListNode slow) {
        while (head != slow) {
            // System.out.println(slow.value);
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
