package com.company;
import java.util.*;

public class SuperUglyNumber {
    public static void main(String[] args) {
        SuperUglyNumber s = new SuperUglyNumber();
        System.out.println(s.nthSuperUglyNumber(12, new int[] {2, 7, 13, 19}));
    }

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
        int[] curr = new int[primes.length + 1];
        Arrays.fill(curr, 0);;
        curr[curr.length - 1] = 1;
        minHeap.offer(curr);
        for (int i = 0; i < n; i++) {
            curr = minHeap.poll();
            for (int j = 0; j < primes.length; j++) {
                int[] next = Arrays.copyOf(curr, curr.length);
                next[next.length - 1] = curr[curr.length - 1] * primes[j];
                minHeap.offer(next);
            }
        }
        return minHeap.peek()[curr.length - 1];
    }
}
