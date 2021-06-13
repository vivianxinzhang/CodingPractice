package com.company;

import java.util.Arrays;

public class TwoSumSmaller {
    public static void main(String[] args) {
        TwoSumSmaller s = new TwoSumSmaller();
        int[] array = new int[] {1, 2, 2, 4, 7};
        System.out.println(s.smallerPairs(array, 7));   // 6
    }

    // Method 2:
    // Time O(nlogn)
    // Space O(1)
    public int smallerPairs(int[] array, int target) {
        if (array == null || array.length < 2) {
            return 0;
        }
        Arrays.sort(array);
        int count = 0;
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int currSum = array[left] + array[right];
            if (currSum < target) {
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    // Method 1: brute force
    // Time O(n^2)
    // Space O(1)
    public int smallerPairsI(int[] array, int target) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] < target) {
                    count++;
                }
            }
        }
        return count;
    }
}
