package com.company;

import java.util.PriorityQueue;

public class KSortedArray {
    public static void main(String[] args) {
        KSortedArray s = new KSortedArray();

        int[] array = null;
        System.out.println(s.ksort(array, 1));
        array = new int[0];
        System.out.println(s.ksort(array, 1));
        array = new int[] {3, 1, 5, 7};
        System.out.println(s.ksort(array, 1));
    }

    // Assumptions:
    // The given array is not null and length is n, k < n and k >= 0
    // Time O(nlogk)
    // Space O(k)
    public int[] ksort(int[] array, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            minHeap.offer(array[i]);
            if (i >= k) {
                array[index++] = minHeap.poll();
            }
        }
        while (!minHeap.isEmpty()) {
            array[index++] = minHeap.poll();
        }
        return array;
    }
}
