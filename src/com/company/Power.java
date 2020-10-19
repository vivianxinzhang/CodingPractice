package com.company;

import com.sun.javaws.exceptions.InvalidArgumentException;

public class Power {
    public static void main(String[] args) {
        Power s = new Power();
//        System.out.println(s.power(0, 0));
//        System.out.println(s.powerI(0, 0));
//        System.out.println(s.powerHelper(0, 0));

        System.out.println(s.power(2, 0));
        System.out.println(s.powerI(2, 0));
        System.out.println(s.powerHelper(2, 0));

        System.out.println();
        System.out.println(s.power(2, -3));
        System.out.println(s.powerI(2, -3));
        System.out.println(s.powerHelper(2, -3));
    }

    // Assumption: b >= 0
    // Time O(logb)
    // Space O(logb)
    public double power(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (a == 0) {
            return 0;
        }
        double half = power(a, b / 2);
        if (b > 0) {
            return b % 2 == 0 ? half * half : half * half * a;
        } else {
            return b % 2 == 0 ? half * half : half * half * 1 / a;
        }
    }

    // corner cases:
    // a = 0, b = 0: 0^0
    // a = 0, b < 0: 0^-1 = 1 / 0 invalid
    // b < 0: a^b = 1/(a^(-b))
    public double powerI(int a, int b) {
        if (a == 0) {
            if (b <= 0) {
                System.out.println("invalid result");
                 throw new RuntimeException("invalid result");
            }
            return 0;
        }

        if (b > 0) {
            return powerHelper(a, b / 2);
        } else {
            return 1 / (double)powerHelper(a, -b);
        }
    }

    private int powerHelper(int a, int b) {
        if (b == 0) {
            return 1;
        }
        int half = powerHelper(a, b / 2);
        return b % 2 == 0 ? half * half : half * half * a;
    }
}
