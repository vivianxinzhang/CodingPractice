package com.company;

public class DecompressStringII {
    // Method 1: "in place"
    // When we say in place, it usually means the input is a long enough char array,
    // and the original string only occupies part of the array starting from index 0,
    // and usually the length to represent the original string is given.
    // Assumptions
    // 1. The string is not null
    // 2. The characters used in the original string are guaranteed to be 'a' - 'z'
    // 3. There are no adjacent repeated characters with length > 9
    // Time O(n)
    // Space O(1)
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
}
