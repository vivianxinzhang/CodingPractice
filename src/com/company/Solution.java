package com.company;

import java.util.*;

public class Solution {
    // Method 2:
    // Time O(kn * logk)
    // Space O(k)
    public int[] smallestRange(int[][] matrix) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(matrix.length, new MyComparator());
        int minRange = Integer.MAX_VALUE, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] result = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            minHeap.offer(new Entry(row, 0, matrix[row][0]));
            min = matrix[row][0] < min ? matrix[row][0] : min;
            max = matrix[row][0] > max ? matrix[row][0] : max;
            minRange = (max - min) < minRange ? (max - min) : minRange;
        }

        while (!minHeap.isEmpty()) {
            Entry tmp = minHeap.poll();
            if (tmp.col + 1 < matrix[tmp.row].length) {
                return result;
            }
            tmp.col++;
            tmp.value = matrix[tmp.row][tmp.col];
            minHeap.offer(tmp);
            min = minHeap.peek().value;
            max = tmp.value > max ? tmp.value : max;
            if ((max - min) < minRange) {
                minRange = (max - min) < minRange ? (max - min) : minRange;
                result[0] = min;
                result[1] = max;
            }
        }
        return result;
    }

    static class MyComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry e1, Entry e2) {
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
}
