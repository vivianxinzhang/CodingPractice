package com.company;

import java.util.Deque;

public class NestedListWeightSum {
    public static void main(String[] args) {
        NestedListWeightSum s = new NestedListWeightSum();
        System.out.println(s.depthSum("[-1]"));      // 122
        System.out.println(s.depthSum("[[1,1],2,[1,1]]"));  // 10
        System.out.println(s.depthSum("[1,[4,[6]]]"));      // 27
        System.out.println(s.depthSum("[[[18,20],4]]"));      // 122
    }

    // Time O(n)
    // Space O(1)
    public int depthSum(String input) {
        int res = 0;
        int depth = 0;
        int i = 0;
        int sign = 1;
        while (i < input.length()) {
           if (input.charAt(i) == '[') {
               depth++;
               i++;
           } else if (input.charAt(i) == ']') {
               depth--;
               i++;
           } else if (Character.isDigit(input.charAt(i))) {
               int iNext = i;
               while (Character.isDigit(input.charAt(iNext))) {
                   iNext++;
               }
               int num = Integer.parseInt(input.substring(i, iNext));
               res += sign * num * depth;
               i = iNext;
               sign = 1;
           } else if (input.charAt(i) == '-') {
               sign = -1;
               i++;
           } else {
               i++;
           }
        }
        return res;
    }
}
