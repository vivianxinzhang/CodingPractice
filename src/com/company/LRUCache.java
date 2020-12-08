package com.company;
import java.util.*;

/* Hybrid data structure design, a common pattern:
 Two level          index
                --------------
                    storage
 Index layer mainly focuses on allowing fast query over the stored data.
 Storage layer mainly focuses on storing data. It usually takes care of some special requirements for storing data.
 Index is built from storage.
 Requirement analysis:
 1. This cache should support lookup value by a given key. => HashMap seems like a good fit.
 2. When this cache is full and you want to add a new key value pair, then the oldest key value pair should be evicted.
 => this implies we should store key value pairs based on visit timestamp in ascending order.
 按用户 access 的时间顺序来进行存储
*/
public class LRUCache<K, V> {
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.set(0, "zero");
        cache.set(1, "first");
        cache.set(2, "second");
        cache.set(3, "third");
        cache.set(2, "newTwo");
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(0));
    }
    // each node contains the key, value pair,
    // and it is also a double linked list node.
    static class Node<K, V> {
        Node<K, V> next;
        Node<K, V> prev;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        void update(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // make it final for the pre-defined size limit of the cache.
    private final int limit;
    // record all the time the head and tail of the double linked list.
    private Node<K, V> head;
    private Node<K, V> tail;
    // maintains the relationship of key and its corresponding node
    // in the double linked list.
    private Map<K, Node<K, V>> map;

    public LRUCache(int limit) {
        this.limit = limit;
        this.map = new HashMap<K, Node<K, V>>();
    }

    // Time: O(1)
    // 看一下有没有
    // Case 1:有的话 先 update value 再换位置(remove + append)
    // Case 2: 没有的话
    //     2.1 没有满 还可以加 append at head
    //     2.2 满了不能加 先remove oldest(tail)再append new node at head
    public void set(K key, V value) {
        Node<K, V> node = null;
        // 1. if the key already in the cache, we need to update its value
        // and move it to head (most recent position).
        if (map.containsKey(key)) {
            node = map.get(key);
            // update node info
            node.value = value;
            remove(node);
        } else if (map.size() < limit) {
            // 2. if the key is not in the cache and we still have space,
            // we can append a new node to head.
            node = new Node<K, V>(key, value);
        } else {
            // 3. if the key is not in the cache and we don't have space,
            // we need to evict the tail and reuse the node let it maintain
            // the new key, value and put it to head.
            node = tail;
            remove(node);
            node.update(key, value);
        }
        append(node);
    }

    // Time: O(1)
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        // even it is a read operation, it is still a write operation to
        // the double linked list, and we need to move the node to head.
        remove(node);
        append(node);
        return node.value;
    }

    // Time: O(1)
    // remove the node from the double linked list
    private Node<K, V> remove(Node<K, V> node) {
        map.remove(node.key);
        // 前面指向后面
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        // 后面指向前面
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        // node是头，下一个变成头
        if (node == head) {
            head = head.next;
        }
        // node是尾，前一个变成尾
        if (node == tail) {
            tail = tail.prev;
        }
        node.next = node.prev = null;
        return node;
    }

    // Time: O(1)
    // append the last accessed node at head
    private Node<K, V> append(Node<K, V> node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }
}
