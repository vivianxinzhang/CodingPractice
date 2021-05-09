package DataStructure;

public class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList s = new MyLinkedList();
        System.out.println();
        // input:
        // linkedList.addAtHead(1),
        // linkedList.addAtTail(3),
        // linkedList.addAtIndex(1, 2),
        // linkedList.get(1),
        // linkedList.deleteAtIndex(1),
        // linkedList.get(1)
        s.addAtHead(1);
        s.addAtTail(3);
        s.addAtIndex(1, 2);
        System.out.println(s.get(1));
        // output: 2
        s.deleteAtIndex(1);
        System.out.println(s.get(1));
        // output: 3
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    ListNode head;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
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

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        size++;
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        size++;
        if (head == null) {
            head = new ListNode(val);
        } else {
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new ListNode(val);
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
        } else if (index == size) {
            addAtTail(val);
        } else {
            // don't forget size++
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

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        // only size-- if it is valid deletion index
        size--;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
        head = dummy.next;
    }
}
