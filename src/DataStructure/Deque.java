package DataStructure;

import com.company.Solution;

import java.util.ArrayDeque;
import java.util.List;

public class Deque {
    ListNode head;
    ListNode tail;
    private int size;

    public static void main(String[] args) {
        Deque deque = new Deque();
        deque.offerFirst(1);
        deque.offerLast(2);
        // 1   2

        System.out.println(deque.size());       // 2

        System.out.println(deque.pollFirst());  // 1
        System.out.println(deque.pollLast());   // 2

        System.out.println(deque.pollFirst());  // null
        System.out.println(deque.pollLast());   // null
    }

    public void offerFirst(int val) {
        size++;
        ListNode node = new ListNode(val);
        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
        }
        head = node;
    }

    public void offerLast(int val) {
        size++;
        ListNode node = new ListNode(val);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.pre = tail;
        }
        tail = node;
    }

    public Integer peekFirst() {
        return head == null ? null : head.val;
    }

    public Integer peekLast() {
        return tail == null ? null : tail.val;
    }

    public Integer pollFirst() {
        if (head == null) {
            return null;
        } else if (head == tail) {
            int val = head.val;
            head = null;
            tail = null;
            size--;
            return val;
        } else {
            int val = head.val;
            head = head.next;
            size--;
            return val;
        }
    }

    public Integer pollLast() {
        if (tail == null) {
            return null;
        } else if (head == tail) {
            int val = tail.val;
            head = null;
            tail = null;
            size--;
            return val;
        } else {
            int val = tail.val;
            tail = tail.pre;
            size--;
            return val;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class ListNode {
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
