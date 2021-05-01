package com.company;
import java.util.*;

public class FlipGameII {
    public static void main(String[] args) {
        FlipGameII s = new FlipGameII();

        String input = "++++";
        System.out.print("++++ ");
        System.out.println(s.canWin(input));    // true

        input = "+++----+++";
        System.out.print("+++----+++ ");
        System.out.println(s.canWin(input));    // false

        input = "+++++++++";
        System.out.print("+++++++++ ");
        System.out.println(s.canWin(input));    // false

        input = "+-+";
        System.out.print("+-+ ");
        System.out.println(s.canWin(input));    // false
    }

    // Assume input != null && input.length() >= 2
    // M[i][j] represents whether I can win if I flip first
    // Method 4: dp


    // Method 3: dfs
    // Suppose originally the board of size N contains only '+' signs, then roughly we have:
    // T(N): total ways to flip the string of length N. Assume the original string is "+++++++++".
    // Move the "--" from left to write you will get: "--+++++++" (T(N - 2)), "+--++++++" (T(N - 3)),
    // "++--+++++"(T(2) * T(N - 4)), "+++--++++"(T(3) * T(N - 5)), .......,"++++++--+"(T(N - 3)), "+++++++--" T(N - 2)
    // When a string separated by "--", e.g., "+++--++++", the time complexity is T(3) * T(N - 5),
    // not T(N - 2). Note, T(3)*T(N - 5) < T(N - 2). So, the correct equation should be (we need to test every flip strategy)
    // T(N) = T(N-2) + T(N-3) + (T(2) * T(N-4)) + (T(3) * T(N-5)) + ... (T(N-5) * T(3)) + (T(N-4) * T(2)) + T(N-3) + T(N-2)
    //      = 2 * sum(T[i])  (i = 3..N-2)
    // You will find that T(N) = 2^(N-1) satisfies the above equation.
    // Therefore, this algorithm is at least exponential.
    // Time O((n!)
    // Space O(n)
    public boolean canWin(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        char[] array = input.toCharArray();
        return canWin(array);
    }

    private boolean canWin(char[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == '+' && array[i + 1] == '+') {
                array[i] = '-';
                array[i + 1] = '-';
                boolean otherCanWin = canWin(array);
                array[i] = '+';
                array[i + 1] = '+';
                // return need be the last line, cause need to recovery to previous state for other recursive calls to canWin();
                // if this line happens before the above 2 line, the state is not recovered during this recursive call.
                if (!otherCanWin) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method 2: recursion with memo
    // Time O(n^2*n)
    // Space O(n^2)
    public boolean canWinII(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        Map<String, Boolean> map = new HashMap<>();
        return canWinHelper(input, map);
    }

    private boolean canWinHelper(String input, Map<String, Boolean> map) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
//                char[] array = input.toCharArray();
//                array[i] = '-';
//                array[i + 1] = '-';
//                String nextState = new String(array);
                String nextState = input.substring(0, i) + "--" + input.substring(i + 2);
                if (!canWinHelper(nextState, map)) {
                    map.put(input, true);
                    return true;
                }
            }
        }
        map.put(input, false);
        return false;
    }

    // Method 1: recursion
    // each level flip 2, n/2 levels
    // Time O(n!*n)
    // Space O(n^2)
    public boolean canWinI(String input) {
        if (input == null || input.length() < 2) {
            return false;
        }
        for (int i = 0; i < input.length() - 1; i++) {
            if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
                char[] array = input.toCharArray();
                array[i] = '-';
                array[i + 1] = '-';
                String nextState = new String(array);
                if (!canWin(nextState)) {
                    return true;
                }
            }
        }
        return false;
    }
}
