package com.company;

public class FindNumberOfBSTsGenerated {
    public static void main(String[] args) {
        FindNumberOfBSTsGenerated s = new FindNumberOfBSTsGenerated();
        System.out.println(s.numOfTrees(0));

        System.out.println(s.numOfTrees(1));

        System.out.println(s.numOfTrees(2));

        System.out.println(s.numOfTrees(3));

        System.out.println(s.numOfTrees(4));

        System.out.println(s.numOfTrees(5));
    }

    // Time O(n)
    // Space O(n)
    public int numOfTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] M = new int[n + 1];
        M[0] = 1;
        M[1] = 1;
        for (int i = 2; i <= n; i++) {
            // pick one as root, then i - 1 will be children
            for (int j = 0; j < i; j++) {
                // nodes in left subtree j, nodes in right subtree i - 1 - j
                M[i] += M[j] * M[i - 1 - j];
            }
        }
        return M[n];
    }
}