package com.company;
import java.util.*;

public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        DifferentWaysToAddParentheses s = new DifferentWaysToAddParentheses();
        System.out.println(s.diffWaysToCompute("2-1-1"));
        // [0, 2]
        System.out.println(s.diffWaysToCompute("2*3-4*5"));
        // [-34, -14, -10, -10, 10]
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
    // Time O((n/2)! * n)
    // Space O(n^2)
    public List<Integer> diffWaysToComputeI(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                String a = input.substring(0, i);
                String b = input.substring(i + 1);
                List<Integer> al = diffWaysToComputeI(a);
                List<Integer> bl = diffWaysToComputeI(b);
                for (int x : al) {
                    for (int y : bl) {
                        if (c == '-') {
                            res.add(x - y);
                        } else if (c == '+') {
                            res.add(x + y);
                        } else if (c == '*') {
                            res.add(x * y);
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
