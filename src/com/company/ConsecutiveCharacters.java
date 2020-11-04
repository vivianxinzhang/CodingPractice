package com.company;

public class ConsecutiveCharacters {
    // Given a string s, the power of the string is the maximum length of a non-empty substring
    // that contains only one unique character. Return the power of the string.
    public static void main(String[] args) {
        ConsecutiveCharacters s = new ConsecutiveCharacters();
        String input = "leetcode";
        System.out.println(s.maxPower(input));  // 2

        input = "abbcccddddeeeeedcba";
        System.out.println(s.maxPower(input));  // 5

        input = "triplepillooooow";
        System.out.println(s.maxPower(input));  // 5

        input = "hooraaaaaaaaaaay";
        System.out.println(s.maxPower(input));  // 11
    }

    public int maxPower(String s) {
        int count = 0;
        int maxCount = 0;
        char previous = ' ';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == previous) {
                // if same as previous one, increase the count
                count++;
            } else {
                // else, reset the count
                count = 1;
                previous = c;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
