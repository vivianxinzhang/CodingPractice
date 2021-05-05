package com.company;

public class FlattenBinaryTreeToString {
    public static void main(String[] args) {
        FlattenBinaryTreeToString s = new FlattenBinaryTreeToString();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        one.left = two;
        one.right = three;
        two.left = four;
        /**
         *          1
         *        /   \
         *      2      3
         *     /
         *    4
         * */
        System.out.println(s.flattenBinaryTree(one));
        // 1(2(4))(3)

        TreeNode one1 = new TreeNode(1);
        TreeNode two1 = new TreeNode(2);
        TreeNode three1 = new TreeNode(3);
        TreeNode four1 = new TreeNode(4);
        one1.left = two1;
        one1.right = three1;
        three1.right = four1;
        /**
         *          1
         *        /   \
         *      2      3
         *              \
         *               4
         * */
        System.out.println(s.flattenBinaryTree(one1));
        // 1(2)(3()(4))
    }

    // Given an binary tree, try to flatten it following the requirements below:
    // 1. Traverse the binary tree in preorder.
    // 2. If a TreeNode has child(children), put it (them) into parenthesis.
    // 3. When it is necessary to represent a null node to make sure the tree is represented
    // correctly without ambiguity, use empty parenthesis "()" .
    // Time O(n)
    // Space O(height) worst case O(n)
    public String flattenBinaryTree(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            sb.append(root.key);
            return;
        }
        sb.append(root.key);
        // left child
        sb.append('(');
        preOrder(root.left, sb);
        sb.append(')');
        // right child
        if (root.right != null) {
            sb.append('(');
            preOrder(root.right, sb);
            sb.append(')');
        }
    }
}
