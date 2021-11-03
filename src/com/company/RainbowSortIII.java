package com.company;

import java.util.Arrays;

public class RainbowSortIII {
    public static void main(String[] args) {
        RainbowSortIII s = new RainbowSortIII();

        int[] array = new int[] {1, 3, 1, 2};
        System.out.println(Arrays.toString(s.rainbowSortIII(array, 3)));
        // [1, 1, 2, 3]
        array = new int[] {3, 1, 5, 5, 1, 4, 2};
        System.out.println(Arrays.toString(s.rainbowSortIII(array, 5)));
        // [1, 1, 2, 3, 4, 5, 5]
    }

    // rainbow sort k colors:
    // Assumptions:
    // 1. The input array is not null.
    // 2. k is guaranteed to be >= 1.
    // 3. k << logn.
    // Method 1:
    // sort two colors each time until all the colors are sorted
    //          leftColor and rightColor
    // 1st round     1    and    k
    // 2nd round     2    and    k - 1
    // 3rd           3    and    k - 2
    //                    ...
    // Time O(nk)
    // Space O(k)
    public int[] rainbowSortIV(int[] array, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        int left = 0;
        int right = array.length - 1;
        int leftColor = 1;
        int rightColor = k;
        // before each iteration: [left, right] is the unexplored area
        // in the loop
        // Case 1: leftColor, swap with left boundary, then left++ (leftColor should at left - 1)
        // Case 2: rightColor, swap with right boundary, then right-- (should be at right + 1)
        while (leftColor < rightColor) {
            for (int i = left; i <= right; i++) {
                if (array[i] == leftColor) {
                    swap(array, left, i);
                    left++;
                } else if (array[i] == rightColor) {
                    swap(array, i, right);
                    right--;
                }
            }
            leftColor++;
            rightColor--;
        }
        return array;
    }

    // Method 2:
    // Time O(nk)
    // Space O(k)
    public int[] rainbowSortIII(int[] array, int k) {
        int[] indices = new int[k];
        while (indices[k - 1] <= array.length - 1) {
            int index = array[indices[k - 1]] - 1;
            for (int i = k - 2; i >= index; i--) {
                swap(array, indices[i], indices[i + 1]);
                indices[i + 1]++;
            }
//            for (int i = index; i <= k - 1; i++) {
//                indices[i]++;
//            }
            indices[index]++;
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
