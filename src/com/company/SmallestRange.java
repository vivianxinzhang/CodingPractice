package com.company;
import java.util.*;

public class SmallestRange {
    public static void main(String[] args) {
        SmallestRange s = new SmallestRange();
        int[][] matrix = new int[][] {{129,356,388,985},{274,381,402,911},{95,112,181,193,284,323,459,463,489,508,548,680,708,738,791,842,923,940,951},{331,431,523,538,625,646,725,793,870,945},{635,839,923,989},{74,209,324,363,407,665,824,993},{18,176,183,483,574,585,595,646,676,687,688,697,783,869,918,964}};
        System.out.println(Arrays.toString(s.smallestRange2(matrix)));
    }

    // Method 2:
    // Time O(kn * logk)
    // Space O(k)
    public int[] smallestRange2(int[][] matrix) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(matrix.length, new MyComparator());
        int minRange = Integer.MAX_VALUE, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] result = new int[2];
        // initialize:
        for (int row = 0; row < matrix.length; row++) {
            minHeap.offer(new Entry(row, 0, matrix[row][0]));
            min = matrix[row][0] < min ? matrix[row][0] : min;
            max = matrix[row][0] > max ? matrix[row][0] : max;
        }

        if ((max - min) < minRange) {
            minRange = (max - min) < minRange ? (max - min) : minRange;
            result[0] = min;
            result[1] = max;
        }

        // loop to find minRange
        while (!minHeap.isEmpty()) {
            Entry tmp = minHeap.poll();
            if (tmp.col + 1 == matrix[tmp.row].length) {
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

    // Method 1:
    // Time O(kn * k)
    // Space O(k)
    public int[] smallestRange1(int[][] matrix) {
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