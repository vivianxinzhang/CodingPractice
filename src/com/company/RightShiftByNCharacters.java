package com.company;

public class RightShiftByNCharacters {
    public static void main(String[] args) {
        RightShiftByNCharacters s = new RightShiftByNCharacters();

        System.out.println(s.rightShift("abc", 4));     // cab
    }

    // Assumptions:
    // 1. The given string is not null.
    // 2. n >= 0.
    // Time O(n)
    // Space O(n)
    public String rightShift(String input, int k) {
        if (input == null || input.length() <= 1 || k == 0) {
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
