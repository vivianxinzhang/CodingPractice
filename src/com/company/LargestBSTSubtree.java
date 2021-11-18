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

    /**
     *                                                     25(F,8,0,0)
     *                       (F,3,0,0) /                                      \(T,8,20,70)
     *                     18                                                    50
     *         (F,1,0,0) /      \(T,3,18,25)                     (T,4,20,40) /      \(T,3,55,70)
     *      19                         20                         35                        60
     *        \(T,1,15,15) (T,1,18,18)/ \(T,1,25,25)  (T,2,20,25)/ \(T,1,40,40) (T,1,55,55)/ \(T,1,70,70)
     *          15                18       25                 20    40                   55  70
     *                                                          \(T,1,25,25)
     *                                                          25
     * */
    // Method 2: use post-order traversal to find the largest binary search tree in O(n)
    // left - right - root
    // visit everything on the left subtree, visit everything on the right subtree
    // use the information collected to find the largest BST
    // Traverse tree in post order fashion. Left and right nodes return 4 pieces
    // of information to root which isBST, size of max BST, min and max in those subtree.
    // If both left and right subtree are BST and this node data is greater than max
    // of left and less than min of right then it returns to above level
    // left size + right size + 1 and new min will be min of left side and new max will be max of
    // right side.
    // Time O(n)
    // Space O(height) worst case O(n)
    public int largestBSTSubtree(TreeNode root) {
        MinMax res = largest(root);
        return res.size;
    }

    private MinMax largest(TreeNode root) {
        if (root == null) {
            return new MinMax();
        }
        MinMax currRes = new MinMax();
        //postorder traversal of tree. First visit left and right then
        //use information of left and right to calculate largest BST.
        MinMax leftRes = largest(root.left);
        MinMax rightRes = largest(root.right);
        //if either of left or right subtree says its not BST or the data
        //of this node is not greater/equal than max of left and less than min of right
        //then subtree with this node as root will not be BST.
        //Return false and max size of left and right subtree to parent
        if(!leftRes.isBST || !rightRes.isBST || (leftRes.max > root.key || rightRes.min <= root.key)){
            currRes.isBST = false;
            currRes.size = Math.max(leftRes.size, rightRes.size);
            return currRes;
        }
        //if we reach this point means subtree with this node as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        currRes.isBST = true;
        currRes.size = leftRes.size + rightRes.size + 1;
        //if root.left is null then set root.data as min else
        //take min of left side as min
        currRes.min = root.left != null ? leftRes.min : root.key;
        //if root.right is null then set root.data as max else
        //take max of right side as max.
        currRes.max = root.right != null ? rightRes.max : root.key;
        return currRes;
    }

    class MinMax {
        int min;
        int max;
        boolean isBST;
        int size;

        public MinMax() {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            isBST = true;
            size = 0;
        }
    }

    // Method 1: brute force
    // Time O(n*n)
    // Space O(h) worst case O(n)
    public int largestBSTSubtreeI(TreeNode root) {
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
        if (isBST(root, null, null)) {
            max[0] = Math.max(max[0], numOfNodes);
        }
        return numOfNodes;
    }

    private boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.key <= min) || (max != null && root.key >= max)) {
            return false;
        }
        boolean leftRes = isBST(root.left, min, root.key);
        boolean rightRes = isBST(root.right, root.key, max);
        return leftRes && rightRes;
    }
}
