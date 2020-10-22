package com.company;
import java.util.*;

public class GetKeysInBinaryTreeLayerByLayer {
    public static void main(String[] args) {
        GetKeysInBinaryTreeLayerByLayer s = new GetKeysInBinaryTreeLayerByLayer();
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        two.left = one;
        System.out.println(s.layerByLayer(two));
    }

    // Time O(n)
    // Space O(n)
    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // the list storing all the nodes on the current level
            List<Integer> currLayer = new ArrayList<>();
            // the size of current level
            int size = queue.size();
            // traverse all the nodes on the current level and
            // prepare for the next level
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                currLayer.add(curr.key);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            result.add(currLayer);
        }
        return result;
    }
}
