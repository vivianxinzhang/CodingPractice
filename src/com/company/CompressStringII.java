package com.company;

public class CompressStringII {
    public static void main(String[] args) {
        CompressStringII s = new CompressStringII();
        System.out.println(s.compress("a"));

        System.out.println(s.compress("cccc"));

        System.out.println(s.compress("abbcccdeee"));   // "a1b2c3d1e3"
    }

    // Time O(n)
    // Space O(1)
    public String compress(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        return encode(array);
    }

    private String encode(char[] input) {
        // Step 1: deal with the cases where the adjacent occurrence of the letters >= 2
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while (fast < input.length) {
            int begin = fast;
            // count number of repetitive char
            while (fast < input.length && input[fast] == input[begin]) {
                fast++;
            }
            // copy char
            input[slow++] = input[begin];
            if (fast - begin == 1) {
                newLength += 2;
            } else {
                int len = copyDigits(input, slow, fast - begin);
                slow += len;
                newLength += len + 1;
            }
        }
        // Step 2: deal with the cases where the adjacent occurrence of the letters == 1
        // Notice: if it is required to do this in place, usually the input array is a
        // sufficient large one, you will not need to allocate a new array.
        char[] result = new char[newLength];
        // [0, slow) are all processed
        // when go backward, should start at index slow - 1
        fast = slow - 1;
        slow = newLength - 1;
        // each iteration copy a pattern
        while (fast >= 0) {
            if (Character.isDigit(input[fast])) {
                // Case 1: pattern starts with number --> already compressed
                // copy all numbers
                while (fast >= 0 && Character.isDigit((input[fast]))) {
                    result[slow--] = input[fast--];
                }
            } else {   // Case 2: pattern starts with letter --> only one letter case
                result[slow--] = '1';
            }
            // copy letter
            result[slow--] = input[fast--];
        }
        return new String(result);
    }

    // copy "count" as digits into "input", starting at "index"
    private int copyDigits(char[] input, int index, int count) {
        int len = 0;
        for (int i = count; i > 0; i /= 10) {
            index++;
            len++;
        }
        for (int i = count; i > 0; i /= 10) {
            int digit = i % 10;
            input[--index] = (char)('0' + digit);
        }
        return len;
    }
}
