package com.company;

public class MininumNumberOfOperations {
    public static void main(String[] args) {
        MininumNumberOfOperations s = new MininumNumberOfOperations();
        System.out.println(s.minOps(0));    // 0

        System.out.println(s.minOps(1));    // 0

        System.out.println(s.minOps(2));    // 2

        System.out.println(s.minOps(3));    // 3

        System.out.println(s.minOps(4));    // 4

        System.out.println(s.minOps(5));    // 5

        System.out.println(s.minOps(6));    // 4
    }

    // M[i] represents minimum number of operations to get i characters of 'A'
    // M[i] = min(curr = M[j] + i / j)  if i % j == 0
    // cppp...pp: 1 copy + (i/j-1) paste of of the first j chars
    // Time O(n)
    // Space O(1)
    public int minOps(int n) {
        // Write your solution here
        if (n <= 0) {
            return 0;
        }
        int[] M = new int[n + 1];
        M[1] = 0;   // no operation needed
        for (int i = 2; i <= n; i++) {
            int currMin = i;
            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    int curr = M[j] + i / j;
                    if (curr < currMin) {
                        currMin = curr;
                    }
                }
            }
            M[i] = currMin;
        }
        return M[n];
    }
}
