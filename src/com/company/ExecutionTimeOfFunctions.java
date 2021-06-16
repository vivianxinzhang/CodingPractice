package com.company;

import java.util.*;

public class ExecutionTimeOfFunctions {
    public static void main(String[] args) {
        ExecutionTimeOfFunctions s = new ExecutionTimeOfFunctions();
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:7");
        // 0   1   2   3   4   5   6    7   8
        // 0-------|               |--------0
        //         |1-------------1|
        System.out.println(Arrays.toString(s.executeTime(2, logs)));    // [4, 4]

        logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("2:start:6");
        logs.add("2:end:6");
        logs.add("0:end:7");
        // 0   1   2   3   4   5   6   7   8
        // 0-------|                   |---0
        //         |1--------------1|
        //                         |2-2|
        System.out.println(Arrays.toString(s.executeTime(3, logs)));    // [3, 4, 1]

        logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("0:start:2");
        logs.add("0:start:3");
        logs.add("0:end:4");
        logs.add("0:end:5");
        logs.add("0:end:7");
        // 0   1   2   3   4   5   6   7   8
        // 0-------|           |-----------0
        //         0---|   |---0
        //             0---|
        System.out.println(Arrays.toString(s.executeTime(1, logs)));    // [8]
    }

    // Note:
    // 1. Input logs will be sorted by timestamp, NOT log id.
    // 2. Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
    // 3. Two functions won’t start or end at the same time.
    // 4. Functions could be called recursively, and will always end.
    // For example,
    // "0:start:1" means function 0 starts at timestamp 1;
    // "0:end:0" means function 0 ends at timestamp 0 + 1.
    // total operation time for function 0 is 1
    // 5. 1 <= n <= 100
    // Time O(n)
    // Space O(n)
    public int[] executeTime(int n, List<String> logs) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        int currId;
        String action;
        int currTime;
        int prevTime = 0;
        for (String entry : logs) {
            String[] log = entry.split(":");
            currId = Integer.parseInt(log[0]);
            action = log[1];
            currTime = Integer.parseInt(log[2]);
            if (action.equals("start")) {
                if (!stack.isEmpty()) {     // suspend job on stack top
                    res[stack.peekFirst()] += currTime - prevTime;
                }
                stack.offerFirst(currId);
                prevTime = currTime;
            } else {
                res[stack.peekFirst()] += currTime + 1 - prevTime;
                stack.pollFirst();
                prevTime = currTime + 1;
            }
        }
        return res;
    }
}
