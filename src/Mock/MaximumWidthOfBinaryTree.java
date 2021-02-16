package Mock;
import java.util.*;

public class MaximumWidthOfBinaryTree {
    // Time O(n)
    // Space O(h)
    public int maxWidthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] maxWidth = new int[] {Integer.MIN_VALUE};
        List<Integer> firstIdx = new ArrayList<>();
        dfs(root, 0, 1, firstIdx, maxWidth);
        return maxWidth[0];
    }

    private void dfs(TreeNode root, int level, int index, List<Integer> firstIdx, int[] maxWidth) {
        if (root == null) {
            return;
        }
        if (level == firstIdx.size()) {
            firstIdx.add(index);
        }
        maxWidth[0] = Math.max(maxWidth[0], index - firstIdx.get(level) + 1);
        dfs(root.left, level + 1, index * 2, firstIdx, maxWidth);
        dfs(root.right, level + 1, index * 2 + 1, firstIdx, maxWidth);
    }

    class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
            this.key = key;
        }
    }
}
