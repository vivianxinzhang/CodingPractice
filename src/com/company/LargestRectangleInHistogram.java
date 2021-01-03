package com.company;
import java.util.*;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        LargestRectangleInHistogram s = new LargestRectangleInHistogram();
        int[] array = new int[] {2, 1, 3, 3, 4};
        System.out.println(s.largest(array));   // 9
        System.out.println(s.largestI(array));

        array = new int[] {2, 1, 3, 4, 5, 2, 6};
        System.out.println(s.largest(array));   // 10
        System.out.println(s.largestI(array));
    }

    // Assumptions: array is not null, array.length >= 1
    // all the values in the array are non-negative
    // Method 2:
    // Use a stack to store all the indices of the columns that form an ascending order
    // stack that stores the indices in ascending order		Bottom || [1, 2, 3, 4
    // 							-
    //                          -		-
    //				        -	-		-
    //			        -	-	-		-
    //			—       -	-	-	-	-
    //          -   -	-	-	-	-	-
    // index 	0	1	2	3	4	5   6
    // L		0   0	2	3	4	2   6
    // R		0   6	3	3	3	6	6
    // height 	2	1	3	4	5	2	6
    // When scanning the element with index = 5, array[5](2) < array[4](5), so we keep checking left column of index 5,
    // and calculate the area of index 4, 3, 2, and pop them out of the stack, after this step, the stack is Bottom || [1, 5
    // Principle, to maintain the stack to make sure the columns whose indices are stored in the stack form an ascending order.
    // How to get the left borader and right border：
    // When popped an element out of the stack, the element’s right border == the current index - 1,
    // the left border of the element = the index of the element on top of the stack + 1;
    // 从左到右 linear scan: 如果变大   当前元素的左边界就确定了 右边界还不知道
    // 			 	       (如果单调递增 左边都比我矮 左边界都确定了)
    //	                    如果变小   当高度掉下来后 左边比它高的柱子的右边界就确定了
    //				       (可以确定前面所有比它大的元素的右边界)
    // 右边界不确定时 continue linear scan 往前走
    // 掉下来了 右边界确定了 就要回头看 计算面积
    // Time O(n) every single element can only be inserted and popped out of the stack once and only once.
    // Space O(n)
    public int largest(int[] array) {
        int result = 0;
        // Note that the stack contains the "index", not the "value" of the array
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= array.length; i++) {
            // we need a way of popping out all the elements in the stack at last,
            // so that we explicitly add a bar of height 0
            int cur = i == array.length ? 0 : array[i];
            while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
                int currHeight = array[stack.pollFirst()];
                // determine the left boundary of the largest rectangle
                // with height array[i]
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                // determine the right boundary of the largest rectangle
                // with height of the popped element
                // right boundary is i - 1
                int right = i - 1;
                int currArea = currHeight * (right - left + 1);
                result = Math.max(result, currArea);
            }
            stack.offerFirst(i);
        }
        return result;
    }

    // Method 1: 中心开花
    // for each index i {
    //    try using myself as the top bound
    //    中心开花，go left, go right, until we found a lower height
    //    calculate area with min(left, right) as height
    //  }
    //  return max of all area
    // Time = O(n^2)
    // Extra space = O(1)
    public int largestI(int[] array) {
        int maxArea = 0;
        // for each index i, try using array[i] as the top bound for the rectangle
        // need to find the leftmost border and rightmost border for the rectangle
        for (int i = 0; i < array.length; i++) {
            // find left border
            int left = i;
            while (left > 0 && array[left - 1] >= array[i]) {
                left--;
            }
            // find right border
            int right = i;
            while (right < array.length - 1 && array[right + 1] >= array[i]) {
                right++;
            }
            int width = right - left + 1;
            int currArea = width * array[i];
            maxArea = Math.max(maxArea, currArea);
        }
        return maxArea;
    }
}
