package com.company;

public class ValidPostOrderTraversalOfBST {
    public static void main(String[] args) {
        ValidPostOrderTraversalOfBST s = new ValidPostOrderTraversalOfBST();
        int[] post = new int[] {3, 5, 4};
        System.out.println(s.validPostOrder(post));

        post = new int[] {3,  6,  2,  5,  4};
        System.out.println(s.validPostOrder(post));
    }

    // Time O(nlogn)
    // Space O(height)
    public boolean validPostOrder(int[] post) {
        if (post == null || post.length <= 2) {
            return true;
        }
        return isValidPostOrder(post, 0, post.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidPostOrder(int[] post, int start, int end, int min, int max) {
        if (start > end) {
            return true;
        } else if (start == end) {
            if (post[start] > min && post[start] < max) {
                return true;
            } else {
                return false;
            }
        }
        // key is post[end]
        int currRoot = post[end];
        if (currRoot <= min || currRoot >= max) {
            return false;
        }
        int lastSmallerIdx = end - 1;
        while (lastSmallerIdx >= start && post[lastSmallerIdx] > post[end]) {
            lastSmallerIdx--;
        }
        return isValidPostOrder(post, start, lastSmallerIdx, min, post[end]) && isValidPostOrder(post, lastSmallerIdx + 1, end - 1, post[end], max);
    }
}