package com.company;
import java.util.*;

public class SmallestRange {
    public static void main(String[] args) {
        SmallestRange s = new SmallestRange();
        int[][] matrix = new int[][] {{1, 2}, {3, 4}};
        System.out.println(Arrays.toString(s.smallestRange(matrix)));
    }

    // Method 2:
    // Time O(kn * k)
    // Space O(k)
    public int[] smallestRange(int[][] matrix) {
        int length = 0;
        PriorityQueue<Solution.Entry> minHeap = new PriorityQueue<Solution.Entry>(14, new Solution.MyComparator());
        for (int row = 0; row < matrix.length; row++) {
            length += matrix[row].length;
            if (matrix[row].length != 0) {
                minHeap.offer(new Solution.Entry(row, 0, matrix[row][0]));
            }
        }
        int[] result = new int[length];
        int curr = 0;
        while (!minHeap.isEmpty()) {
            Solution.Entry tmp = minHeap.poll();
            result[curr] = tmp.value;
            curr++;
            if (tmp.col + 1 < matrix[tmp.row].length) {
                tmp.col++;
                tmp.value = matrix[tmp.row][tmp.col];
                minHeap.offer(tmp);
            }
        }
        return result;
    }
    static class MyComparator implements Comparator<Solution.Entry> {
        @Override
        public int compare(Solution.Entry e1, Solution.Entry e2) {
            if (e1.value == e2.value) {
                return 0;
            }
            return e1.value < e2.value ? -1 : 1;
        }
    }

    static class Entry {
        int row;
        int col;
        int value;

        Entry(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    // Method 1:
    // Time O(kn * k)
    // Space O(k)
    public int[] smallestRange2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[2];
        int[] idx = new int[matrix.length];
        int minRange = Integer.MAX_VALUE;
        while (true) {
            int minIndex = -1, currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE;
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == matrix[i].length) {
                    return result;
                }
                if (matrix[i][idx[i]] < currMin){
                    currMin = matrix[i][idx[i]];
                    minIndex = i;
                }
                if (matrix[i][idx[i]] > currMax) {
                    currMax = matrix[i][idx[i]];
                }
            }
            if ((currMax - currMin) < minRange) {
                minRange = currMax - currMin;
                result[0] = currMin;
                result[1] = currMax;
            }
            idx[minIndex]++;
        }
    }
}