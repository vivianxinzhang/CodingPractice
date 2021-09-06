package com.company;

import DataStructure.Deque;

import java.util.ArrayDeque;

public class TernaryExpressionTree {
    public static void main(String[] args) {
        TernaryExpressionTree s = new TernaryExpressionTree();

        ExpNode root = s.tree("a?b:c");
        /**      a
         *     /   \
         *    b    c
         * */
        System.out.println();

        ExpNode root1 = s.tree("a?b?c:d:e");
        /**        a
         *       /   \
         *      b     e
         *    /  \
         *   c    d
         * */

        ExpNode root2 = s.tree("a?b:c?d:e");
        /**        a
         *       /   \
         *      b     c
         *          /  \
         *         d    e
         * */
        ExpNode root3 = s.tree("a?b?c?d:e:f?g:h:i?j:k");
    }


    // method 2: using stack

    // Assumptions:
    // The input ternary expression is a string, and it is guaranteed to be valid.
    // Idea is that we traverse a string make first character as root and do following step recursively .
    // 1. If we see Symbol ‘?’, then we add next character as the left child of root.
    // 2. If we see Symbol ‘:’, then we add it as the right child of current root.
    // do this process until we traverse all element of “String”.
    // Method 1: recursion
    // Time O(n)
    // Space O(height)  worst case O(n)
    public ExpNode tree(String exp) {
        if (exp == null || exp.length() == 0) {
            return null;
        }
        int[] index = new int[] {0};
        return convert(exp, index);
    }

    // Helper unction to convert Ternary Expression to a Binary Tree.
    // It return the root of tree.
    private ExpNode convert(String expression, int[] index) {
        // Base case
        if (index[0] >= expression.length()) {
            return null;
        }
        // store current character of expression_string
        // [ 'a' to 'z']
        ExpNode root = new ExpNode(expression.charAt(index[0]));
        // Move ahead in str
        index[0]++;
        // if current character of ternary expression is '?'
        // then we add next character as a left child of
        // current node
        if (index[0] < expression.length() && expression.charAt(index[0]) == '?') {
            index[0]++;
            root.left = convert(expression, index);
            // else we have to add it as a right child of current node
        }
        if (index[0] < expression.length() && expression.charAt(index[0]) == ':' && root.left != null) {
            index[0]++;
            root.right = convert(expression, index);
        }
        return root;
    }
}
