package com.company;
import java.util.*;

public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        DifferentWaysToAddParentheses s = new DifferentWaysToAddParentheses();

        System.out.println(s.diffWaysToCompute("2-1-1"));
        // [0, 2]
        System.out.println(s.diffWaysToCompute("2*3-4*5"));
        // [-34, -14, -10, -10, 10]
        System.out.println(s.diffWaysToCompute("2*3-4*5*2+2*2"));
        // expected:<[-314, -314, -314, -314, -314, -308, -308, -308, -308, -272, -272, -272, -272, -272, -234, -234, -204, -204, -186, -186, -180, -180, -162, -162, -156, -156, -156, -156, -154, -154, -154, -154, -154, -148, -148, -144, -144, -140, -140, -140, -140, -132, -132, -128, -128, -114, -114, -112, -112, -106, -90, -90, -84, -84, -82, -82, -80, -80, -80, -80, -80, -80, -80, -80, -80, -80, -80, -80, -80, -80, -78, -78, -72, -72, -70, -70, -66, -66, -64, -64, -64, -64, -60, -60, -60, -60, -60, -60, -52, -50, -48, -48, -48, -48, -48, -38, -38, -36, -36, -36, -36, -36, -32, -32, -32, -32, -30, -30, -28, -28, -24, -16, -16, -16, -16, -16, -12, -12, 24, 24, 28, 44, 44, 48, 48, 60, 60, 80, 80, 80, 80, 80]> but was:<[-156, -156, -156, -156, -152, -152, -146, -146, -144, -144, -140, -140, -140, -140, -140, -140, -122, -122, -116, -116, -82, -82, -78, -78, -76, -72, -72, -72, -72, -70, -70, -66, -66, -64, -64, -60, -58, -58, -52, -48, -48, -48, -48, -44, -44, -44, -44, -44, -42, -38, -38, -32, -32, -32, -32, -32, -32, -30, -30, -28, -28, -24, -24, -24, -24, -24, -20, -20, -18, -12, -12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 16, 16, 20, 20, 20, 20, 20, 28, 32, 32, 36, 36, 46, 46, 68, 68, 86, 86]>
    }

    // Method 2: Recursion with Memo:
    //         ways("2-1-1")
    // Case 1: ways("2") X ways("1-1") = 2 - 0 = 2
    // Case 2: ways("2 - 1") X ways("1") = 1 - 1 = 0
    // return: {0, 2}
    //         ways("2*3-4*5")
    // Case 1: ways("2") X ways("3-4*5") = 2 - 0 = 2
    // Case 2: ways("2*3") X ways("4*5") = 1 - 1 = 0
    // Case 3: ways("2*3-4") X ways("5") = 1 - 1 = 0
    // return: {0, 2}
    // Time O(n^2)
    // Space O(n^2)
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> res = ways(input, map);
        Collections.sort(res);
        return res;
    }

    private List<Integer> ways(String input, Map<String, List<Integer>> map) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        // 枚举所有操作符作为分界点
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isOperator(ch)) {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftRes = ways(left, map);
                List<Integer> rightRes = ways(right, map);
                for (int leftNum : leftRes) {
                    for (int rightNum : rightRes) {
                        int currRes = computeRes(leftNum, rightNum, ch);
                        res.add(currRes);
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }

    private int computeRes(int leftNum, int rightNum, char ch) {
        int res = 0;
        if (ch == '+') {
            res = leftNum + rightNum;
        } else if (ch == '-') {
            res = leftNum - rightNum;
        } else if (ch == '*') {
            res = leftNum * rightNum;
        }
        return res;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*';
    }

    // Method 1: Recursion
    // recursion has n/2 levels
    // input                           2 * 3 - 4 * 5
    //       /         |               |          |             |         \              n
    //    left  *    right            left   -   right         left   *    right
    //    2     *    3-4*5             2*3   -   4*5           2*3-4  *     5
    //          /    |   |    \       /   \      /   \      /  |   |  \                 n-2
    //         3 -  4*5  3-4 * 5
    //                                                                                  n-4
    // Time O(n! * n)
    // Space O(n^2)
    public List<Integer> diffWaysToComputeI(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                List<Integer> leftRes = diffWaysToComputeI(left);
                List<Integer> rightRes = diffWaysToComputeI(right);
                for (int leftNum : leftRes) {
                    for (int rightNum : rightRes) {
                        if (c == '-') {
                            res.add(leftNum - rightNum);
                        } else if (c == '+') {
                            res.add(leftNum + rightNum);
                        } else if (c == '*') {
                            res.add(leftNum * rightNum);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(input));
        Collections.sort(res);
        return res;
    }
}
