package com.company;

public class InsertNodeToCircularLinkedList {
    public static void main(String[] args) {
        InsertNodeToCircularLinkedList s = new InsertNodeToCircularLinkedList();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = one;
        Printer.printCircularLinkedList(one);
        // Input List:  1 -> 2 -> 3 -> 4 -> 5 -> (1)
        //              |_________________________|
        // Output List: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> (1)
        //              |______________________________|
        ListNode head = s.insertCircularList(one, 6);
        System.out.println();
        Printer.printCircularLinkedList(head);
        System.out.println();

        ListNode four1 = new ListNode(4);
        ListNode five1 = new ListNode(5);
        ListNode one1 = new ListNode(1);
        ListNode two1 = new ListNode(2);
        four1.next = five1;
        five1.next = one1;
        one1.next = two1;
        two1.next = four1;
        Printer.printCircularLinkedList(four1);
        System.out.println();
        // Input List:  4 -> 5 -> 1 -> 2
        //              |______________|
        // Output List: 4 -> 5 -> 6 -> 1 -> 2
        //              |___________________|

        head = s.insertCircularList(four1, 6);
        Printer.printCircularLinkedList(head);
        System.out.println();

        ListNode one2 = new ListNode(1);
        ListNode one3 = new ListNode(1);
        ListNode two2 = new ListNode(2);
        ListNode two3 = new ListNode(2);
        one2.next = one3;
        one3.next = two2;
        two2.next = two3;
        two3.next = one2;
        Printer.printCircularLinkedList(one2);
        System.out.println();
        head = s.insertCircularList(one2, 0);
        Printer.printCircularLinkedList(head);
    }

    // Corner case:
    // input empty:
    // only 1 node in input:
    // Observation: last maxNode points to first minNode
    // Step 1: find last maxNode and first minNode
    // Step 2: consider different insert cases
    //   Case 2.1: insertVal <= minNode, then create new node after maxNode
    //   Case 2.2: insertVal >= maxNode, create new node after maxNode
    //   Case 2.3: loop around with two pointers and check where to insert the new Node
    // Time O(n)
    // Space O(1)
    public ListNode insertCircularList(ListNode head, int newVal) {
        if (head == null) {
            ListNode newHead = new ListNode(newVal);
            newHead.next = newHead;
            return newHead;
        }
        if (head.next == head) {
            ListNode newNode = new ListNode(newVal);
            head.next = newNode;
            newNode.next = head;
            return head;
        }
        // we have 2 or more nodes
        ListNode maxNode = findLastMaxInCircularLinkedList(head);
        ListNode minNode = maxNode.next;
        if (newVal <= minNode.value || newVal >= maxNode.value) {
            ListNode newNode = new ListNode(newVal);
            maxNode.next = newNode;
            newNode.next = minNode;
            return head;
        }
        ListNode pre = minNode;
        while (pre.next.value < newVal) {
            pre = pre.next;
        }
        ListNode newNode = new ListNode(newVal);
        newNode.next = pre.next;
        pre.next = newNode;
        return head;
    }

    // [cycle(1,1,2,2)...]] newVal: 0
    // Expected: [cycle(1,1,2,2,0)...]
    private ListNode findLastMaxInCircularLinkedList(ListNode head) {
        ListNode maxNode = head;
        ListNode cur = head.next;
        while (cur != head) {
            if (cur.value >= maxNode.value) {
                maxNode = cur;
            }
            cur = cur.next;
        }
        return maxNode;
    }
}
