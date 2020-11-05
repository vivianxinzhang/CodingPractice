package com.company;
import java.util.*;

public class DecompressStringIII {
    public static void main(String[] args) {
        DecompressStringIII s = new DecompressStringIII();
        String input = "2[e]";
        System.out.println(s.decodeStringIII(input));

        input = "cd[2[e]]";
        System.out.println(s.decodeStringIII(input));

        input = "3[cd[2[e]]";
        System.out.println(s.decodeStringIII(input));

        input = "ab3[cd[2[e]]]f";
        System.out.println(s.decodeStringIII(input));

        input = "abc";
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
