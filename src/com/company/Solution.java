package com.company;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println();
    }




    public String removeSpaces(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if ((fast == 0 || array[fast - 1] == ' ') && array[fast] == ' ') {
                fast++;
            } else {
                array[slow++] = array[fast++];
            }
        }
        if (slow > 0 && array[slow - 1] == ' ') {
            slow--;
        }
        return new String(array, 0, slow);
    }
}
