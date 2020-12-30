package com.company;
import java.util.HashMap;

public class PseudoPalindromicPathsInABinaryTree {
    public static void main(String[] args) {
        PseudoPalindromicPathsInABinaryTree s = new PseudoPalindromicPathsInABinaryTree();
        TreeNode one = new TreeNode(1);
        System.out.println(s.pseudoPalindromicPaths(one));  // 1

        TreeNode two1 = new TreeNode(2);
        TreeNode two2 = new TreeNode(2);
        one.left = two1;
        one.right = two2;
        System.out.println(s.pseudoPalindromicPaths(one));  // 0
    }

    // Time O(n)
    // Space O(h)
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] sum = new int[] {0};
        int[] oddCount = new int[] {0};
        HashMap<Integer, Integer> map = new HashMap<>();
        dfs(root, map, oddCount, sum);
        return sum[0];
    }

    private void dfs(TreeNode root, HashMap<Integer, Integer> map, int[] oddCount, int[] sum) {
        if (root == null) {
            return;
        }
        Integer count = map.getOrDefault(root.key, 0);
        map.put(root.key, count + 1);
        int originalOddCount = oddCount[0];
        if ((count + 1) % 2 == 0) {
            oddCount[0]--;
        } else {
            oddCount[0]++;
        }
        if (root.left == null && root.right == null) {
            if (oddCount[0] <= 1) {
                sum[0]++;
            }
        }
        dfs(root.left, map, oddCount, sum);
        dfs(root.right, map, oddCount, sum);
        map.put(root.key, count);
        oddCount[0] = originalOddCount;
    }
}
