package com.company;

public class LargestBSTSubtree {
    public static void main(String[] args) {
        LargestBSTSubtree s = new LargestBSTSubtree();
        TreeNode ten = new TreeNode(10);
        TreeNode five = new TreeNode(5);
        TreeNode fifteen = new TreeNode(15);
        TreeNode one = new TreeNode(1);
        TreeNode eight = new TreeNode(8);
        TreeNode seven = new TreeNode(7);
        ten.left = five;
        ten.right = fifteen;
        five.left = one;
        five.right = eight;
        fifteen.right = seven;
        /**
         *          10
         *        /    \
         *       5     15
         *     /   \     \
         *    1     8     7
         * */
        System.out.println(s.largestBSTSubtree(ten));   // 3
    }

    // brute force:
    // Time O(n*n)
    // Space O(h) worst case O(n)
    public int largestBSTSubtree(TreeNode root) {
        int[] max = new int[] {0};
        numOfNodes(root, max);
        return max[0];
    }

    private int numOfNodes(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int leftRes = numOfNodes(root.left, max);
        int rightRes = numOfNodes(root.right, max);
        int numOfNodes = leftRes + rightRes + 1;
        if (isBST(root)) {
            max[0] = Math.max(max[0], numOfNodes);
        }
        return numOfNodes;
    }

    private boolean isBST(TreeNode root) {
        return helper(root, null, null);
    }

    private boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.key <= min) || (max != null && root.key >= max)) {
            return false;
        }
        boolean leftRes = helper(root.left, min, root.key);
        boolean rightRes = helper(root.right, root.key, max);
        return leftRes && rightRes;
    }


    // Time O(n)
    // Space O(height)
    public int largestBSTSubtreeI(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return 0;
        }
        int[] max = new int[] {1};
        largestBSTSubtree(root, max);
        return max[0];
    }

    // if not BST return -1, if is BST return size
    // update max if current tree is BST
    private Value largestBSTSubtree(TreeNode root, int[] max) {
        if (root == null) {
            return new Value(0, true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        } else if (root.left == null && root.right == null) {
            return new Value(1, true, root.key, root.key);
        }
        Value leftRes = largestBSTSubtree(root.left, max);
        Value rightRes = largestBSTSubtree(root.right, max);
        if (leftRes.isBST && rightRes.isBST && root.key > leftRes.max && root.key < rightRes.min) {
            max[0] = Math.max(leftRes.size + rightRes.size + 1, max[0]);
            return new Value(leftRes.size + rightRes.size + 1, true, leftRes.min, rightRes.max);
        } else {
            return new Value(-1, false, -1, -1);
        }
    }

    class Value {
        int size = 0;
        boolean isBST = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        public Value(int size, boolean isBST, int min, int max) {
            this.size = size;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
