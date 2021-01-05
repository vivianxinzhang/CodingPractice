package com.company;

public class ReplacementsOfAAndB {
    public static void main(String[] args) {
        ReplacementsOfAAndB s = new ReplacementsOfAAndB();
        String input = "abaab";
        System.out.println(s.minReplacements(input));
    }

    // Time O(n)
    // Space O(n)
    public int minReplacements(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        int[] bOnLeft = numOfBLeft(input);
        int[] aOnRight = numOfARight(input);
        // aaaaa bbbbb
        //       i
        // i is the index for the first b after replacement
        // range of i is [0, input.length]
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= input.length(); i++) {
            int curr = bOnLeft[i] + aOnRight[i];
            min = Math.min(min, curr);
        }
        return min;
    }

    // M[i] represents num of Bs on left of i, not including i
    // M[i] represents num of Bs on left of i - 1, including i - 1
    // Base case:
    // M[0] = 0
    // Induction rule:
    // M[i] = M[i-1] + 1, if input.charAt(i-1) == 'b'
    //        M[i-1]      otherwise
    private int[] numOfBLeft(String input) {
        int[] M = new int[input.length() + 1];
        M[0] = 0;
        for (int i = 1; i <= input.length(); i++) {
            if (input.charAt(i - 1) == 'b') {
                M[i] = M[i - 1] + 1;
            } else {
                M[i] = M[i - 1];
            }
        }
        return M;
    }

    // M[i] represents num of As on right of i, including i
    // Base case:
    // M[input.length + 1] = 0;
    // Induction rule:
    // M[i] = M[i + 1] + 1, if input.charAt(i) == 'a'
    //        M[i + 1],     otherwise
    private int[] numOfARight(String input) {
        int[] M = new int[input.length() + 1];
        M[input.length()] = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == 'a') {
                M[i] = M[i + 1] + 1;
            } else {
                M[i] = M[i + 1];
            }
        }
        return M;
    }
}
