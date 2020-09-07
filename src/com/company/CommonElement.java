package com.company;
import java.util.Arrays;

public class CommonElement {
    public static void main(String[] args) {

        CommonElement s = new CommonElement();
        int[][] matrix = new int[][]{{0},{1},{1},{1}};
        System.out.println(s.search(matrix));
    }

    // Method 5: k pointers
    // Time O(k * kn)
    // Space O(1)
    public int search(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        int[] lastCol = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            lastCol[i] = matrix[i].length - 1;
        }
        int minRow = -1;
        while (lastCol[minRow] >= 0) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][lastCol[i]] < matrix[minRow][lastCol[minRow]]) {
                    minRow = i;
                }
            }
            int countEqual = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][lastCol[i]] > matrix[minRow][lastCol[minRow]]) {
                    if (lastCol[i] == 0) {
                        return -1;
                    }
                    lastCol[i]--;
                } else {
                    countEqual++;
                }
            }
            if (countEqual == matrix.length) {
                return matrix[minRow][lastCol[minRow]];
            }
        }
        return -1;
    }

    // Method 4: k pointers
    // each round find the largest value and move all indexes pointing to smaller values
    // Time O(k * kn)
    // Space O(1)
    public int search4(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        int[] idx = new int[matrix.length];
        while (true) {
            int currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE, maxIdx = -1;
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == matrix[i].length) {
                    return -1;
                }
                currMin = matrix[i][idx[i]] < currMin ? matrix[i][idx[i]] : currMin;
                if (matrix[i][idx[i]] > currMax) {
                    currMax = matrix[i][idx[i]];
                    maxIdx = i;
                }
            }
            if (currMin == currMax) {
                return matrix[maxIdx][idx[maxIdx]];
            }
            for (int i = 0; i < idx.length; i++) {
                if (matrix[i][idx[i]] == matrix[maxIdx][idx[maxIdx]]) {
                    continue;
                }
                idx[i]++;
            }
        }
    }

    // Method 3: k pointers
    // each round find the smallest value and move the index of smallest value among all rows
    // Time O(k * kn)
    // Space O(1)
    public int search3(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        int[] idx = new int[matrix.length];
        int minRange = Integer.MAX_VALUE;
        while (true) {
            int currMin = Integer.MAX_VALUE, currMax = Integer.MIN_VALUE, minIdx = -1;
            for (int i = 0; i < idx.length; i++) {
                if (idx[i] == matrix[i].length) {
                    return -1;
                }
                if (matrix[i][idx[i]] < currMin) {
                    currMin = matrix[i][idx[i]];
                    minIdx = i;
                }
                currMax = matrix[i][idx[i]] > currMax ? matrix[i][idx[i]] : currMax;
            }
            if (currMin == currMax) {
                return matrix[minIdx][idx[minIdx]];
            }
            idx[minIdx]++;
        }
    }

    // Method 2: use binary search instead of linear search
    // Time O(n * klogn)
    // Space O(1)


    // Method 1: linear search in each row
    // Time O(n * nk)
    // Space O(1)
    public int search1(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return -1;
        }
        for (int ele : matrix[0]) {
            int row = 1;
            while (row < matrix.length) {
                if (!contains(matrix[row], ele)) {
                    break;
                }
                row++;
            }
            if (row == matrix.length) {
                return ele;
            }
        }
        return -1;
    }

    private boolean contains(int[] array, int target) {
        for (int ele : array) {
            if (ele == target) {
                return true;
            }
        }
        return false;
    }
}
