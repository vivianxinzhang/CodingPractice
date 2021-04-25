package com.company;

public class ReplacementsOfAAndB {
    public static void main(String[] args) {
        ReplacementsOfAAndB s = new ReplacementsOfAAndB();
        String input = "ba";
        System.out.println(s.minReplacements(input));   // 1

        input = "abaab";
        System.out.println(s.minReplacements(input));   // 1

        input = "abbabbaab";
        System.out.println(s.minReplacements(input));   // 3
    }

    // Assumptions:
    // The given string is not null.
    // Time O(n)
    // Space O(n)
    public int minReplacements(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        // aaaaa bbbbb
        //       i
        // i is the index for the first b after replacement
        // range of i is [0, input.length]
        int[] leftReplaceBToA = getLeftReplaceBToA(input);  // b on left
        int[] rightReplaceAToB = getRightReplaceAToB(input);    // a on right
        int globalMin = Integer.MAX_VALUE;
        // i is the first char of part bs
        for (int i = 0; i <= input.length(); i++) {
            int currMin = getNumber(leftReplaceBToA, i - 1) + getNumber(rightReplaceAToB, i);
            globalMin = Math.min(currMin, globalMin);
        }
        return globalMin;
    }

    private int getNumber(int[] array, int i) {
        if (i < 0 || i >= array.length) {
            return 0;
        }
        return array[i];
    }

    // M[i] represents num of Bs on left of i,  including i
    // Base case:
    // M[0] = 0
    // Induction rule:
    // M[i] = M[i-1] + 1, if input.charAt(i-1) == 'b'
    //        M[i-1]      otherwise
    private int[] getLeftReplaceBToA(String input) {
        int[] M = new int[input.length()];
        M[0] = input.charAt(0) == 'b' ? 1 : 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == 'b') {
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
    private int[] getRightReplaceAToB(String input) {
        int[] M = new int[input.length()];
        int n = input.length();
        M[n - 1] = input.charAt(n - 1) == 'a' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (input.charAt(i) == 'a') {
                M[i] = M[i + 1] + 1;
            } else {
                M[i] = M[i + 1];
            }
        }
        return M;
    }
}
