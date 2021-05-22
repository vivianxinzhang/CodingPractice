package com.company;

import DataStructure.BoundedDeque;

import java.util.Arrays;

class MyCircularDeque {
    int[] array;
    int head;
    int tail;
    int size;

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);
        deque.insertFront(1);
        deque.insertLast(2);

        System.out.println(deque.isEmpty());      // false
        System.out.println(deque.isFull());       // true

        System.out.println(deque.getFront());     // 1
        System.out.println(deque.getRear());      // 2

        System.out.println(deque.deleteFront());  // true
        System.out.println(deque.deleteLast());   // true

        System.out.println(deque.isEmpty());      // true
    }

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        array = new int[k];
        head = 0;
        tail = 0;
        size = 0;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (size == array.length - 1) {
            return false;
        }
        array[head] = value;
        head = head - 1 == -1 ? array.length - 1 : head - 1;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (size == array.length - 1) {
            return false;
        }
        int index = tail + 1 == array.length ? 0: tail + 1;
        array[index] = value;
        tail = index;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (size == 0) {
            return false;
        }
        int index = head + 1 == array.length ? 0 : head + 1;
        head = index;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }
        tail = tail - 1 == -1 ? array.length - 1 : tail - 1;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (size == 0) {
            return -1;
        }
        int index = head + 1 == array.length ? 0 : head + 1;
        return array[index];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (size == 0) {
            return -1;
        }
        return array[tail];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == array.length - 1;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
