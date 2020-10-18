package com.company;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String input = "   I     love MTV   ";
        char[] array = new char[] {'a', 'b', 'c'};
        char[] newArray = Arrays.copyOf(array, 10);
        System.out.println(Arrays.toString(newArray));
    }

    public String rightShift(String input, int k) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        k %= input.length();
        char[] array = input.toCharArray();
        reverse(array, 0, array.length - 1);
        reverse(array, 0, k - 1);
        reverse(array, k, array.length - 1);
        return new String(array);
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