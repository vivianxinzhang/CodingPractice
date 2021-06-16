package com.company;

import java.util.ArrayList;
import java.util.List;

public class SelfDividedNumbers {
    public static void main(String[] args) {
        SelfDividedNumbers s = new SelfDividedNumbers();

        System.out.println(s.selfDivideNumbers(1, 15));
        // [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15]
    }

    // Time O(n)
    // Space O(1)
    public List<Integer> selfDivideNumbers(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int num = low; num <= high; num++) {
            if (canSelfDivide(num)) {
                res.add(num);
            }
        }
        return res;
    }

    private boolean canSelfDivide(int num) {
        int cur = num;
        while (cur != 0) {
            int digit = cur % 10;
            if (digit == 0 || (num % digit != 0)) {
                return false;
            }
            cur /= 10;
        }
        return true;
    }
}
