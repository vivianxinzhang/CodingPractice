package com.company;
import java.util.*;

public class Solution {
    public int[] smallestRange(int[][] matrix) {
        int length = 0;
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(14, new MyComparator());
        for (int row = 0; row < matrix.length; row++) {
            length += matrix[row].length;
            if (matrix[row].length != 0) {
                minHeap.offer(new Entry(row, 0, matrix[row][0]));
            }
        }
        int[] result = new int[length];
        int curr = 0;
        while (!minHeap.isEmpty()) {
            Entry tmp = minHeap.poll();
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

