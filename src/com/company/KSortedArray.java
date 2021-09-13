package com.company;

import java.util.PriorityQueue;

public class KSortedArray {
    public static void main(String[] args) {
        KSortedArray s = new KSortedArray();

    }

    // Assumptions:
    // The given array is not null and length is n, k < n and k >= 0
    // Time O((n+k)logk)
    // Space O(k)
    public int[] ksort(int[] array, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (fast < k + 1) {
                minHeap.offer(array[fast]);
                fast++;
            } else {
                array[slow] = minHeap.poll();
                slow++;
                minHeap.offer(array[fast]);
                fast++;
            }
        }
        while (slow < array.length) {
            array[slow] = minHeap.poll();
            slow++;
        }
        return array;
    }
}
