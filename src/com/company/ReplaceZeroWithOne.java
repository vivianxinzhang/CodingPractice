package com.company;

public class ReplaceZeroWithOne {
    public static void main(String[] args) {
        ReplaceZeroWithOne s = new ReplaceZeroWithOne();

        /**
         * The given array strictly follows the rule that there is no consecutive 1s in this array.
         * In other word, 1 cannot be placed next to 1.
         * Your job is to find out whether we could replace n 0s with 1s and still keep the same rule.
         * Return true if we could otherwise false.
         * */
        int[] array = new int[] {0, 0, 0, 1, 0, 1};
        System.out.println(s.canReplace(array, 1));     // true
        System.out.println(s.canReplaceI(array, 1));    // true

        array = new int[] {1, 0, 0, 0, 0, 0, 1};
        System.out.println(s.canReplace(array, 2));     // true
        System.out.println(s.canReplaceI(array, 2));    // true

        array = new int[] {0, 1, 0, 1, 0, 1};
        System.out.println(s.canReplace(array, 1));     // true
        System.out.println(s.canReplaceI(array, 1));    // true

        array = new int[] {1};
        System.out.println(s.canReplace(array, 2));     // false
        System.out.println(s.canReplaceI(array, 2));     // false
        // We cannot replace any 0 with 1 and still follow the rule.
    }

    // Assumptions:
    // 0 <= n <= length of given array.
    // Method 2:
    // Time O(n)
    // Space O(1)
    public boolean canReplace(int[] array, int k) {
        if (array == null || array.length == 0) {
            return true;
        }
        // lastPos is the position of imaginary previous 1
        int lastPos = -2;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                int numAtMostCanFlipBetween = canFlipAtMostBetween(lastPos, i);
                k -= numAtMostCanFlipBetween;
                lastPos = i;
                if (k <= 0) {
                    return true;
                }
            }
        }
        return k <= canFlipAtMostBetween(lastPos, array.length + 1);
    }

    // index  0  1  2  3
    // array [0, 1, 0, 0]
    private int canFlipAtMostBetween(int lastPos, int i) {
        // i - lastPos - 1 is # of 0s between cur 1 and previous 1
        // cannot flip the first 0 and the last 0
        // num = (i - lastPos - 1 - 2) 0s to consider for flipping
        // at most half can be flipped
        // if num % 2 == 0: num / 2
        // if num % 2 == 0: (num + 1 )/ 2
        // together: (num + 1) / 2 = (i - lastPos - 1 - 2 + 1) / 2 = (i - lastPos - 2) / 2
        return (i - lastPos - 2) / 2;
    }

    // Method 1: Recursion
    // Recursion has min(k, n) levels, branch factor is n/2
    // Time O((n/2)!)
    // Space O(n)
    public boolean canReplaceI(int[] array, int k) {
        if (array == null || array.length == 0) {
            return true;
        }
        return helper(array, 0, array.length - 1, k);
    }

    private boolean helper(int[] array, int left, int right, int k) {
        if (k == 0) {
            return true;
        }
        for (int i = left; i <= right; i++) {
            if (array[i] == 0 && (i == 0 || array[i - 1] != 1) && (i == array.length - 1 || array[i + 1] != 1)) {
                // array[i] = 1;
                if (helper(array, i + 1, right, k - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
}
