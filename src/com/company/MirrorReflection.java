package com.company;

public class MirrorReflection {
    public static void main(String[] args) {
        MirrorReflection s = new MirrorReflection();
        System.out.println(s.mirrorReflection(2, 1));
    }

    // Method 1:
    // Instead of modelling the ray as a bouncing line,
    // model it as a straight line through reflections of the room.
    // In general, the ray goes to the first integer point (kp, kq)
    // where k is an integer, and kp and kq are multiples of p.
    // Thus, the goal is just to find the smallest k for which kq is a multiple of p.
    // Time O(logp)
    // Space O(1)
    public int mirrorReflectionI(int p, int q) {
        int k = helper(p, q);
        p /= k;
        p %= 2;
        q /= k;
        q %= 2;

        if (p == 1 && q == 1) return 1;
        return p == 1 ? 0 : 2;
    }

    private int helper(int p, int q) {
        if (p == 0) {
            return q;
        }
        return helper(q % p, p);
    }

    // Method 2:
    // Time O(logp)
    // Space O(1)
    public int mirrorReflection(int p, int q) {
        int extension = q, reflection = p;
        while (extension % 2 == 0 && reflection % 2 == 0) {
            extension /= 2;
            reflection /= 2;
        }
        if (extension % 2 == 0 && reflection % 2 != 0) {
            return 0;
        }
        if (extension % 2 == 1 && reflection % 2 == 0) {
            return 2;
        }
        if (extension % 2 == 1 && reflection % 2 != 0) {
            return 1;
        }
        return -1;
    }
}
