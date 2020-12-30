package com.company;

public class ReachANumber {
    public static void main(String[] args) {
        ReachANumber s = new ReachANumber();
        System.out.println(s.reachNumber(2));
        System.out.println(s.reachNumber(3));
    }

    // https://zxi.mytechroad.com/blog/math/leetcode-754-reach-a-number/
    // Observation: 1. Steps(n) = Steps(-n) 2. +/- 1 +/- 2 +/- 3 ... +/- k = target
    // To find the smallest k, use + only
    // + 1 + 2 + 3 + ... + i + ... + k = target + d, (0 <= d < k)
    // 1. if d == 0: k is the answer
    // 2. if d % 2 == 0: 1 + 2 + 3 + ... -i + ... + k = target, i = d/2
    // 3. if d % 2 == 1:
    //    a. k % 2 == 0, 1 + 2 + 3 + ... -i + ... + k + (k + 1) = target
    //       i. i = ((k + 1) + d) / 2 <= k   k为偶数 d为奇数
    //    b. k % 2 == 1, 1 + 2 + 3 + ... -i + ... + k - (k + 1) + (k - 2)= target
    //       i. i = ((k + 2) - (k + 1) + d) / 2 = (d + 1) / 2 <= k   k为奇数 d为奇数
    // Time O(sqrt(n))
    // Space O(1)
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        int sum = 0;
        while (sum < target) {
            k++;
            sum += k;
        }
        int d = target - sum;
        if (d % 2 == 0) {
            return k;
        }
        return k + 1 + (k % 2);
    }
}