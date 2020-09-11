package com.company;
import java.util.*;

// Time = nlogk = O(k + (n - k)logk + klogk)
// Space = O(n)
public class Solution {
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0 || k > array.length) {
            return array;
        }
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            maxHeap.offer(array[i]);
        }
        for (int i = k; i < array.length; i++) {
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] result = new int[k];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
}


