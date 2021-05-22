package com.company;

public class MyLinkedListDoubly {
    public static void main(String[] args) {
        MyLinkedListDoubly list = new MyLinkedListDoubly();
        list.addAtHead(1);
        // 1
        list.addAtTail(3);
        // 1 -> 3
        list.addAtIndex(1, 2);
        // 1 -> 2 - > 3
        System.out.println(list.get(1));    // 2
        list.deleteAtIndex(1);
        // 1 - > 3
        System.out.println(list.get(1));    // 3
    }

    private class ListNode {
        public int val;
        public ListNode pre;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode head;
    public ListNode tail;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedListDoubly() {
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        size++;
        if (head == null) {
            head = new ListNode(val);
            tail = head;
        } else {
            ListNode newHead = new ListNode(val);
            newHead.next = head;
            head.pre = newHead;
            head = newHead;
        }
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        size++;
        ListNode newTail = new ListNode(val);
        tail.next = newTail;
        newTail.pre = tail;
        tail = newTail;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            // throw new IllegalArgumentException();
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            size++;
            ListNode pre = head;
            for (int i = 0; i < index - 1; i++) {
                pre = pre.next;
            }
            ListNode next = pre.next;
            ListNode node = new ListNode(val);
            pre.next = node;
            node.next = next;
            next.pre = node;
            node.pre = pre;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            // throw new IllegalArgumentException();
            return;
        }
        size--;
        if (index == 0) {
            head = head.next;
            head.pre = null;
        }
        ListNode pre = head;
        for (int i = 0; i < index - 1; i++) {
            pre = pre.next;
        }
        ListNode next = pre.next.next;
        pre.next = next;
        next.pre = pre;
    }
}
