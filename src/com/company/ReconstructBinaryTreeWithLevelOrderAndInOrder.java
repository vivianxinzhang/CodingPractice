package com.company;
import java.util.*;

public class ReconstructBinaryTreeWithLevelOrderAndInOrder {
    public static void main(String[] args) {
        ReconstructBinaryTreeWithLevelOrderAndInOrder s = new ReconstructBinaryTreeWithLevelOrderAndInOrder();

        int[] inOrder = new int[]{};
        int[] level = new int[]{};
        System.out.println(s.reconstruct(inOrder, level));   // null

        inOrder = new int[]{1, 3, 4, 5, 8, 11};
        level = new int[]{5, 3, 8, 1, 4, 11};
        System.out.println(s.reconstruct(inOrder, level));   // null
        /**
         *           5
         *        /    \
         *      3        8
         *    /   \        \
         *  1      4        11
         * */
    }

    // levelOrder traversal = {5, 3, 8, 1, 4, 11}
    // inOrder traversal =    {1, 3, 4, 5, 8, 11}
    //               5
    //    (3,1,4)              (8,11)
    //       3                   8
    //   (1)    (4)          ()   (11)
    // Step 1: Pick the first element from level-order, say 20. Find the index of 20 in the in-order sequence.
    // Thus, the in-order sequence can be divided into two parts.
    // Step 2: How to divide the level-order into two parts?
    //	We need to build the level-order for the left subtree and for the right subtree.
    //	Iterate through each element in the level-order, and check if it belongs to the left or the right subtree,
    //	using indexMap:
    //	Case 1: if indexMap[x] < indexMap[root]: add x to left level-order
    //	Case 2: if indexMap[x] > indexMap[root]: add x to right level-order
    // worst case 链表的情况 每层都要过一遍 level order, best case 每次都平分
    // Time = O(n^2) = O(n + n-1 + n-2 + … + 1)  Best case time = O(n log n) if tree is balanced logn levels
    // Space = O(n^2) = O(n + n-1 + n-2 + … + 1)  Best case space = (n + n/2 + n/4 + n/8 + 1) + logn = O(n)
    public TreeNode reconstruct(int[] in, int[] level) {
        // Assumptions: level, in are not null
        // there is no duplicate in the binary tree
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        List<Integer> lList = new ArrayList<>();
        for (int num : level) {
            lList.add(num);
        }
        return helper(lList, inMap);
    }

    private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
        if (level.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(level.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (inMap.get(num) < inMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inMap);
        root.right = helper(right, inMap);
        return root;
    }
}
