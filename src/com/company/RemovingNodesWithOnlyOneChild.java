package com.company;

public class RemovingNodesWithOnlyOneChild {
    public static void main(String[] args) {
        RemovingNodesWithOnlyOneChild s = new RemovingNodesWithOnlyOneChild();
        System.out.println();
        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode twelve = new TreeNode(12);
        ten.left = five;
        ten.right = twelve;
        TreeNode six = new TreeNode(6);
        five.left = six;
        TreeNode two = new TreeNode(2);
        TreeNode eight = new TreeNode(8);
        six.left = two;
        six.right = eight;
        TreeNode fourteen = new TreeNode(14);
        TreeNode nineteen = new TreeNode(19);
        twelve.right = fourteen;
        fourteen.right = nineteen;
        System.out.println(ten);
        TreeNode newRoot = s.deleteNodesWithOneChild(ten);
        System.out.println(newRoot);
        System.out.println();
    }

    public TreeNode deleteNodesWithOneChild(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftRes = deleteNodesWithOneChild(root.left);
        TreeNode rightRes = deleteNodesWithOneChild(root.right);
        if (root.left == null && root.right == null
            || root.left != null && root.right != null) {
            root.left = leftRes;
            root.right = rightRes;
            return root;
        }
        return root.left == null ? rightRes : leftRes;
    }


    public TreeNode deleteNodesWithOneChildI(TreeNode root) {
        if (root == null) {
            return null;
        } else if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode leftRes = deleteNodesWithOneChild(root.left);
        TreeNode rightRes = deleteNodesWithOneChild(root.right);
        if (root.left != null || root.right != null) {
            root.left = leftRes;
            root.right = rightRes;
            return root;
        } else {
            return root.left == null ? rightRes : leftRes;
        }
    }
}
