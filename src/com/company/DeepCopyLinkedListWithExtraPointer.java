package com.company;
import java.util.*;

public class DeepCopyLinkedListWithExtraPointer {
    public static void main(String[] args) {
        DeepCopyLinkedListWithExtraPointer s = new DeepCopyLinkedListWithExtraPointer();
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.next = two;
        two.next = three;

        System.out.println(one);
        Node head = one;
        while (head != null) {
            System.out.println(head + ": " +head.value);
            head = head.next;
        }
        Node copyHead = s.deepCopyLinkedListWithExtraPointer(one);
        System.out.println(copyHead);
        while (copyHead != null) {
            System.out.println(copyHead + ": " + copyHead.value);
            copyHead = copyHead.next;
        }
    }
    // Time O(n)
    // Space O(1)
    public Node deepCopyLinkedListWithExtraPointer(Node head) {
        if (head == null) {
            return null;
        }
        // lookup map to avoid duplicate.
        // Key: original node. Value: copied node.
        Map<Node, Node> lookup = new HashMap<>();
        Node newHead = new Node(head.value);
        lookup.put(head, newHead);
        Node curr = newHead;
        while (head != null) {
            if (head.next != null) {    // copy next
                if (!lookup.containsKey(head.next)) {
                    // Hasn't been copied over due to random pointer.
                    lookup.put(head.next, new Node((head.next.value)));
                }
                curr.next = lookup.get(head.next);
            }
            if (head.other != null) {   // copy other
                if (!lookup.containsKey(head.other)) {
                    // Hasn't been copied over previously
                    lookup.put(head.other, new Node(head.other.value));
                }
                curr.other = lookup.get(head.other);
            }
            head = head.next;
            curr = curr.next;
        }
        return newHead;
    }

}

class Node{
    int value;
    Node next;
    Node other;

    public Node(int value) {
        this.value = value;
    }
}
