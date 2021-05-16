package com.company;

public class ReOrderStringII {
    public static void main(String[] args) {
        ReOrderStringII s = new ReOrderStringII();

        System.out.println(s.reorderStringII("ABCD1234"));
        // A1B2C3D4
        System.out.println(s.reorderStringII("ABC123"));
        // A1B2C3
        System.out.println(s.reorderStringII("AB12"));
        // A1B2
        System.out.println(s.reorderStringII("A1"));
        // A1
    }

    /**
     * Convert string “ABCD1234” to “A1B2C3D4”.
     * Please do it in place（In place means do it with the original input string rather than use an additional string).
     * size = r - l + 1
     * m = l + size / 2
     * lm = l + (size/2) / 2 = l + size/4
     * rm = m + length(chunk 1) = m + size/4 = l + size * 3 / 4
     * */
    // Time O(nlogn)
    // Space O(logn)
    public String reorderStringII(String input) {
        if (input == null || input.length() <= 2) {
            return input;
        }
        char[] array = input.toCharArray();
        char[] helper = new char[input.length()];
        mergeSort(array, helper, 0, array.length - 1);
        return new String(array);
    }

    private void mergeSort(char[] array, char[] helper, int left, int right) {
        int size = right - left + 1;    // size = 6
        if (size <= 2) {
            return;
        }
        // A |  B   C | 1 | 2   3
        // 0    1   2   3   4   5    index
        // l   lm   m       rm
        // A |  1 | B   C | 2   3
        // size = r - l + 1
        // m = l + size / 2
        // lm = l + (size/2) / 2 = l + size/4
        // rm = m + length(chunk 1) = m + size/4 = l + size * 3 / 4
        int mid = left + (right - left) / 2;    // mid = 2
        int leftMid = left + size / 4;          // lm = 0 + 6 / 4  = 1
        int rightMid = left + size * 3 / 4;     // rm = 0 + 6 * 3 / 4  = 4
        reverse(array, leftMid, mid);
        reverse(array, mid + 1, rightMid - 1);
        reverse(array, leftMid, rightMid - 1);
        mergeSort(array, helper, left, left + (leftMid - left) * 2 - 1);
        mergeSort(array, helper, left + (leftMid - left) * 2 , right);
    }

    private void reverse(char[] array, int i, int j) {
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}
