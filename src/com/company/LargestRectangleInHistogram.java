package com.company;
import java.util.*;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram s = new LargestRectangleInHistogram();
        int[] array = new int[] {2, 1, 3, 3, 4};
        System.out.println(s.largest(array));
        System.out.println(s.largestI(array));

        array = new int[] {2, 1, 3, 4, 5, 2, 6};
        System.out.println(s.largest(array));
        System.out.println(s.largestI(array));
    }

    // Method 2:
    // Use a stack to store all the indices of the columns that form an ascending order
    // stack that stores the indices in ascending order		Bottom || [1, 2, 3, 4
    // When scanning the element with index = 5, M[5] == 2 < M[4] == 5, so we keep checking left column of index 5,
    // and calculate the area of index 4, 3, 2, and pop them out of the stack, after this step, the stack is Bottom || [1, 5
    // Principle, to maintain the stack to make sure the columns whose indices are stored in the stack form an ascending order.
    // 细节：When popped an element out of the stack, the element’s right border == the current index - 1,
    // the left border of the element = the index of the element on top of the stack + 1;
    // Time = O(n) because every single element can only be inserted and popped out of the stack once and only once.
    // Space O(n)
    public int largest(int[] array) {
        // Write your solution here
        // Assumptions: array is not null, array.length >= 1
        // all the values in the array are non-negative
        int result = 0;
        // Note that the stack contains the "index", not the "value" of the array
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {
            // we need a way of popping out all the elements in the stack at last,
            // so that we explicitly add a bar of height 0
            int cur = i == array.length ? 0 : array[i];
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
                int height = array[stack.pollFirst()];
                // determine the left boundary of the largest rectangle
                // with height array[i]
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                // determine the right boundary of the largest rectangle
                // with height of the popped element
                result = Math.max(result, height * (i - left));
            }
            stack.offerFirst(i);
        }
        return result;
    }

    // Method 1: 中心开花
    // for each index i {
    //    try using myself as the top bound
    //    中心开花，go left, go right, until we found a lower height
    // Time = O(n^2)
    // Extra space = O(1)
    public int largestI(int[] array) {
        int maxArea = 0;
        for (int i = 0; i < array.length; i++) {
            int leftBorder = findLeftBorder(array, i);
            int rightBorder = findRightBorder(array, i);
            int currArea = (rightBorder - leftBorder + 1) * array[i];
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }

    private int findLeftBorder(int[] array, int i) {
        int currHeight = array[i];
        while (i > 0 && array[i - 1] >= currHeight) {
            i--;
        }
        return i;
    }

    private int findRightBorder(int[] array, int i) {
        int currHeight = array[i];
        while (i < array.length - 1 && array[i + 1] >= currHeight) {
            i++;
        }
        return i;
    }
}
