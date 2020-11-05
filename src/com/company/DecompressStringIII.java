package com.company;

import java.util.Deque;
import java.util.*;

public class DecompressStringIII {
    public static void main(String[] args) {
        DecompressStringIII s = new DecompressStringIII();
        String input = "abc";
        System.out.println(s.decodeStringIII(input));

        input = "ab3[cd[2e]]f";
        System.out.println(s.decodeStringIII(input));
    }

    // every time when you meet a number, it must be followed by [...],
    // we just need to recursively call our method to decode "...",
    // then repeat the result "num" times.
    // Time O()
    // Space O()
    public String decodeStringIII(String input) {
        Deque<Character> queue = new ArrayDeque<>();
        for (char c : input.toCharArray()) queue.offer(c);
        return helper(queue).toString();
    }

    public StringBuilder helper(Deque<Character> queue) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c= queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                StringBuilder sub = helper(queue);
                for (int i = 0; i < num; i++) {
                    sb.append(sub);
                }
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb;
    }
}
