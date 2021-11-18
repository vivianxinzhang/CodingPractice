package com.company;
import java.util.*;

public class HouseRobberIII {
    public static void main(String[] args) {
        HouseRobberIII s = new HouseRobberIII();

        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        System.out.println(s.rob(one));   // 5
    }

    // Time O(n)
    // Space O(n)
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> flag1 = new HashMap<>();     // rob
        Map<TreeNode, Integer> flag0 = new HashMap<>();     // not rob
        return helper(root, 1, flag1, flag0);
    }

    private int helper(TreeNode root, int flag, Map<TreeNode, Integer> flag1, Map<TreeNode, Integer> flag0) {
        if (root == null) {
            return 0;
        }
        if (flag == 1 && flag1.get(root) != null) {
            return flag1.get(root);
        }
        if (flag == 0 && flag0.get(root) != null) {
            return flag0.get(root);
        }
        int res;
        if (flag == 0) {
            // cannot choose
            res = dfs(root.left, 1) + dfs(root.right, 1);
        } else {
            // choose
            int option1 = root.key + dfs(root.left, 0) + dfs(root.right, 0);
            // not choose
            int option2 = dfs(root.left, 1) + dfs(root.right, 1);
            res = Math.max(option1, option2);
        }
        if (flag == 1) {
            flag1.put(root, res);
        }
        if (flag == 0) {
            flag0.put(root, res);
        }
        return res;
    }

    // Method 2: DFS height levels, branching factor is 4
    // Time: O(4^h)
    // Space: O(height) worst case O(n)
    public int robII(TreeNode root) {
        return dfs(root, 1);
    }

    // flag = 1: can choose or not choose
    // flag = 0: cannot choose
    private int dfs(TreeNode root, int flag) {
        if (root == null) {
            return 0;
        }
        if (flag == 0) {
            // cannot choose
            return dfs(root.left, 1) + dfs(root.right, 1);
        } else {
            // choose
            int option1 = root.key + dfs(root.left, 0) + dfs(root.right, 0);
            // not choose
            int option2 = dfs(root.left, 1) + dfs(root.right, 1);
            return Math.max(option1, option2);
        }
    }

    // Method 1: post order travesal + dfs
    // Time O(2^n)
    // Space O(n)
    public int robI(TreeNode root) {
        int[] max = new int[1];
        List<TreeNode> postOrder = new ArrayList<>();
        postOrder(root, postOrder);
        Map<TreeNode, Integer> map = new HashMap<>();   // 1: rob   0: no rob
        dfs(postOrder, 0, map, 0, max);
        return max[0];
    }

    private void dfs(List<TreeNode> postOrder, int index, Map<TreeNode, Integer> map, int cur, int[] max) {
        if (index == postOrder.size()) {
            max[0] = Math.max(max[0], cur);
            return;
        }
        // not rob
        TreeNode root = postOrder.get(index);
        map.put(root, 0);
        dfs(postOrder, index + 1, map, cur, max);
        // rob
        if ((root.left == null || map.get(root.left) == 0) && (root.right == null || map.get(root.right) == 0)){
            map.put(root, 1);
            dfs(postOrder, index + 1, map, cur + root.key, max);
            map.put(root, 0);
        }
    }

    private void postOrder(TreeNode root, List<TreeNode> postOrder) {
        if (root == null) {
            return;
        }
        postOrder(root.left, postOrder);
        postOrder(root.right, postOrder);
        postOrder.add(root);
    }
}
