package com.company;
import java.util.*;

import java.util.HashMap;

public class FirstNonRepeatingCharacterInStreamI {
    static class Node {
        Character key;
        Node prev;
        Node next;

        Node(Character key) {
            this.key = key;
        }
    }

    // Doubly linked list. Keeping all non-repeating characters in order.
    Node dummy;
    // linked all characters with their corresponding nodes. Set it's value to null
    // when the char has duplicates
    Map<Character, Node> map = new HashMap<>();

    public FirstNonRepeatingCharacterInStreamI() {
        this.dummy = new Node(null);
        this.dummy.prev = dummy;
        this.dummy.next = dummy;
    }

    public void read(char ch) {
        if (map.containsKey(ch)) {
            Node cur = map.get(ch);
            if (cur != null) {
                map.put(ch, null);
                remove(cur);
            }
        } else {
            Node cur = new Node(ch);
            map.put(ch, cur);
            insertEnd(cur);
        }
    }

    private void remove(Node cur) {
        Node prev = cur.prev;
        Node next = cur.next;
        prev.next = next;
        next.prev = prev;
        cur.prev = cur.next = null;
    }

    private void insertEnd(Node cur) {
        Node prev = this.dummy.prev;
        prev.next = cur;
        cur.prev = prev;
        cur.next = this.dummy;
        this.dummy.prev = cur;
    }

    public Character firstNonRepeating() {
        return dummy.next.key;
    }
}