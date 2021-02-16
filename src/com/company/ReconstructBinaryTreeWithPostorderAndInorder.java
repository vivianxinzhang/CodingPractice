package com.company;
import java.util.*;

public class ReconstructBinaryTreeWithPostorderAndInorder {
    public static void main(String[] args) {
        ReconstructBinaryTreeWithPostorderAndInorder s = new ReconstructBinaryTreeWithPostorderAndInorder();
        int[] postOrder = new int[] {1, 4, 3, 11, 8, 5};
        int[] inOrder = new int[] {1, 3, 4, 5, 8, 11};
        TreeNode root = s.reconstruct(inOrder, postOrder);
        TreePrinter.print(root);
    }

    // postorder traversal = {1, 4, 3, 11, 8, 5}
    // inorder traversal = {1, 3, 4, 5, 8, 11}
    //    			         5
    //          post(1,4,3)		(11,8)
    //          in  (1,3,4)		(8,11)
    //            3				    8
    //      (1)		(4)		    ()	    (11)
    //      (1)		(4)		    ()	    (11)
    //       1		 4				     11
    // Method 1: Utilizing the inOrder sequence to determine the size of left/right subtrees
    // Time O(n)
    // Space O(h)
    public TreeNode reconstruct(int[] inOrder, int[] postOrder) {
        // Write your solution here
        Map<Integer, Integer> inIndex = indexMap(inOrder);
        return helper(postOrder, 0, postOrder.length - 1, inOrder, 0, inOrder.length - 1, inIndex);
    }

    private TreeNode helper(int[] postOrder, int postLeft, int postRight, int[] inOrder, int inLeft, int inRight, Map<Integer, Integer> inIndex) {
        if (postLeft > postRight) {
            return null;
        }
        TreeNode root = new TreeNode(postOrder[postRight]);
        int rootInOrderIndex = inIndex.get(postOrder[postRight]);
        int rightSubTreeSize = inRight - rootInOrderIndex;
        root.left = helper(postOrder, postLeft, postRight - rightSubTreeSize - 1, inOrder, inLeft, rootInOrderIndex - 1, inIndex);
        root.right = helper(postOrder, postRight - rightSubTreeSize, postRight - 1, inOrder, rootInOrderIndex + 1, inRight, inIndex);
        return root;
    }

    private Map<Integer, Integer> indexMap(int[] in) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return map;
    }
}
