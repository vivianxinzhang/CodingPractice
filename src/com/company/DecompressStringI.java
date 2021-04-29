package com.company;
import java.util.*;

public class DecompressStringI {
    public static void main(String[] args) {
        DecompressStringI s = new DecompressStringI();

        String input = "ac2d3ef";
        System.out.println(s.decompress(input));    // accdddef

        input = "acb2c4";
        System.out.println(s.decompress(input));    // acbbcccc
    }

    // Assumptions:
    // 1. The string is not null
    // 2. The characters used in the original string are guaranteed to be ‘a’ - ‘z’
    // 3. There are no adjacent repeated characters with length > 9
    // Time O(n)
    // Space O(n)
    public String decompress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        int length = countLength(input);
        char[] array = new char[length];
        int slow = array.length - 1;
        int fast = input.length() - 1;
        while (fast >= 0) {
            if (Character.isDigit(input.charAt(fast))) {
                int count = input.charAt(fast) - '0';
                while (count > 0) {
                    array[slow--] = input.charAt(fast - 1);
                    count--;
                }
                fast -= 2;
            } else {
                array[slow--] = input.charAt(fast--);
            }
        }
        return new String(array);
    }

    private int countLength(String input) {
        int length = 0;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                length += (input.charAt(i) - '0' - 1);
            } else {
                length++;
            }
        }
        return length;
    }

    // Time O(n)
    // Space O(n)
    public String decompressI(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (isLetter(input.charAt(i))){
                sb.append(input.charAt(i));
            } else {
                int count = input.charAt(i) - '0';
                while (count > 1) {
                    sb.append(input.charAt(i - 1));
                    count--;
                }
            }
        }
        return sb.toString();
    }


    private boolean isLetter(char input) {
        if (input >= 'a' && input <= 'z') {
            return true;
        }
        return false;
    }
}
