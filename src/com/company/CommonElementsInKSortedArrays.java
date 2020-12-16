package com.company;
import java.util.*;

public class CommonElementsInKSortedArrays {
    public static void main(String[] args) {
        CommonElementsInKSortedArrays s = new CommonElementsInKSortedArrays();
        int[][] arrayOfArrays = new int[][] {{1, 3, 4, 9}, {0, 1, 4}, {1, 2, 4, 7, 8, 10}};
        System.out.println(Arrays.toString(s.commonI(arrayOfArrays)));  // [1, 4]
        System.out.println(Arrays.toString(s.commonII(arrayOfArrays)));  // [1, 4]
        System.out.println(s.common(arrayOfArrays));  // [1, 4]


        arrayOfArrays = new int[][]{{}, {1, 5, 7}, {4}, {2, 3, 5, 11}, {2, 4, 4, 6, 8}};
    }

    // Assumption：arrayOfArrays is not null, none of the array is null either
    // Method 3:  k-way finding common elements， play together (heap)
    // Time O(kn*logk)
    // Space O(k)
    public List<Integer> common(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.val == o2.val) {
                    return 0;
                }
                return o1.val < o2.val ? -1 : 1;
            }
        });
        int max = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != 0) {
                minHeap.offer(new Entry(i, 0, matrix[i][0]));
                max = Math.max(max, matrix[i][0]);
            }
        }
        while (!minHeap.isEmpty()) {
            int count = 1;
            if (minHeap.peek().val == max) {
                result.add(minHeap.peek().val);
                count = matrix.length;
            }
            for (int i = 0; i < count; i++) {
                Entry curr = minHeap.poll();
                if (curr.col + 1 == matrix[curr.row].length) {
                    return result;
                } else {
                    minHeap.offer(new Entry(curr.row, curr.col + 1, matrix[curr.row][curr.col + 1]));
                    max = Math.max(max, matrix[curr.row][curr.col + 1]);
                }
            }
        }
        return result;
    }

    class Entry {
        int row;
        int col;
        int val;

        Entry(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }

    }


    // Method 2: binary reduction (recursive implementation)
    // Time O(kn)  kn + k/2 * n + k/4 * n + … + 1 = n(k + k/2 + k/4 + … + 1) = n*k
    // Space O(kn) ?? O(n * logk)
    public int[] commonII(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        return commonInBinaryReduction(matrix, 0, matrix.length - 1);
    }

    private int[] commonInBinaryReduction(int[][] matrix, int i, int j) {
        if (i == j) {
            return matrix[i];
        } else if (i == j - 1) {
            int length = commonInTwo(matrix[i], matrix[i].length, matrix[j]);
            return Arrays.copyOf(matrix[i], length);
        }
        int mid = i + (j - i) / 2;
        int[] left = commonInBinaryReduction(matrix, i, mid);
        int[] right = commonInBinaryReduction(matrix, mid + 1, j);
        int newLength = commonInTwo(left, left.length, right);
        return Arrays.copyOf(left, newLength);
    }


    // Method 1: iterative reduction
    // Time O(kn)
    // Space O(n)
    public int[] commonI(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = matrix[0];
        int length = matrix[0].length;
        // [0, length - 1] is common elements result
        for (int i = 1; i < matrix.length; i++) {
            length = commonInTwo(result, length, matrix[i]);
        }
        return Arrays.copyOf(result, length);
    }

    private int commonInTwo(int[] result, int length, int[] array) {
        int i = 0;
        int j = 0;
        int cur = 0;
        while (i < length && j < array.length) {
            if (result[i] == array[j]) {
                result[cur++] = result[i];
                i++;
                j++;
            } else if (result[i] < array[j]) {
                i++;
            } else {
                j++;
            }
        }
        return cur;
    }
}
