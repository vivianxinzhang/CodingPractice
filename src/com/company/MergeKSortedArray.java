package com.company;
import java.util.*;

public class MergeKSortedArray {
    public static void main(String[] args) {
        MergeKSortedArray s = new MergeKSortedArray();
        int[][] arrayOfArrays = new int[][] {{1, 3, 4, 9}, {0, 1, 4}, {1, 2, 4, 7, 8, 10}};
        System.out.println(Arrays.toString(s.mergeIII(arrayOfArrays)));
        System.out.println(Arrays.toString(s.mergeII(arrayOfArrays)));
        System.out.println(Arrays.toString(s.mergeI(arrayOfArrays)));
        System.out.println(Arrays.toString(s.merge(arrayOfArrays)));

        arrayOfArrays = new int[][]{{},{1,5,7},{4},{2,3,5,11},{2,4,4,6,8}};
        System.out.println(Arrays.toString(s.mergeIII(arrayOfArrays)));
        System.out.println(Arrays.toString(s.mergeII(arrayOfArrays)));
        System.out.println(Arrays.toString(s.mergeI(arrayOfArrays)));
        System.out.println(Arrays.toString(s.merge(arrayOfArrays)));
    }

    // Assumption：arrayOfArrays is not null, none of the array is null either
    // Method 3: play together (heap)
    // Time O(kn*logk)
    // Space O(k)
    public int[] mergeIII(int[][] arrayOfArrays) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(11, new MyComparator());
        int length = 0;
        // initialize minHeap
        for (int i = 0; i < arrayOfArrays.length; i++) {
            int[] array = arrayOfArrays[i];
            length += array.length;
            if (array.length != 0) {
                // we use two index to record the position of each element:
                // i is the index of the array in the arrayOfArrays --> row
                // y is the index of the element in array[i] --> col
                minHeap.offer(new Entry(i, 0, array[0]));
            }
        }
        int[] result = new int[length];
        int cur = 0;
        while (!minHeap.isEmpty()) {
            Entry tmp = minHeap.poll();
            result[cur++] = tmp.value;
            if (tmp.y + 1 < arrayOfArrays[tmp.x].length) {
                // reuse the same Entry object but advance the index by 1
                tmp.y++;
                tmp.value = arrayOfArrays[tmp.x][tmp.y];
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
        // The row number
        int x;
        // The column number
        int y;
        // The corresponding value
        int value;

        Entry(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    // Method 2: binary reduction (recursive implementation)
    // Time O(kn * logk)
    // Space O(kn)
    public int[] mergeII(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        return merge(matrix, 0, matrix.length - 1);
    }

    private int[] merge(int[][] matrix, int i, int j) {
        if (i == j) {
            return matrix[i];
        }
        if (i == j - 1) {
            return merge(matrix[i], matrix[j]);
        }
        int mid = i + (j - i) / 2;
        int[] left = merge(matrix, i, mid);
        int[] right = merge(matrix, mid + 1, j);
        return merge(left, right);
    }

    private int[] merge(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (A[i] < B[j]) {
                result[k] = A[i];
                i++;
            } else {
                result[k] = B[j];
                j++;
            }
            k++;
        }
        while (i < m) {
            result[k] = A[i];
            i++;
            k++;
        }
        while (j < n) {
            result[k] = B[j];
            j++;
            k++;
        }
        return result;
    }

    // Method 1: iterative reduction
    // Time O(k^2 * n)
    // Space O(kn)
    public int[] mergeI(int[][] matrix) {
        // Write your solution here
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] tmp = matrix[0];
        for (int i = 1; i < matrix.length; i++) {
            tmp = merge(tmp, matrix[i]);
        }
        return tmp;
    }

    // Method 0:
    // Time O(mn * log(mn))
    // Space O(kn)
    public int[] merge(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int length = 0;
        for (int[] array : matrix) {
            length += array.length;
        }
        int[] result = new int[length];
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[k] = matrix[i][j];
                k++;
            }
        }
        Arrays.sort(result);
        return result;
    }
}


