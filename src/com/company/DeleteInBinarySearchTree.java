package com.company;

public class DeleteInBinarySearchTree {
    public static void main(String[] args) {
        DeleteInBinarySearchTree s = new DeleteInBinarySearchTree();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode eleven = new TreeNode(11);
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        /*           5
                  /    \
                 3      8
              /    \       \
            1       4         11
        * */
        TreeNode newRoot = s.deleteTree(five, 3);
        /*           5
                  /    \
                 4      8
               /          \
             1            11
        * */
        System.out.println();
    }

    // Time O(height) worst case O(n)
    // Space O(height)
    // Step 1: find the node
    // Step 2: delete operation based on different case
    public TreeNode deleteTree(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.key == key) {
            // case 2.1 root.left == null && root.right == null
            // case 2.2 root.left == null && root.right != null
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                // case 2.3 root.left != null && root.right == null
                return root.left;
            } else if (root.right.left == null) {
                // case 2.4 root.left != null && root.right == null && root.right.left == null
                TreeNode newRoot = root.right;
                newRoot.left = root.left;
                return newRoot;
            } else {
                // case 2.4 root.left != null && root.right == null && root.right.left != null
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }
        if (key < root.key) {
            root.left = deleteTree(root.left, key);
        } else {
            root.right = deleteTree(root.right, key);
        }
        return root;
    }

    private TreeNode deleteSmallest(TreeNode root) {
        while(root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = smallest.right;
        smallest.right = null;
        return smallest;
    }
}
