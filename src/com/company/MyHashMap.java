package com.company;
import java.util.Arrays;

/**
 * A hashtable implementation of map, demonstration purpose, generic type is provided
 * supported operations:
 * size()
 * isEmpty()
 * put(K key, V value)
 * get(K key)
 * containsKey(K key)
 * containsValue(V value)   // check if the desired value is in the map O(n)
 * remove(K key)
 * 1. use case -> API (public methods)
 * 2. data -> input/output/field -> data structure -> constructor
 * 3. implementation
 */
public class MyHashMap<K, V> {
    // Node is a static class of MyHashMap, since it is:
    // very closely bonded to MyHashMap class.
    // we probably need to access this class outside from MyHashMap class
    static class Node<K, V> {
        private final K key;//不可以改 要跟前面的hash func对应改了就找不到了
        private V value;
        Node<K,V> next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {   // why not???
            return value;
        }

        public void setValue(V value) {  // 1. Do you need to synchronize this? No
            this.value = value;
        }
    }

    // static final variable are global constants
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int SCALE_FACTOR = 2;

    private Node<K, V>[] array;
    private int size;   // how many key-value pairs are actually stored in the HashMap
    private float loadFactor;   // determine when to rehash

    public MyHashMap(int cap, float loadFactor) {
        if (cap <= 0) {
            throw new IllegalArgumentException("cap can not be <= 0");
        }
        this.array = (Node<K, V>[])(new Node[cap]);
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {  // 2. need to synchronize this? yes
        return size == 0;
    }

    public synchronized void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }

    // non-negative
    private int hash(K key) {   // 3. synchronize on private methods? No
        // 1. null key
        if (key == null) {
            return 0;
        }
        // 2.3. hashCode()
        // int code = key.hashCode();
        // return code >= 0 ? code : -code;
        // int range = [-2^31, 2^31 -1]
        // -Integer.MIN_VALUE = Integer.MIN_VALUE; -> overflow
        int code = key.hashCode();
        return code & 0X7FFFFFFF; // guarantee non-negative
        // 01111111 11111111 11111111 11111111
        // & 最高位取0表示正数 其它位不变
        // Reason: java's % return remainder rather than modulus.
        // The remainder can be negative
    }

    //    private int getIndex(int hash) {
//        return hash % array.length;
//    }
    private int getIndex(K key) {
        return hash(key) % array.length;
    }

    private boolean equalsKey(K k1, K k2) {
        // k1, k2 all possibly to be null
//        if (k1 == null && k2 == null) {
//            return true;
//        }
//        if (k1 == null || k2 == null) {
//            return false;
//        }
//        return k1.equals(k2);
        // return k1 != null && k1.equals(k2) || k1 == k2;
        return k1 == k2 || k1 != null && k1.equals(k2);
    }

    // insert/ update
    // if the key already exists, return the old corresponding value
    // if the key not exists, return null
    public synchronized V put(K key, V value) {
        int index = getIndex(key);
        Node<K, V> cur = array[index];
        // need to check if the key exist already
        // if exist, update value
        while (cur != null) {
            // check if the key equals()
            // key, node.key() are possible to be null
            if (equalsKey(cur.key, key)) {
                V result = cur.value;
                cur.value = value;
                // return previous value
                return result;
            }
            cur = cur.next;
        }
        // key does not exist
        // append the new node before the head and update the new head
        // insert operation
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = array[index];
        array[index] = newNode; // new head is here
        size++;
        if (needRehashing()) {
            rehashing();
        }
        // key does not exist
        return null;
    }

    private boolean needRehashing() {
        // float loadFactor
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }

    private void rehashing() {
        // new double sized array.
        // for each node in the old array,
        // do the put() operation (add to head of linked list) to new larger array
        Node<K, V>[] oldArray = array;
        array = (Node<K, V>[]) (new Node[array.length * SCALE_FACTOR]);
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                Node<K, V> next = node.next;
                // index in the new array, where to insert the node from old array
                int index = getIndex(node.key);
                // insert node to the linkedlist of the new array
                node.next = array[index];
                array[index] = node;
                // move node to next, prepare to process the next node in the old array
                node = next;
            }
        }
    }

    // if key does not exists in the HashMap, return null
    public synchronized V get(K key) { // 4. synchronize for public and/or read-only methods? Yes
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            // check if the key equals()
            // key, node.key() are possible to be null
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public synchronized boolean containsKey(K key) {
        // get the index of the key
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            // check if the key equals()
            // key, node.key() are possible to be null
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean equalsValue(V v1, V v2) {
        // v1, v2 both possibly to be null
//        if (v1 == null && v2 == null) {
//            return true;
//        }
//        if (v1 == null || v2 == null) {
//            return false;
//        }
//        return v1.equals(v2);
        return v1 == v2 || v1 != null && v1.equals(v2);
    }

    // O(n), traverse the whole array, and traverse each of the linked list in the array
    public synchronized boolean containsValue(V value) {
        // special case
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node : array) {
            while (node != null) {
                // check if the value equals()
                // value, node.getValue() all possible to be null
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }   // 当前 thread instance vs. this

    // if the key exists, remove the <key, value> from the HashMap, return the value
    // if the key not exists, return null
    public synchronized V remove(K key) {
        // get index
        // delete operation on the linked list
        // size--
        int index = getIndex(key);
        Node<K, V> pre = null;
        Node<K, V> cur = array[index];
        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                if (pre == null) {
                    array[index] = cur.next;
                } else {
                    pre.next = cur.next;
                }
                size--;
                return cur.value;
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }
}