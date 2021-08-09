package com.company;

import java.util.Arrays;

public class ReOrderArray {
    public static void main(String[] args) {
        ReOrderArray s = new ReOrderArray();

        int[] array = new int[] {1, 2, 3, 4};
        System.out.println(Arrays.toString(s.reorder(array)));
        // [1, 3, 2, 4]

        array = new int[] {1, 2, 3};
        System.out.println(Arrays.toString(s.reorder(array)));
        //[1, 2, 3]

        array = new int[] {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(s.reorder(array)));
        // [1, 4, 2, 5, 3, 6, 7]

        array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        System.out.println(Arrays.toString(s.reorder(array)));
        // [1, 8, 2, 9, 3, 10, 4, 11, 5, 12, 6, 13, 7, 14]
    }

    /**
     * Array reorder in place implementation
     *
     * Suppose I have a array of chars, the requirement is as follow
     * 1). [c1, c2, ... c2k]
     * --> [c1, ck+1, c2, ck+2, ... , ck, c2k]
     * 2). [c1, c2, ... c2k]
     * --> [c1, ck+1, c2, ck+2, ... , ck, c2k, c2k+1]
     */
    // Assumptions: The given array is not null
    // size = r - l + 1
    // m = l + size / 2
    // lm = l + (size/2) / 2 = l + size/4
    // rm = m + length(chunk 1) = m + size/4 = l + size * 3 / 4
    // Assumptions:
    // The given array is not null
    // Time O(nlogn)
    // Space O(logn)
    public int[] reorder(int[] array) {
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorder(int[] array, int left, int right) {
        int length = right - left + 1;
        // if the subarray has 2 or 0 elements, we can just return
        // as this should be the base case
        if (length <= 2) {
            return;
        }
        // calculate the important mid points
        // 0 1 2 3 4 5 6 7
        // lm: 2, m: 4, rm: 6
        // 0 1 2 3 4 5 6 7 8 9
        // lm: 2, m: 5, rm: 7
        // mid is the last element index of left 1 / 2
        int mid = left + length / 2;
        // lmid is the first element of second 1 / 4
        int lmid = left + length / 4;
        // lmid is the first element of fourth 1 / 4
        int rmid = left + length * 3 / 4;
        reverse(array, lmid, mid - 1);
        reverse(array, mid, rmid - 1);
        reverse(array, lmid, rmid - 1);
        // half of the left partition's size = lmid - left
        reorder(array, left, left + (lmid - left) * 2 - 1);
        reorder(array, left + (lmid - left) * 2, right);
    }

    public void reverse(int[] array, int i, int j) {
        while (i < j) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}
