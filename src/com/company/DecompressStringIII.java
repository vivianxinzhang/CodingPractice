package com.company;
import java.util.*;

public class DecompressStringIII {
    public static void main(String[] args) {
        DecompressStringIII s = new DecompressStringIII();
        String input = "2[e]";
        System.out.println(s.decodeStringIII(input));   // ee

        input = "cd[2[e]]";
        System.out.println(s.decodeStringIII(input));   // cdee

        input = "3[cd[2[e]]";
        System.out.println(s.decodeStringIII(input));   // cdeecdeecdee

        input = "ab3[cd[2[e]]]f";
        System.out.println(s.decodeStringIII(input));   // abcdeecdeecdeef

        input = "abc";
        System.out.println(s.decodeStringIII(input));   // abc
    }

    // Assumptions:
    // 1. N is alway positive integer.
    // 2. The input is always valid, i.e. the brackets are always in pair.
    // 3. The decompressed string (that is the return value) doesn't contain digit or brackets,
    // which means digit, '[' and ']' are only used in encoded (compressed) string.
    // every time when you meet a number, it must be followed by [...],
    // we just need to recursively call our method to decode "...",
    // then repeat the result "num" times.
    // Time O(n)  n is output length
    // Space O(n + level)  worst case O(n)
    public String decodeStringIII(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        int[] index = new int[] {0};
        return helper(input, index);
    }

    private String helper(String input, int[] index) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (index[0] < input.length()) {
            char ch = input.charAt(index[0]);
            index[0]++;
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                String sub = helper(input, index);
                for (int i = 0; i < num; i++) {
                    sb.append(sub);
                }
                num = 0;
            } else if (ch == ']') {
                return sb.toString();
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public String decodeStringIV(String input) {
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : input.toCharArray()) queue.offer(c);
        return helper(queue);
    }

    public String helper(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = helper(queue);
                if (num == 0) {     // no prefix int
                    sb.append(sub);
                } else {    // has prefix int
                    for (int i = 0; i < num; i++) {
                        sb.append(sub);
                    }
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
