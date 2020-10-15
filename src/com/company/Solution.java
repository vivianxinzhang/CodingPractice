package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println("main");
        Solution s = new Solution();
//        String[] combo = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
//        System.out.println(Arrays.toString(combo));
//        String str = "abc";
        int[] A = {1, 3, 5};
        int[] B = {4, 8};
        System.out.println(s.kthSmallest(A, B, 4));
    }

    // Time O(klogk)
    // Space O(k)
    public int kthSmallest(int[] A, int[] B, int k) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        minHeap.offer(new Pair(0, 0, A[0] + B[0]));
        for (int i = 1; i < k; i++) {
            Pair curr = minHeap.poll();
            if (curr.indexA + 1 < A.length) {
                minHeap.offer(new Pair(curr.indexA + 1, curr.indexB, A[curr.indexA + 1] + B[ curr.indexB]));
            }
            if (curr.indexB + 1 < B.length) {
                minHeap.offer(new Pair(curr.indexA, curr.indexB + 1, A[curr.indexA] + B[curr.indexB + 1]));
            }
        }
        return minHeap.peek().sum;
    }

    class Pair implements Comparable<Pair> {
        int indexA;
        int indexB;
        int sum;

        public Pair(int indexA, int y, int sum) {
            this.indexA = indexA;
            this.indexB = indexB;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair another) {
            if (this.sum == another.sum) {
                return 0;
            }
            return this.sum < another.sum ? -1 : 1;
        }
    }


}
