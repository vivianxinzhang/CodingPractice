package com.company;
import java.util.*;

public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath s = new SimplifyPath();
        String input = "/home/";
        System.out.println(s.simplify(input));

        input = "/a/./b/../../c/";
        System.out.println(s.simplify(input));
    }

    // Time O(n)
    // Space O(n)
    public String simplify(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        while (!stack.isEmpty()) {
            String dir = stack.pollFirst();
            res = "/" + dir + res;
        }
        return res.isEmpty() ? "/" : res;
    }
}
