package com.company;
import java.util.*;

public class DeepCopyLinkedListWithRandomPointer {
    public static void main(String[] args) {
        DeepCopyLinkedListWithRandomPointer s = new DeepCopyLinkedListWithRandomPointer();
        RandomListNode one = new RandomListNode(1);
        RandomListNode two = new RandomListNode(2);
        RandomListNode three = new RandomListNode(3);
        one.next = two;
        two.next = three;
        one.random = one;
        two.random = two;
        three.random = three;

        print(one);
        System.out.println("Copy");
        print(s.copy(one));
        System.out.println();

        one.random = three;
        three.random = two;
        two.random = one;
        print(one);
        System.out.println("Copy");
        print(s.copy(one));
    }

    private static void print(RandomListNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            if (head.random != null) {
                System.out.println("Random node: " + head.random.value);
            }
            head = head.next;
        }
    }

    // Method 1: using HashMap to avoid copy multiple times for the same node
    // Time O(n)
    // Space O(n)
    public RandomListNode copy(RandomListNode head) {
        // Write your solution here.
        if (head == null) {
            return null;
        }
        // Sentinel node to help construct the deep copy.
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        // Maintains the mapping between the node in the original list and
        // the corresponding node in the new list.
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (head != null) {
            // Copy the current node if necessary.
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.value));
            }
            // Connect the copied node to the deep copy list.
            cur.next = map.get(head);
            // Copy the random node if necessary.
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new RandomListNode(head.random.value));
                }
                // Connect the copied node to the random pointer
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    // Time O(n)
    // Space O(n)
    public RandomListNode copyI(RandomListNode head) {
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