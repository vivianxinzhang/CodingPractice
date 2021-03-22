package com.company;
import java.util.*;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath s = new SimplifyPath();
        String input = "/a/../../../..";
        System.out.println(s.simplify(input));  //  /

        input = "/home/";
        System.out.println(s.simplify(input));  //  /home

        input = "/a/./b/../../c/";
        System.out.println(s.simplify(input));  //  /c

        input = "/a/../../../..";
        System.out.println(s.simplify(input));  //  /
    }

    // consider different cases: ".","" need skip;
    // ".." go back to prev level or skip if there is no previous level
    // Time O(n)
    // Space O(n)
    public String simplify(String path) {
        Set<String> skip = new HashSet<>(Arrays.asList(".", ""));
        Deque<String> deque = new ArrayDeque<>();
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !deque.isEmpty()) {
                deque.pollLast();
            } else if (!skip.contains(dir) && !dir.equals("..")) {
                deque.offerLast(dir);
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }

    public String simplifyI(String path) {
        Deque<String> deque = new ArrayDeque<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !deque.isEmpty()) {
                deque.pollFirst();
            } else if (!skip.contains(dir)) {
                deque.offerFirst(dir);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append("/");
            sb.append(deque.pollLast());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}
