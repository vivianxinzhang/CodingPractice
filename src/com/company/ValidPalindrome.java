package com.company;

public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome s = new ValidPalindrome();

        System.out.println(s.valid("FQJKY$jB3Qd-fSOUAI`^iAUOSfdQ3BjyKJQF"));   // true

        System.out.println(s.valid("an apple, :) elp pana#"));  // true
        System.out.println(s.valid("dia monds dn dia"));        // false

    }

    // Time O(n)
    // Space O(1)
    public boolean valid(String input) {
        // Write your solution here
        input = input.toLowerCase();
        return valid(input, 0, input.length() - 1);
    }

    private boolean valid(String input, int i, int j) {
        while (i < j) {
            while (i < j && !alphanumeric(input.charAt(i))) {
                i++;
            }
            while (i < j && !alphanumeric(input.charAt(j))) {
                j--;
            }
            if (input.charAt(i) != input.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean alphanumeric(Character a) {
        return Character.isDigit(a) || Character.isAlphabetic(a);
    }
}
