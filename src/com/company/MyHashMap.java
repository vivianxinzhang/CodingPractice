package com.company;

/**
 * A hashtable implementation of map, demonstration purpose, generic type is provided
 * <p>
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
public class MyHashMap {
    private static float loadFactor = 0.75f;
    Entry[] array;
    int size;

    public Integer put(String key, Integer value) {
        if (size > array.length * loadFactor) {
            rehash();
        }
        int hash = hash(key);
        int index = hash % array.length;
        Entry head = array[index];
        while (head != null) {
            if (equalsKey(head.key, key)) {
                Integer preValue = head.value;
                head.value = value;
                return preValue;
            }
            head = head.next;
        }
        Entry newEntry = new Entry(key, value);
        newEntry.next = array[index];
        array[index] = newEntry;
        size++;
        return null;
    }

    public Integer get(String key) {
        int hash = hash(key);
        int index = hash % array.length;
        Entry head = array[index];
        while (head != null) {
            if (equalsKey(head.key, key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public Integer remove(String key) {
        int hash = hash(key);
        int index = hash % array.length;
        Entry head = array[index];
        Entry pre = null;
        while (head != null) {
            if (equalsKey(head.key, key)) {
                if (pre == null) {
                    array[index] = head.next;
                } else {
                    pre.next = head.next;
                }
                head.next = null;
                size--;
                return head.value;
            }
            pre = head;
            head = head.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int hash(String key) {
        if (key == null) {
            return 0;
        }
        int hashNumber = key.hashCode();
        return Math.abs(hashNumber);
    }

    private void rehash() {
        Entry[] newArray = new Entry[array.length * 2 + 1];
        for (int i = 0; i < array.length; i++) {
            Entry head = array[i];
            while (head != null) {
                Entry next = head.next;
                int hash = hash(head.key);
                int index = hash % array.length;
                head.next = newArray[index];
                newArray[index] = head;
                head = next;
            }
            array = newArray;
        }
    }

    private boolean equalsKey(String key1, String key2) {
        if (key1 == null) return key2 == null;
        return key1.equals(key2);
    }

    class Entry {
        final String key;
        Integer value;
        Entry next;

        Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }
}
