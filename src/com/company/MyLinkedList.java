package com.company;

public class MyLinkedList {
    private class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public ListNode head;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = null;
        size = 0;
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
        ListNode newHead = new ListNode(val);
        newHead.next = head;
        head = newHead;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        size++;
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
        } else {
            ListNode pre = head;
            while (pre.next != null) {
                pre = pre.next;
            }
            pre.next = node;
        }
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
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
            ListNode node = new ListNode(val);
            node.next = pre.next;
            pre.next = node;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        size--;
        if (index == 0) {
            head = head.next;
        }
        ListNode pre = head;
        for (int i = 0; i < index - 1; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;

    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
