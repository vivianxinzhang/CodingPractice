package com.company;
import java.util.*;

public class KthSmallestWithOnlyThreeFiveSevenAsFactors {
    public static void main(String[] args) {
        KthSmallestWithOnlyThreeFiveSevenAsFactors s = new KthSmallestWithOnlyThreeFiveSevenAsFactors();
        System.out.println(s.kth(1));   // 105
        System.out.println(s.kth(2));   // 315
        System.out.println(s.kth(3));   // 525
        System.out.println(s.kth(5));   // 945
    }

    // Assumption: K >= 1
    // Time O(klogk) <-- O(k(log2 + logk)) <-- O(klog(2k))
    // Space O(k)  <-- at most 2k elements in minHeap
    public long kth(int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        // we use the actual product value to represent the states <x, y, z>,
        // the value is 3^x * 5^y * 7^z, and the initial state is <1, 1, 1>.
        minHeap.offer(3 * 5 * 7L);
        visited.add(3 * 5 * 7L);
        while (k > 1) {
            long current = minHeap.poll();
            // for the state <x+1, y, z>, the actual value is *3
            if (visited.add(3 * current)) {
                minHeap.offer(3 * current);
            }
            // for the state <x, y+1, z>, the actual value is *5
            if (visited.add(5 * current)) {
                minHeap.offer(5 * current);
            }
            // for the state <x, y, z+1>, the actual value is *7
            if (visited.add(7 * current)) {
                minHeap.offer(7 * current);
            }
            k--;
        }
        return minHeap.peek();
    }
}
