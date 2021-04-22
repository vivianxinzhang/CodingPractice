package com.company;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GetPostOrderSequenceByPreOrderAndInOrder {
    public static void main(String[] args) {
        GetPostOrderSequenceByPreOrderAndInOrder s = new GetPostOrderSequenceByPreOrderAndInOrder();
        /**
         *              1
         *            /   \
         *           2     3
         *         /  \     \
         *        4    5     6
         * */
        int[] pre = new int[] {1, 2, 4, 5, 3, 6};
        int[] in = new int[] {4, 2, 5, 1, 3, 6};
        System.out.println(Arrays.toString(s.postOrder(pre, in)));
        // [4, 5, 2, 6, 3, 1]
    }

    // root key is the first in pre, and the last in post,
    // use root key and inOrderIndexMap, to root index in inOrder,
    // and then calculate left subtree size and right subtree size
    // Assumptions:
    // The given Inorder and Preorder traversals are guaranteed to be valid.
    // Time O(n)
    // Space O(height) worst case O(n)
    public int[] postOrder(int[] pre, int[] in) {
        Map<Integer, Integer> inOrderIndexMap = indexMap(in);
        int[] post = new int[pre.length];
        helper(pre, 0, pre.length - 1, in, 0, in.length - 1, post, 0, post.length - 1, inOrderIndexMap);
        return post;
    }

    private void helper(int[] pre, int preLeft, int preRight,
                        int[] in, int inLeft, int inRight,
                        int[] post, int postLeft, int postRight,
                        Map<Integer, Integer> inOrderIndexMap) {
        if (preLeft > preRight) {
            return;
        }
        int rootKey = pre[preLeft];
        post[postRight] = pre[preLeft];    // root
        int rootInOrderIndex = inOrderIndexMap.get(rootKey);
        int leftSubTreeSize = rootInOrderIndex - inLeft;
        helper(pre, preLeft + 1, preLeft + leftSubTreeSize, in, inLeft, rootInOrderIndex - 1, post, postLeft, postLeft + leftSubTreeSize - 1, inOrderIndexMap);
        helper(pre, preLeft + leftSubTreeSize + 1, preRight, in, rootInOrderIndex + 1, inRight, post, postLeft + leftSubTreeSize, postRight - 1, inOrderIndexMap);
    }

    private Map<Integer, Integer> indexMap(int[] inOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return map;
    }
}
