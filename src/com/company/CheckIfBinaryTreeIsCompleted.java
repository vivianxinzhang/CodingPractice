package com.company;
import java.util.*;

public class CheckIfBinaryTreeIsCompleted {
    public static void main(String[] args) {
        CheckIfBinaryTreeIsCompleted s = new CheckIfBinaryTreeIsCompleted();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        System.out.println(s.isCompleted(five));

        eight.right = new TreeNode(11);
        System.out.println(s.isCompleted(five));
    }

    // Time O(n)
    // Space O(n)
    public boolean isCompleted(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean seenNull = false;
        // if the flag is set true, there should not be any child nodes afterwards.
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            //
            if (curr.left != null) {
                if (seenNull) {
                    // if flag is set but we still see cur has a left child,
                    // the binary tree is not a complete tree.
                    return false;
                } else {
                    // if flag is not set and left child is present
                    queue.offer(curr.left);
                }
            } else {
                // if any of the child is not present, set the flag to true.
                seenNull = true;
            }

//            if (curr.left == null) {
//                // if any of the child is not present, set the flag to true.
//                seenNull = true;
//            } else if (seenNull) {
//                // if flag is set but we still see cur has a left child,
//                // the binary tree is not a complete tree.
//                return false;
//            } else {
//                // if flag is not set and left child is present
//                queue.offer(curr.left);
//            }

            // same logic applied to right child.
            if (curr.right != null) {
                if (seenNull) {
                    return false;
                } else {
                    queue.offer(curr.right);
                }
            } else {
                seenNull = true;
            }
        }
        return true;
    }
}
