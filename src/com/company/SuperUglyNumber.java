package com.company;
import java.util.*;

public class SuperUglyNumber {
    public static void main(String[] args) {
        SuperUglyNumber s = new SuperUglyNumber();
        int[] result = new int[12];
        for (int i = 0; i < result.length; i++) {
            result[i] = s.nthSuperUglyNumber(i + 1, new int[] {2, 7, 13, 19});
        }
        System.out.println(Arrays.toString(result));

        int[] primes = new int[] {2,59,83,131,157,383,409,443,457,487,557,593,607,653,683,701,727,739,823,839,863,881,907,911,967};
        System.out.println(s.nthSuperUglyNumber(9800, primes));
    }

    // Time O(n)
    // Space O(n * primes.length)
    public int nthSuperUglyNumber(int n, int[] primes) {
        // Write your solution here
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[o1.length - 1] == o2[o2.length - 1]) {
                    return 0;
                }
                return o1[o1.length - 1] < o2[o2.length - 1] ? -1 : 1;
            }
        });
        Set<Integer> visited = new HashSet<>();
        int[] curr = new int[primes.length + 1];
        Arrays.fill(curr, 0);;
        curr[curr.length - 1] = 1;
        minHeap.offer(curr);
        for (int i = 0; i < n; i++) {
            curr = minHeap.poll();
            for (int j = 0; j < primes.length; j++) {
                double nextUglyNumber = (double) curr[curr.length - 1] * primes[j];
                if (!visited.contains(curr[curr.length - 1] * primes[j]) && nextUglyNumber < Integer.MAX_VALUE) {
                    int[] next = Arrays.copyOf(curr, curr.length);
                    next[next.length - 1] = curr[curr.length - 1] * primes[j];
                    minHeap.offer(next);
                    visited.add(curr[curr.length - 1] * primes[j]);
                }
            }
        }
        return curr[curr.length - 1];
    }
}
