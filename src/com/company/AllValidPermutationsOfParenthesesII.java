package com.company;
import java.util.*;

public class AllValidPermutationsOfParenthesesII {
    public static void main(String[] args) {
        AllValidPermutationsOfParenthesesII s = new AllValidPermutationsOfParenthesesII();
        System.out.println(s.validParentheses(1, 1, 0));
    }

    // Assumptions: l, m, n >= 0
    // Time O(6^2(m+n+l)*(m+n+l))     ←     O(6^2(m+n+l)*2(m+n+l))
    // Space O(m+n+l)    ←     stringbuilder 2(m+n+l) + recursion 层数 2(m+n+l)
    private static final char[] PS = new char[] {'(', ')', '<', '>', '{', '}'};
    public List<String> validParentheses(int l, int m, int n) {
        List<String> result = new ArrayList<>();
        char[] array = new char[2 * (l + m + n)];
        int[] count = new int[] {l, l, m, m, n, n};
        Deque<Character> stack = new ArrayDeque<>();
        dfs(array, 0, count, stack, result);
        return result;
    }

    private void dfs(char[] array, int index, int[] count, Deque<Character> stack, List<String> result) {
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        for (int i = 0; i < PS.length;i ++) {
            // left parentheses
            if (i % 2 == 0 && count[i] > 0) {
                array[index] = PS[i];
                stack.offerFirst(PS[i]);
                count[i]--;
                dfs(array, index + 1, count, stack, result);
                count[i]++;
                stack.pollFirst();
            } else if (count[i] > 0 && (!stack.isEmpty() && stack.peekFirst() == PS[i - 1])) {    // right parentheses
                array[index] = PS[i];
                stack.pollFirst();
                count[i]--;
                dfs(array, index + 1, count, stack, result);
                count[i]++;
                stack.offerFirst(PS[i - 1]);
            }
        }
    }
}
