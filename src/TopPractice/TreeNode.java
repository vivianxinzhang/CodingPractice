package TopPractice;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }

    // Time O(n)
    // Space O(h)
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        }
        TreeNode newRoot = new TreeNode(t1.val + t2.val);
        newRoot.left = mergeTrees(t1.left, t2.left);
        newRoot.right = mergeTrees(t1.right, t2.right);
        return newRoot;
    }
}
