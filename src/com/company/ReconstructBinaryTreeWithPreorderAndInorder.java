package com.company;
import java.util.*;

public class ReconstructBinaryTreeWithPreorderAndInorder {
    public static void main(String[] args) {
        ReconstructBinaryTreeWithPreorderAndInorder s = new ReconstructBinaryTreeWithPreorderAndInorder();
        int[] preOrder = new int[] {5, 3, 1, 4, 8, 11};
        int[] inOrder = new int[] {1, 3, 4, 5, 8, 11};
        TreeNode root = s.reconstruct(inOrder, preOrder);
        TreePrinter.print(root);
    }

    //    			        5
    //          pre(3,1,4)		(8,11)
    //          in (1,3,4)		(8,11)
    //            3				    8
    //      (1)		(4)		    ()	    (11)
    //      (1)		(4)		    ()	    (11)
    //       1		 4				     11
    // Assumptions:
    // 1. The given sequences are not null and they have the same length
    // 2. There are no duplicate keys in the binary tree
    // preorder traversal = {5, 3, 1, 4, 8, 11}
    // inorder traversal = {1, 3, 4, 5, 8, 11}
    // Method 1: Utilizing the inOrder sequence to determine the size of left/right subtrees
    // Time O(n)
    // Space O(h) worst case O(n)
    public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
        // Assumptions: pre, in are not null, there is no duplicates
        // in the binary tree, the length of pre and in are guaranteed
        // to be the same
        Map<Integer, Integer> inIndex = indexMap(inOrder);
        return helper(preOrder, inIndex, 0, inOrder.length - 1, 0, preOrder.length - 1);
    }

    private Map<Integer, Integer> indexMap(int[] inOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return map;
    }

    private TreeNode helper(int[] pre, Map<Integer, Integer> inIndex, int inLeft,
                            int inRight, int preLeft, int preRight) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        // get the position of the root in inOrder sequence, so that we will know
        // the size of left/right subtrees
        int inMid = inIndex.get(root.key);
        root.left = helper(pre, inIndex, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
        root.right = helper(pre, inIndex, inMid + 1, inRight, preRight + inMid - inRight + 1, preRight);
        return root;
    }

    // Method 2: Another linear Solution with traversing and constructing
    // the binary tree using preOrder and inOrder at the same time.
    public TreeNode reconstructII(int[] inOrder, int[] preOrder) {
        // Assumptions: pre, in are not null, there is no duplicates
        // in the binary tree, the length of pre and in are guaranteed
        // to be the same
        int[] preIndex = new int[] { 0 };
        int[] inIndex = new int[] { 0 };
        return helperII(preOrder, inOrder, preIndex, inIndex, Integer.MAX_VALUE);
    }

    private TreeNode helperII(int[] preOrder, int[] inOrder, int[] preIndex, int[] inIndex, int target) {
        // Traversing and construct the binary tree using preOrder and inOrder,
        // the preOrder is [root][left subtree][right subtree]
        // from the preOrder, we know the root of the binary tree,
        // the inOrder is [left subtree][root][right subtree]
        // when we know the root, we actually know the boundary of the left/right subtree.
        // The "target" is actually the root, and we are using inOrder to identify
        // the boundary of left subtree.
        if (inIndex[0] > inOrder.length || inOrder[inIndex[0]] == target) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preIndex[0]]);
        // preOrder, advance the index by 1 since we already finish the root.
        preIndex[0]++;
        root.left = helperII(preOrder, inOrder, preIndex, inIndex, root.key);
        // inOrder, after finish the left subtree, we can advance the index by 1
        inIndex[0]++;
        root.right = helperII(preOrder, inOrder, preIndex, inIndex, target);
        return root;
    }
}
