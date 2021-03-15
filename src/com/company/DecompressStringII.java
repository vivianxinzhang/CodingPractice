package com.company;
import java.util.*;

public class DecompressStringII {
    public static void main(String[] args) {
        DecompressStringII s = new DecompressStringII();
        System.out.println(s.decompress("a1"));     // a

        System.out.println(s.decompress("a1b3"));   // abbb
    }

    // Method 1: "in place"
    // When we say in place, it usually means the input is a long enough char array,
    // and the original string only occupies part of the array starting from index 0,
    // and usually the length to represent the original string is given.
    // Assumptions
    // 1. The string is not null
    // 2. The characters used in the original string are guaranteed to be 'a' - 'z'
    // 3. There are no adjacent repeated characters with length > 9
    // Time O(n)
    // Space O(n)
    public String decompress(String input) {
        if (input.isEmpty()) {
            return input;
        }
        char[] array = input.toCharArray();
        // we need to handle the
        // 'a0', 'a1', 'a2' case (the decoded string is shorter) and
        // 'a3', 'a4', ... case (the decoded string is longer)
        // in two pass to avoid conflict, since the encoding of the two cases
        // require different directions.
        return decodeLong(array, decodeShort(array));
    }

    // case of 0, 1, 2 chars, do not need more space, decode from left to right
    // a0 -> ""
    // a1 -> "a"
    // a2 --> "aa"
    // return length of the decoded string (the position of end pointer)
    // when decodeLong, start from end - 1
    // [0, end) is the processed part
    // return the length of the decoded string,
    // only cares about 'a0', 'a1', 'a2', A.K.A the decoded string is shorter
    private int decodeShort(char[] input) {
        // since the decoded string is shorter, we should
        // do the decoding work from left to right direction
        int end = 0;
        for (int i = 0; i < input.length; i += 2) {
            int digit = getDigit(input[i + 1]);
            if (digit >= 0 && digit <= 2) {
                // copy chars less than 2
                for (int j = 0; j < digit; j++) {
                    input[end++] = input[i];
                }
            } else {
                // copy two indices
                // we don't handle the longer decoded string here
                input[end++] = input[i];
                input[end++] = input[i + 1];
            }
        }
        return end;
    }

    // case of >2 chars, need more space, decode from right to left
    // need to know
    // 1. the end of decodeShort short
    // 2. the length of new String for decode long
    // a3 -> "aaa"
    // decode [0, length - 1]
    // decodeLong: take care of "a3", "a4", "a5", ... the decoded string is longer
    // length: the length of the valid partition starting from index 0
    private String decodeLong(char[] input, int length) {
        // we need to calculate the new required length
        int newLength = length;
        for (int i = 0; i < length; i++) {
            // char is a number
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                newLength += digit - 2;
            }
        }
        // Notice: if it is required to do this in place, usually the input
        // array is a sufficient large one, you will not need to allocate a new array
        // This solution is only for demonstration.
        char[] result = new char[newLength];
        int end = newLength - 1;
        for (int i = length - 1; i >= 0; i--) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                i--;
                for (int j = 0; j < digit; j++) {
                    result[end--] = input[i];
                }
            } else {
                // we already take care the shorter cases, 'a1' in previous pass.
                // we can leave as it is. e.g. the input could be 'abc2'
                result[end--] = input[i];
            }
        }
        return new String(result);
    }

    private int getDigit(char digit) {
        return digit - '0';
    }

    /**
      * another implementation of decodeLong
     case of >2 chars, need more space, decode from right to left
     need to know
     1. the end of decodeShort short
     2. the length of new String for decode long
     a3 -> "aaa"
      */
    private String decodeLongI(char[] array, int length) {
        int newLength = getNewLength(array, length);
        char[] result = Arrays.copyOf(array, newLength);
        int slow = newLength - 1;
        int fast = length - 1;
        // decode numbers
        while (fast >= 0) {
            if (Character.isDigit(array[fast])) {
                int count = array[fast] - '0';
                char c = array[fast - 1];
                while (count > 0) {
                    result[slow--] = c;
                    count--;
                }
                fast -= 2;
            } else {
                result[slow--] = array[fast--];
            }
        }
        return new String(result);
    }

    // ab4c5 -> 5 + (4-2) + (5-2)
    // b4 2 position -> bbbb  4 position: diff 4 - 2
    // c5 2 position -> ccccc 5 position: diff 5 - 2
    private int getNewLength(char[] array, int length) {
        int newLength = length;
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(array[i])) {
                int digit = array[i] - '0';
                newLength += digit - 2;
            }
        }
        return newLength;
    }
}
