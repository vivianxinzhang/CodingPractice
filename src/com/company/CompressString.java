package com.company;

public class CompressString {
    public static void main(String[] args) {
        CompressString s = new CompressString();
        System.out.println(s.compress("a"));

        System.out.println(s.compress("cccc"));

        System.out.println(s.compress("abbcccdeee"));   // "ab2c3de3"
    }

    // Time O(n)
    // Space O(n)
    public String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return encode(array);
    }

    private String encode(char[] array) {
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int beginIdx = fast;
            while (fast < array.length && array[fast] == array[beginIdx]) {
                fast++;
            }
            array[slow++] = array[beginIdx];
            if (fast - beginIdx != 1) {
                int digitLength = copyDigits(array, slow, fast - beginIdx);
                slow += digitLength;
            }
        }
        return new String(array, 0, slow);
    }

    private int copyDigits(char[] array, int start, int count) {
        int numOfDigits = 0;
        for (int i = count; i > 0; i /= 10) {
            numOfDigits++;
        }
        int end = start + numOfDigits - 1;
        for (int i = end; i >= start; i--) {
            array[i] = (char)(count % 10 + '0');
            count /= 10;
        }
        return numOfDigits;
    }
}
