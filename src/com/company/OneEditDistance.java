package com.company;
import java.util.*;

public class OneEditDistance {
    // A Simple Solution is to find Edit Distance using Dynamic programming.
    // If distance is 1, then return true, else return false. Time complexity of this solution is O(n2)
    // An Efficient Solution is to simultaneously traverse both strings and keep track of count
    // of different characters. Below is complete algorithm.
    // Time O(n)
    // Space O(1)
    public boolean oneEditDistance(String source, String target) {
        // Find lengths of given strings
        int m = source.length(), n = target.length();
        // If difference between lengths is more than 1, then strings can't
        // be at one distance
        if (Math.abs(m - n) > 1) {
            return false;
        }
        int count = 0; // Count of edits
        int i = 0, j = 0;
        while (i < m && j < n) {
            // If current characters don't match
            if (source.charAt(i) != target.charAt(j)) {
                if (count == 1) {
                    return false;
                }
                // If length of one string is more, then only possible edit
                // is to remove a character
                if (m > n) {
                    i++;
                } else if (m < n) {
                    j++;
                } else {   // If lengths are the same, only possible edit is to replace
                    i++;
                    j++;
                }
                // Increment count of edits
                count++;
            } else {    // If current characters match
                i++;
                j++;
            }
        }

        // If last character is extra in any string
        if (i < m || j < n)
            count++;

        return count == 1;
    }
}
