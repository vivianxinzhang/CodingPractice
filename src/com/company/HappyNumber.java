package com.company;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public static void main(String[] args) {
        HappyNumber s = new HappyNumber();

        System.out.println(s.isHappy(19));          // true
        System.out.println(s.isHappy(10000));       // true
        System.out.println(s.isHappy(12249));       // false
    }

    // Time O(logn)
    // Space O(1)
    public boolean isHappy(int n) {
        int tortoise = n;
        int hare = next(n);
        while (tortoise != hare) {
            tortoise = next(tortoise);  // cur = cur.next;
            hare = next(next(hare));    // cur = cur.next.next;
        }
        return tortoise == 1;
    }

    private int next(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    // T O(logn)
    // S O(1)
    public boolean isHappyI(int n) {
        Set<Integer> seen = new HashSet<>();
        seen.add(n);
        while (n != 1) {
            int nextNum = getNext(n);
            if (seen.contains(nextNum)){
                return false;
            }
            seen.add(nextNum);
            n = nextNum;
        }
        return true;
    }

    private int getNext(int n) {        // O(logn)
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}
