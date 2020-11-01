package com.company;

public class BitwiseANDofNumbersRange {
    public static void main(String[] args) {
        BitwiseANDofNumbersRange s = new BitwiseANDofNumbersRange();
        System.out.println(s.rangeBitwiseAnd(5, 7));
    }

    // Method 2:
    // 10   1 0 1 0        13  0 1 1 0 1       16  1 0 0 0 0
    // 11   1 0 1 1        14  0 1 1 1 0       17  0 0 0 0 1
    // 12   1 1 0 0        15  0 1 1 1 1       18  1 0 0 1 0
    // 13   1 1 0 1        16  1 0 0 0 0       19  1 0 0 1 1
    // 14   1 1 1 0        &   0 0 0 0 0       &   1 0 0 0 0
    // &    1 0 0 0        all bits flipped        fixed|flipped
    // 1. if any bit flips for a given position in range [m to n],
    // then the entire col & val = 0
    // 2. all the columns to the right of flipped bit is also flipped
    // 3. leftmost consecutive common elements in [m,n] are always fixed in given range
    // m 16  1 0 0 0 0      1 0 0 0     1 0 0
    // n 19  1 0 0 1 1      1 0 0 1     1 0 0
    // &     1 0 0 0 0      need to find leftmost common bits(not flipped part)
    // Time O(1) 32 bits
    // Space O(1)
    public int rangeBitwiseAnd(int m, int n) {
        // Write your solution here
        int count = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            count += 1;
        }
        return m << count;
    }


    // Method 1:
    // Time O(n)
    // Space O(1)
    public int rangeBitwiseAndI(int m, int n) {
        // Write your solution here
        int result = m;
        for (int i = m + 1; i <= n; i++) {
            result &= i;
        }
        return result;
    }
}
