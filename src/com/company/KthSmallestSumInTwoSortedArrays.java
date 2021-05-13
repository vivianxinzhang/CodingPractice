package com.company;
import java.util.*;

public class KthSmallestSumInTwoSortedArrays {
    public static void main(String[] args) {
        KthSmallestSumInTwoSortedArrays s = new KthSmallestSumInTwoSortedArrays();
        int[] A = new int[] {1, 3, 5};
        int[] B = new int[] {4, 8};
        System.out.println(s.kthSum(A, B, 1));  // 5
        System.out.println(s.kthSum(A, B, 2));  // 7
        System.out.println(s.kthSum(A, B, 3));  // 9
        System.out.println(s.kthSum(A, B, 5));  // 11
    }

    // Assumption: 1) m > 0, n > 0
    //             2) k >0 and k < m * n
    //			   3) A and B are sorted in ascending order
    // Approach: Use Best First Search to find the k-th smallest element
    // Total possible values of  s can be put in an imiginary 2-D array: dimension mn and there could have same values
    // Data Structure: Priority Queue -- minHeap
    // Initial State: A[0] + B[0]
    // Expand: A[i] + B[j]
    // Generate: A[i + 1] + B[j] and A[i] + B[j + 1]
    // Termination condition: before the k-th element is popped out for expansion
    // Deduplication: which node have been generated
    // Time O(klogk)
    // Space O(k + m * n)
    public int kthSum(int[] A, int[] B, int k) {
        // Best First Search, need a minHeap on the value of each Pair
        // PriorityQueue<Solution.Cell> minHeap = new PriorityQueue<>((c1, c2) -> (c1.sum - c2.sum));
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, new MyComparator());
        // all the generated cells will be marked true to avoid being generated more than once
        boolean[][] visited = new boolean[A.length][B.length];
        minHeap.offer(new Pair(A, 0, B, 0));
        visited[0][0] = true;
        // iterate k - 1 rounds, and Best First Search the smallest k - 1 values
        for (int q = 0; q < k - 1; q++) {
            Pair cur = minHeap.poll();
            // the neighbor Pair will be inserted into the minHeap only if
            // 1. it is not out of boundary
            // 2. it has not been generated before
            if (cur.row + 1 < A.length && !visited[cur.row + 1][cur.col]) {
                minHeap.offer(new Pair(A, cur.row + 1, B, cur.col));
                visited[cur.row + 1][cur.col] = true;
            }
            if (cur.col + 1 < B.length && !visited[cur.row][cur.col + 1]) {
                minHeap.offer(new Pair(A, cur.row, B, cur.col + 1));
                visited[cur.row][cur.col + 1] = true;
            }
        }
        return minHeap.peek().value;
    }

    static class Pair {
        int row;
        int col;
        int value;

        Pair(int[] A, int i, int[] B, int j) {
            this.row = i;  // i is the row index in our imiginary 2-D array
            this.col = j;   // j is the col index in our imiginary 2-D array
            this.value = A[i] + B[j];
        }
    }

    private class MyComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair p1, Pair p2) {
            if (p1.value == p2.value) {
                return 0;
            }
            return p1.value < p2.value ? -1 : 1;
        }
    }
}
