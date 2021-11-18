package com.company;
import java.util.*;

public class SuperUglyNumber {
    public static void main(String[] args) {
        SuperUglyNumber s = new SuperUglyNumber();

        int[] primes = new int[] {2, 7, 13, 19};
        for (int i = 0; i < 12; i++) {
            System.out.print(s.nthSuperUglyNumber(i + 1, primes) + " ");
        }
        // 1 2 4 7 8 13 14 16 19 26 28 32
        System.out.println();
        primes = new int[] {2,59,83,131,157,383,409,443,457,487,557,593,607,653,683,701,727,739,823,839,863,881,907,911,967};
        System.out.println(s.nthSuperUglyNumber(9800, primes));
        // 358743059

        System.out.println();
        primes = new int[] {2,13,19,43,59,137,227,263,269,397,419,433,463,479,487,491,547,563,613,653,787,797,839,877,911,991};
        System.out.println(s.nthSuperUglyNumber(3776, primes));
        // 3640832
    }

    // Note:
    // (1) 1 is a super ugly number for any given primes.
    // (2) The given numbers in primes are in ascending order.
    // (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
    // (4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
    // Time O(nlog(nk))     primes.length = k
    // Space O(nk)
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();
        minHeap.offer(1);
        visited.add(1);
        for (int i = 0; i < n - 1; i++) {
            int tmp = minHeap.poll();
            for (int j = 0; j < primes.length; j++) {
                // use double to avoid Integer overflow
                double nextUglyNumber = (double) tmp * primes[j];
                // need to de-dup using visited
                if (nextUglyNumber < Integer.MAX_VALUE && !visited.contains(primes[j] * tmp)) {
                    minHeap.offer(primes[j] * tmp);
                    visited.add(primes[j] * tmp);
                }
            }
        }
        return minHeap.peek();
    }
}
