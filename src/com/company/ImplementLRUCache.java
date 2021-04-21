package com.company;
import java.util.*;

public class ImplementLRUCache<K, V> {
    public static void main(String[] args) {
        ImplementLRUCache<Integer, Integer> s = new ImplementLRUCache<>(2);
        System.out.println(s.get(1)); // null
        s.set(1, 1);      // 1: 1
        s.set(2, 2);      // 2: 2,  1: 1

        System.out.println(s.get(2)); // 2
        System.out.println(s.get(1)); // 1

        s.set(3, 3);      // 3: 3,  1: 1

        System.out.println(s.get(1)); // 1
        System.out.println(s.get(2)); // null
        System.out.println(s.get(3)); // 3
    }

    // each node contains the key, value pair,
    // and it is also a double linked list node.
    private class Node<K, V> {
        Node<K, V> next;
        Node<K, V> pre;
        K key;
        V value;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    // make it final for the pre-defined size limit of the cache.
    private final int limit;
    // record all the time the head and tail of the double linked list.
    private Node<K, V> head;
    private Node<K, V> tail;    // tail used when limit hit and need to remove old elements
    // maintains the relationship of key and its corresponding node
    // in the double linked list.
    private Map<K, Node<K, V>> map;

    public ImplementLRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<>();
    }

    // Time: O(1)
    public void set(K key, V value) {
        // find and update, or create
        // 1. if the key already in the cache, we need to update its value
        // and move it to head (most recent position).
        // 2. if the key is not in the cache and we still have space,
        // we can append a new node to head.
        // 3. if the key is not in the cache and we don't have space,
        // we need to evict the tail and reuse the node let it maintain
        // the new key, value and put it to head.
        Node<K, V> node = updateOrCreate(key, value);
        // check size
        if (map.size() == limit) {
            removeNode(tail);
        }
        // append at head
        insertAtHead(node);
    }

    // Time: O(1)
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        // even it is a read operation, it is still a write operation to
        // the double linked list, and we need to move the node to head.
        removeNode(node);
        insertAtHead(node);
        return node.value;
    }


    private Node<K,V> updateOrCreate(K key, V value) {
        Node node = map.get(key);
        if (node == null) {
            return new Node<>(key, value);
        }
        node.update(key, value);
        removeNode(node);
        return node;
    }

    // Time: O(1)
    private void insertAtHead(Node<K,V> node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
            return;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }

    // Time: O(1)
    private Node<K, V> removeNode(Node<K,V> node) {
        map.remove(node.key);   // not map.remove(node)
        // 前面指向后面
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        // 后面指向前面
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // node是头，下一个变成头
        if (node == head) {
            head = node.next;
        }
        // node是尾，前一个变成尾
        if (node == tail) {
            tail = node.pre;
        }
        // de-link node
        node.pre = node.next = null;
        return node;
    }
}
