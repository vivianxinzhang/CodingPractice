package com.company;

public class MinimumCutsForPalindromes {
    public static void main(String[] args) {
        MinimumCutsForPalindromes s = new MinimumCutsForPalindromes();

        System.out.println(s.minCuts("aba"));    // 0
        System.out.println(s.minCuts("ababbbab"));    // 1
        //                                  a | babbbab
        System.out.println(s.minCuts("aaaaaabbabb"));    // 1
        //                                  aaaaaa | bbabb
        System.out.println(s.minCuts("ababbbabbababa"));    // 3
    }

    // Assumptions:
    // The given string is not null
    // Method 1:
    // Time O(n^3) bottle neck在for loop, two nested outside for loop each takes O(n),
    // for loop 里的 palindrome check takes O(n), total should be O(n^3)
    // Space O(n) char[] array takes O(n) extra space
    public int minCuts(String input) {
        // Corner case
        if (input == null || input.length() <= 1) {
            return 0;
        }
        // M[i] represents minimum # of cuts needed to make substring[0, i] a palindrome partition
        char[] array = input.toCharArray();
        int[] M = new int[input.length()];
        // Base case: dp[0] = 0
        M[0] = 0;
        // Induction rule:
        for (int i = 1; i < input.length(); i++) {
            M[i] = Integer.MAX_VALUE;
            // case 1: if substring[0, i] is palindrome: dp[i] = 0
            if (isPalindrome(array, 0, i)) {
                M[i] = 0;
                continue;   // use continue instead of break in outside loop when [0, i] is already a palindrome
            }
            // case 2: find right most "cut" positions
            // for j = 1 --> i, 左大查表[0, j-1] + 右小[j, i] (不可分割的最右侧一段)
            // j is the index of the first letter of rightmost part
            // left part: [0,j-1]
            // right part: [j, i] j可能取到 [1, … , i] 对应右段 [1,...,i]...[i,i]
            // check left part from M, check right part[j, i] using helper function
            // 小问题的解 M[j - 1] represents min # of cuts needed to make substring[0, j-1] a palindrome partition
            // if substring[j, i] is palindrome: dp[i] = min(dp[j-1] +1) for all possible j
            for (int j = 1; j <= i; j++) {
                if (isPalindrome(array, j, i)) {
                    M[i] = Math.min(M[i], M[j - 1] + 1);
                }
            }
        }
        return M[array.length - 1];
    }

    // check whether is palindrome between [left, right]
    public boolean isPalindrome(char[] array, int left, int right) {
        while (left < right) {
            if (array[left] != array[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // trade off between time and space
    // Step 1: find all palindromes --> O(n^2)
    // Step 2: DP , linear scan and look back --> O(n^2)
    // T: O(n^2)
    // S: O(n^2) + O(n)
}