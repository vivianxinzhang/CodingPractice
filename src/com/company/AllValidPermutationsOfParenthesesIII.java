package com.company;
import java.util.*;

public class AllValidPermutationsOfParenthesesIII {
    public static void main(String[] args) {
        AllValidPermutationsOfParenthesesIII s = new AllValidPermutationsOfParenthesesIII();
        System.out.println(s.validParenthesesIII(1, 1, 0));
        // [()<>, <()>, <>()]
        System.out.println(s.validParenthesesIII(2, 0, 1));
        // [()(){}, (){()}, (){}(), {()()}, {()}(), {}()()]
    }

    // Assumptions:
    // 1. l, m, n >= 0
    // 2. l + m + n > 0
    // Time O(6^2(m+n+l)*(m+n+l))     ←     O(6^2(m+n+l)*2(m+n+l))
    // Space O(m+n+l)    ←     stringbuilder 2(m+n+l) + recursion 层数 2(m+n+l)
    // (): round brackets  <>: angle brackets  {}: curly brackets  []: square brackets
    private static final char[] PS = new char[] {'(', ')', '<', '>', '{', '}'};
    public List<String> validParenthesesIII(int l, int m, int n) {
        List<String> result = new ArrayList<>();
        char[] array = new char[2 * (l + m + n)];
        int[] count = new int[] {l, l, m, m, n, n};
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(array, 0, count, stack, result);
        return result;
    }

    private void dfs(char[] array, int index, int[] count, Deque<Integer> stack, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        for (int i = 0; i < PS.length;i ++) {
            // left parentheses
            // index i can be used to represent priority
            // small i represents lower priority and can be added after high priority parenthesis
            // stack.peekFirst() is the priority of the last added left parenthesis
            // must be higher than the next added left parenthesis
            if (i % 2 == 0 && count[i] > 0) {
                if (stack.isEmpty() || stack.peekFirst() > i) {
                    array[index] = PS[i];
                    stack.offerFirst(i);
                    count[i]--;
                    dfs(array, index + 1, count, stack, result);
                    count[i]++;
                    stack.pollFirst();
                }
            } else if (count[i] > 0) {    // right parentheses
                // can only add right bracket when last added left bracket is the the same pair
                // i - 1 represents the same pair left bracket
                if ((!stack.isEmpty() && stack.peekFirst() == i - 1)) {
                    array[index] = PS[i];
                    stack.pollFirst();
                    count[i]--;
                    dfs(array, index + 1, count, stack, result);
                    count[i]++;
                    stack.offerFirst(i - 1);
                }
            }
        }
    }
}
