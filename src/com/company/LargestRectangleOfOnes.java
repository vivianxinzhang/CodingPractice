package com.company;
import java.util.*;

public class LargestRectangleOfOnes {
    public static void main(String[] args) {
        LargestRectangleOfOnes s = new LargestRectangleOfOnes();

        int[][] matrix = new int[][]{{0,1,1,1},{1,1,0,1},{0,1,0,1},{1,1,1,1}};
        System.out.println(s.largest(matrix));  // 4

        matrix = new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        System.out.println(s.largest(matrix));  // 6

        matrix = new int[][]{{1,1,1,1},{0,0,1,1},{1,0,1,1},{1,1,1,1}};
        System.out.println(s.largest(matrix));  // 8
    }

    // Assumptions:
    // The given matrix is not null and has size of M * N, M >= 0 and N >= 0
    // optimize space: fill M and compute max for each row at the same time
    // only need to record one row
    // Time O(mn)
    // Space O(n)
    public int largest(int[][] matrix) {
        int max = 0;
        int[] M = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    M[j] = matrix[i][j];
                } else if (matrix[i][j] == 1) {
                    M[j] = M[j] + 1;
                } else if (matrix[i][j] == 0){
                    M[j] = 0;
                }
            }
            int currMax = largestRectangleInHistogram(M);
            max = Math.max(max, currMax);
        }
        return max;
    }

    // Assumption:
    // The given matrix is not null and has size of M * N, M >= 0 and N >= 0
    // Time O(mn)
    // Space O(mn)
    public int largestI(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        // Step 1: preProcess - find longest consecutive 1s from top to bottom - O(mn)
        int[][] M = topLongestConsecutiveOnes(matrix);
        // Step 2: enumerate all the bottom line and find the largest rectangle in the histogram
        int largest = 0;
        for (int i = 0; i < M.length; i++) {
            // find the largest
            int currMax = largestRectangleInHistogram(M[i]);
            largest = Math.max(largest, currMax);
        }
        return largest;
    }

    // longest consecutive one top of each element(including)
    private int[][] topLongestConsecutiveOnes(int[][] matrix) {
        int[][] M = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    M[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0){
                    M[i][j] = 0;
                } else {
                    M[i][j] = M[i - 1][j] + 1;
                }
            }
        }
        return M;
    }

    // Time O(n)
    // Space O(n)
    private int largestRectangleInHistogram(int[] array) {
        // Assumptions: array is not null, array.length >= 1
        // all the values in the array are non-negative
        int max = 0;
        // Note that the stack contains the "index", not the "value" of the array
        Deque<Integer> incStack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {   // must including = array.length in order to pop all element out
            // we need a way of popping out all the elements in the stack at last,
            // so that we explicitly add a bar of height 0
            int nextHeight = i == array.length ? 0 : array[i];
            while (!incStack.isEmpty() && array[incStack.peekFirst()] > nextHeight) {
                int currHeight = array[incStack.pollFirst()];
                // determine the left boundary of the largest rectangle
                // with height array[i]
                int left = incStack.isEmpty() ? 0 : incStack.peekFirst() + 1;
                // determine the right boundary of the largest rectangle with height of the popped element
                int right = i - 1;
                int currMax = (right - left + 1) * currHeight;
                max = Math.max(currMax, max);
            }
            incStack.offerFirst(i);
        }
        return max;
    }

    // Method 1:
    // enumerate all rectangles in the matrix, and check if it is all ones
    // Time: O((mn)^3)
    // Space: O(1)
}
