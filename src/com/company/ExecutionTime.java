package com.company;
import java.util.*;

public class ExecutionTime {
    public static void main(String[] args) {
        ExecutionTime s = new ExecutionTime();
        List<String> logs = new ArrayList<>();
        System.out.println(Arrays.toString(s.executeTime(0, logs)));
        logs.add("0:start:1");
        logs.add("0:end:3");
        System.out.println(Arrays.toString(s.executeTime(1, logs)));
        logs.add("1:start:8");
        logs.add("1:end:10");
        System.out.println(Arrays.toString(s.executeTime(2, logs)));
    }

    public int[] executeTime(int n, List<String> logs) {
        // Write your solution here
        if (n == 0) {
            return new int[0];
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        String[] s = logs.get(0).split(":");
        String startOrEnd = s[1];
        int currId = Integer.parseInt(s[0]);
        stack.offerFirst(Integer.parseInt(s[0]));
        int prev = Integer.parseInt(s[2]);
        for (int i = 1; i < logs.size(); i++) {
            s = logs.get(i).split(":");
            if (s[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += Integer.parseInt(s[2]) - prev;
                }
                stack.push(Integer.parseInt(s[0]));
                prev = Integer.parseInt(s[2]);
            } else {
                res[stack.peekFirst()] += Integer.parseInt(s[2]) - prev + 1;
                stack.pop();
                prev = Integer.parseInt(s[2]) + 1;
            }
            i++;
        }
        return res;
    }
}
