package com.company;

public class ShortestSubstringWithKTypedCharacters {
    public static void main(String[] args) {
        ShortestSubstringWithKTypedCharacters s = new ShortestSubstringWithKTypedCharacters();

        String input = "";
        System.out.println(s.shortest(input, 3));   // ""

        input = "aaaaabbdaaaacccdddddeeffbbbbbbf";
        // "aaaaabbdaaaacc cdddddeeffb bbbbbf"
        System.out.println(s.shortest(input, 5));   // "cdddddeeffb"

        input = "aabbbcccc";
        System.out.println(s.shortest(input, 3));   // "abbbc"

        input = "aabcc";
        System.out.println(s.shortest(input, 3));   // "abc"
    }

    // Method 2: linear scan
    // Time O(n)
    // Space O(n)
    public String shortest(String s, int k) {
        if (k == 0) {
            return "";
        }

        // Associative array to store the count of characters
        int count[] = new int[26];

        int currStart = 0;
        int currEnd = 0;

        // Also initialize values for result longest window
        int minLength = Integer.MAX_VALUE;
        int globalLeft = 0;
        int globalRight = 0;

        // Start from the second character and add characters in window according to above explanation
        while (currEnd < s.length()) {
            // Add the character 's[i]' to current window
            if (countDiffChar(count) < k) {
                count[s.charAt(currEnd) - 'a']++;
                currEnd++;
            } else { // If there are k unique characters in current window, remove from left side
                count[s.charAt(currStart) - 'a']--;
                currStart++;
            }

            // Update the max window size if required
            if (countDiffChar(count) == k ) {
                char charLeft = s.charAt(currStart);
                while (currStart < s.length() && s.charAt(currStart + 1) == charLeft) {
                    count[s.charAt(currStart) - 'a']--;
                    currStart++;
                }
                if (currEnd- currStart + 1 < minLength) {
                    minLength = currEnd - currStart + 1;
                    globalLeft = currStart;
                    globalRight = currEnd;
                }
            }
        }
        return s.substring(globalLeft, globalRight);
    }

    private int countDiffChar(int[] count) {
        int num = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                num++;
            }
        }
        return num;
    }
}
