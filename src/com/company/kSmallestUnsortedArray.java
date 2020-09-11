package com.company;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class kSmallestUnsortedArray {
    // Method 1: sorting
    // Time = O(nlogn)
    // Space = O(1)
    public int[] kSmallestI(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        Arrays.sort(array);
        return k >= array.length ? array : Arrays.copyOf(array, k);
    }

    // Method 2: heap
    // Implementation 1: n sized minHeap -- offline algorithm
    // Step 1: heapify the whole array with minHeap
    // Step 2: pop out the smallest k elements
    // Time = O(n + klogn)
    // Space = O(n)
    public int[] kSmallestII(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return array;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {
            minHeap.offer(array[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }

    // Implementation 2: k sized maxHeap  -- online algorithm
    // Step 1: heapify the first k elements with maxHeap
    // Step 2: traverse through the rest and compare each element with top of maxHeap
    //    Case 1: new element < top of maxHeap
    //            pop out top of maxHeap and insert new element into maxHeap
    //    Case 2: new element >= top of maxHeap
    //		      continue
    // Time  O(k + (n - k)*logk + klogk) ？？O(nlogk + K) ??
    // Space O(k)
    // Assumptions: 1) array is not null 2) k >= 0 and k <= array.length
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        // handle all possible corner cases at the very beginning
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        // offer the first k elements into the max heap O(k)
        for (int i = 0; i < k; i++) {
            maxHeap.offer(array[i]);
        }
        // for the other elements, only offer it into the max heap
        // if it is smaller than the max value in the max heap  O((n-k)logk)
        for (int i = k; i < array.length; i++) {
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] result = new int[k];
        // result is stored in ascending order, start to fill from the last spot
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    // Method 4: quick select
}
