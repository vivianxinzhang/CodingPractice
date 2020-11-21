package com.company;
import java.util.List;

public class DeepCopyLinkedListWithRandomPointer {
    public static void main(String[] args) {
        DeepCopyLinkedListWithRandomPointer s = new DeepCopyLinkedListWithRandomPointer();
        RandomListNode head = new RandomListNode(0);
        RandomListNode one = new RandomListNode(1);
        RandomListNode two = new RandomListNode(2);
        RandomListNode three = new RandomListNode(3);
        head.next = one;
        one.next = two;
        two.next = three;
        System.out.println(head);
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        RandomListNode copyHead = s.copy(head);
        System.out.println(copyHead);
        while (copyHead != null) {
            System.out.println(copyHead.value);
            copyHead = copyHead.next;
        }
    }

    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        // First pass, for each node in the original list, insert a
        // copied node between the node and node.next
        RandomListNode cur = head;
        while (cur != null) {
            RandomListNode copy = new RandomListNode(cur.value);
            copy.next = copy.next;
            cur.next = copy;
            cur = cur.next.next;
        }
        // Second pass, link the random pointer for the copied node
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // Third pass, extract the copied node
        cur = head;
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode copyPrev = dummy;
        while (cur != null) {
            copyPrev.next = cur.next;
            cur.next = cur.next.next;
            copyPrev = copyPrev.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;

    public RandomListNode(int value) {
        this.value = value;
    }
}