package com.company;

import java.util.ArrayList;
import java.util.List;

public class GetKeysInBinarySearchTreeInGivenRange {
    public static void main(String[] args) {
        ReconstructBinarySearchTreeWithLevelOrder rec = new ReconstructBinarySearchTreeWithLevelOrder();
        int[] level = new int[]{5, 3, 8, 1, 4, 11};
        TreeNode root = rec.reconstruct(level);
        /*        5
                /   \
               3     8
             /   \     \
            1     4     11
        * */
        GetKeysInBinarySearchTreeInGivenRange s = new GetKeysInBinarySearchTreeInGivenRange();
        System.out.println(s.getRange(root, 3, 7));     // [3, 4, 5]
    }

    // in order
    // if min < root.key, need to traverse left subtree
    // if min <= root.key <= max, add root value to the result
    // if max > root.key, need to traverse right subtree
    // Time O(n)
    // Better answer: O(height + # of nodes in the range of [k1, k2])
    // 需要通过O(height)的时间来找到区间 再把区间里的所有元素打印出来
    // 如果区间很小 前一部分dominant
    // 如果区间很大 for example包含所有元素 后一部分为O(n)
    // Space O(height) worst case O(n)
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        helper(root, min, max, res);
        return res;
    }

    private void helper(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 1. determine if the left subtree should be traversed, only when
        // root.key > min, we should traverse the left subtree
        if (min < root.key) {
            helper(root.left, min, max, res);
        }
        // 2. determine if root should be traversed
        if (root.key >= min && root.key <= max) {
            res.add(root.key);
        }
        // 3. determine if the right subtree should be traversed, only when
        // root.key < max, we should traverse the right subtree
        if (max > root.key) {
            helper(root.right, min, max, res);
        }
    }
}
