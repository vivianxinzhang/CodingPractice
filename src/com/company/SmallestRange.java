package com.company;
import java.util.*;

public class SmallestRange {
    public static void main(String[] args) {
        SmallestRange s = new SmallestRange();
        int[][] matrix = new int[][] {
                {1, 4, 6},
                {2, 5},
                {8, 10, 15}
        };
        System.out.println(Arrays.toString(s.smallestRange(matrix)));   // [5, 8]

        matrix = new int[][] {
                {129,356,388,985},
                {274,381,402,911},
                {95,112,181,193,284,323,459,463,489,508,548,680,708,738,791,842,923,940,951},
                {331,431,523,538,625,646,725,793,870,945},
                {635,839,923,989},
                {74,209,324,363,407,665,824,993},
                {18,176,183,483,574,585,595,646,676,687,688,697,783,869,918,964}
        };
        System.out.println(Arrays.toString(s.smallestRange(matrix)));   // [911, 993]
    }

    // Method 4: Pointers + PriorityQueue
    // Set pointers for every list and move them only when it is pointing the current
    // smallest number among all lists (with PriorityQueue's help).
    // Keep the difference between teh numbers pointers pointing as small as possible.
    // Time O(n * m * logm)
    // Space O(m)
    public int[] smallestRangeIV(int[][] matrix) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<Entry>(matrix.length, new MyComparator());
        int minRange = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
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

    // Method 4 Another implementation: Pointers + PriorityQueue
    // Time O(n * m * logm)
    // Space O(m)
    public int[] smallestRangeV(int[][] matrix) {
        int minx = 0;
        int miny = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int[] next = new int[matrix.length];   // store all the pointers
        boolean flag = true;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((i, j) -> matrix[i][next[i]] - matrix[j][next[j]]);
        for (int i = 0; i < matrix.length; i++) {
            minHeap.offer(i);
            max = Math.max(max, matrix[i][0]);
        }
        for (int i = 0; i < matrix.length && flag; i++) {
            for (int j = 0; j < matrix[i].length && flag; j++) {
                int minI = minHeap.poll();
                if (miny - minx > max - matrix[minI][next[minI]]) {
                    minx = matrix[minI][next[minI]];
                    miny = max;
                }
                // 移动指向最小值的 pointer
                next[minI]++;
                if (next[minI] == matrix[minI].length) {
                    flag = false;   // flag shows if reached end for a certain row
                    break;
                }
                minHeap.offer(minI);
                max = Math.max(max, matrix[minI][next[minI]]);
            }
        }
        return new int[] {minx, miny};
    }

    // Method 3:
    // Set pointers for every list and move them only when it is pointing the current
    // smallest number among all lists.
    // Keep the difference between teh numbers pointers pointing as small as possible.
    // Time O(n * m * m)
    // Space O(m)
    public int[] smallestRangeIII(int[][] matrix) {
        int minx = 0;
        int miny = Integer.MAX_VALUE;
        int[] next = new int[matrix.length];   // store all the pointers
        boolean flag = true;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length && flag; j++) {
                int minI = 0;
                int maxI = 0;
                for (int k = 0; k < matrix.length; k++) {
                    // find the min and max
                    if (matrix[minI][next[minI]] > matrix[k][next[k]]) {
                        minI = k;
                    }
                    if (matrix[maxI][next[maxI]] < matrix[k][next[k]]) {
                        maxI = k;
                    }
                    // update global min and max when necessary
                    if (miny - minx > matrix[maxI][next[maxI]] - matrix[minI][next[minI]]) {
                        miny = matrix[maxI][next[maxI]];
                        minx = matrix[minI][next[minI]];
                    }
                    // 移动指向最小值的 pointer
                    next[minI]++;
                    if (next[minI] == matrix[minI].length) {
                        flag = false;   // flag shows if reached end for a certain row
                    }
                }
            }
        }
        return new int[] {minx, miny};
    }

    // Method 3: another implementation
    // Time O(kn * k)
    // Space O(k)
    public int[] smallestRangeI(int[][] matrix) {
        int[] result = new int[2];
        int[] idx = new int[matrix.length];
        int minRange = Integer.MAX_VALUE;
        while (true) {
            int minIndex = -1;
            int currMin = Integer.MAX_VALUE;
            int currMax = Integer.MIN_VALUE;
            for (int i = 0; i < idx.length; i++) {
                // reached end for a certain row
                if (idx[i] == matrix[i].length) {
                    return result;
                }
                // find the min and max
                if (matrix[i][idx[i]] < currMin){
                    currMin = matrix[i][idx[i]];
                    minIndex = i;
                }
                if (matrix[i][idx[i]] > currMax) {
                    currMax = matrix[i][idx[i]];
                }
            }
            // update minRange
            if ((currMax - currMin) < minRange) {
                minRange = currMax - currMin;
                result[0] = currMin;
                result[1] = currMax;
            }
            idx[minIndex]++;
        }
    }

    // Method 2: binary search optimization on method 1
    // pick any two numbers from all of the numbers
    // find the smallest range
    // Time O(mn * mn * mlogn)
    // Space O(1)
    public int[] smallestRangeII(int[][] matrix) {
        int minRange = Integer.MAX_VALUE;
        int[] res = new int[] {0, 0};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int first = matrix[i][j];
                for (int k = i; k < matrix.length; k++) {
                    for (int t = (k == i ? j + 1 : 0); t < matrix[k].length; t++) {
                        if (k == i && t == j) {
                            continue;
                        }
                        int second = matrix[k][t];
                        int min = Math.min(first, second);
                        int max = Math.max(first, second);
                        int curRange = max - min;
                        if (curRange < minRange && coverAllRowsI(matrix, min, max)) {
                            minRange = curRange;
                            res[0] = min;
                            res[1] = max;
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean coverAllRowsI(int[][] matrix, int min, int max) {
        int row = 0;
        while (row < matrix.length) {
            if (!binarySearch(matrix[row], min, max)) {
                return false;
            }
            row++;
        }
        return row == matrix.length;
    }

    private boolean binarySearch(int[] array, int min, int max) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= min && array[mid] <= max) {
                return true;
            } else if (array[mid] < min) {
                left = mid + 1;     // if target exist, it should be on the right of mid
            } else {
                right = mid - 1;    // if target exist, it should be on the left of mid
            }
        }
        return false;
    }

    // Method 1: brute force
    // pick any two numbers from all of the numbers
    // find the smallest range
    // Time O(mn * mn * mn)
    // Space O(1)
    public int[] smallestRange(int[][] matrix) {
        int minRange = Integer.MAX_VALUE;
        int[] res = new int[] {0, 0};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int first = matrix[i][j];
                for (int k = i; k < matrix.length; k++) {
                    for (int t = (k == i ? j : 0); t < matrix[k].length; t++) {
                        if (k == i && t == j) {
                            continue;
                        }
                        int second = matrix[k][t];
                        int min = Math.min(first, second);
                        int max = Math.max(first, second);
                        int curRange = max - min;
                        if (curRange < minRange && coverAllRows(matrix, min, max)) {
                            minRange = curRange;
                            res[0] = min;
                            res[1] = max;
                        }
                    }
                }
            }
        }
        return res;
    }

    private boolean coverAllRows(int[][] matrix, int min, int max) {
        int row = 0;
        while (row < matrix.length) {
            if (!existInRow(matrix[row], min, max)) {
                return false;
            }
            row++;
        }
        return row == matrix.length;
    }

    private boolean existInRow(int[] row, int min, int max) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] >= min && row[i] <= max) {
                return true;
            }
        }
        return false;
    }
}