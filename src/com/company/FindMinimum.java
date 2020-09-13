package com.company;
import java.util.*;

// Time O(logn)
// Space O(1)
public class FindMinimum {
    public int localMinimum(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0, right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < array[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return array[left] < array[right] ? left : right;
    }

    public static void main(String[] args) {
        FindMinimum s = new FindMinimum();

        int[] array = new int[]{};
        System.out.println(s.localMinimum(array));

        array = new int[]{1, 2, 3};
        System.out.println(s.localMinimum(array));

        array = new int[]{3, 2, 1};
        System.out.println(s.localMinimum(array));

        array = new int[]{3, 1, 2, 4};
        System.out.println(s.localMinimum(array));
    }
}
