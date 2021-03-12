package com.company;
import java.util.*;

/*
    Two level           index               hashmap
                    --------------        --------------     LinkedHashMap
                        storage             linked list

    Index layer 查询这个字母有没有出现过
    Storage layer 按出现的顺序存储
    In order to answer the question, you need to store those chars you have read.
    Storage layer: Store those non-repeating chars by the reading order.
    array: [a, b, c, d] or linked list
    read a, lookup in the storage to see if it appears or not
    This should be taken care of by the index layer: HashMap.
* */
public class FirstNonRepeatingCharacterInStream {
    public static void main(String[] args) {
        FirstNonRepeatingCharacterInStream s = new FirstNonRepeatingCharacterInStream();
        System.out.println();
    }

    static class Node {
        Character ch;
        Node prev;
        Node next;

        Node(Character ch) {
            this.ch = ch;
        }
    }

    // record the head and tail of the list all the time
    // only the characters appearing just once will be in the double linked list
    private Node dummyHead;
    private Node tail;
    // for any character, it could be in three state:
    // 1. not existed yet, it will not be in singled Map or repeated Set
    // 2. only exists once, it will be in singled Map and there will be a corresponding node in the list
    // 3. exists more than once, it will be in the repeated Set and it will be removed from the list
    private HashMap<Character, Node> singled;   // Non-Repeating Chars Candidates
    private HashSet<Character> repeated;    // Repeated Chars

    public FirstNonRepeatingCharacterInStream() {
        // Initialize the class.
        // an example of using sentinel node to eliminate some corner cases
        tail = new Node(null);
        tail.next = tail.prev = tail;
        dummyHead = tail;
        singled = new HashMap<Character, Node>();
        repeated = new HashSet<Character>();
    }

    public void read(char ch) {
        // Implement this method here.
        // if the character already exists more than once, we just ignore
        if (repeated.contains(ch)) {
            return;
        }
        Node node = singled.get(ch);
        if (node == null) {
            // if the character appears for the first time, it should be added to the list
            // and added to the nonRepeated
            node = new Node(ch);
            append(node);
        } else {
            // if the character is already in the nonRepeated Map, we should remove it from
            // the Map and list, and put it into the repeated Set
            remove(node);
        }
    }

    private void append(Node node) {
        // step 1: update hashmap
        singled.put(node.ch, node);
        // step 2: append node at tail of the linked list
        tail.next = node;
        node.prev = tail;
        node.next = dummyHead;
        tail = tail.next;
    }

    private void remove(Node node) {
        // use sentinel node so that some of the corner cases will be eliminated
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //
        if (node == tail) {
            tail = node.prev;
        }
        //
        node.prev = node.next = null;
        repeated.add(node.ch);
        singled.remove(node.ch);
    }

    public Character firstNonRepeating() {
        // Implement this method here.
        // when head == tail, it means there is only the sentinel node in the list
        if (dummyHead == tail) {
            return null;
        }
        return dummyHead.next.ch;
    }
}
