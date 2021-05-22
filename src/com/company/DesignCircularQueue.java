package com.company;

import DataStructure.CircularQueueResizable;

import java.util.Arrays;

public class DesignCircularQueue {
    int head;
    int tail;
    int size;
    int[] array;
    private int cap;

    public static void main(String[] args) {
        DesignCircularQueue queue = new DesignCircularQueue(2);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);

        System.out.println(queue.Front());       // 1

        System.out.println(queue.isEmpty());     // false
        System.out.println(queue.isFull());      // true
        System.out.println(queue.Front());       // 1
        System.out.println(queue.Rear());        //
    }

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public DesignCircularQueue(int k) {
        array = new int[k];
        head = 0;
        tail = 0;
        size = 0;
        cap = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size == array.length) {
            return false;
        }
        array[tail] = value;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        size++;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        int result = array[head];
        head = head + 1 == array.length ? 0 : head + 1;
        // head + 1 % array.length
        size--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return size == 0 ? -1 : array[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (size == 0) {
            return -1;
        }
        int lastIdx = tail - 1 == -1 ? array.length - 1 : tail - 1;
        return array[lastIdx];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == cap;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */