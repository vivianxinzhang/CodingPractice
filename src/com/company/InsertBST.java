package com.company;

public class InsertBST {
    public static void main(String[] args) {
        CheckBalancedTree s = new CheckBalancedTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        two.left = three;
        System.out.println(s.isBalanced(one));
    }

    // Time O(height) worst case O(n)
    // Space O(height)
    public TreeNode deleteTree(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.key == key) {
            // case 1: root is a leaf node
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.right == null) {
                return root.left;
            } else if (root.left == null && root.right.left == null) {
                return root.right;
            } else {
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
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = null;
        return smallest;
    }

    public TreeNode insert(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return new TreeNode(key);
        }
        TreeNode curr = root;
        while (curr.key != key) {
            if (key < curr.key) {
                if (curr.left == null) {
                    curr.left = new TreeNode(key);
                }
                curr = curr.left;
            }
            if (key > curr.key) {
                if (curr.right == null) {
                    curr.right = new TreeNode(key);
                }
                curr = curr.right;
            }
        }
        return root;
    }

    // Time O(height)
    // Space O(height)
    public TreeNode insertIII(TreeNode root, int key) {
        // Write your solution here
        if (root == null) {
            return new TreeNode(key);
        } else if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // method 1: recursion
    // Time O(n) better answer O(h)
    // Space O(h) worst case O(n)
    public  TreeNode insertII(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (key < root.key) {
            root.left = insert(root.left, key);
        } else if (key > root.key) {
            root.right = insert(root.right, key);
        }
        return root;
    }

    // method 2: iterative
    // Time O(n) better answer O(h)
    // Space O(1)
    public  TreeNode insertI(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        TreeNode cur = root;
        while (cur.key != key) {
            if (key < cur.key) {
                if (cur.left == null) {
                    cur.left = new TreeNode(key);
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new TreeNode(key);
                }
                cur = cur.right;
            }
        }
        return root;
    }
}