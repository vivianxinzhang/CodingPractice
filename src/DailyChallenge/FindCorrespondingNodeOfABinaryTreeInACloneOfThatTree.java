package DailyChallenge;

import com.company.TreeNode;

public class FindCorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public static void main(String[] args) {
        FindCorrespondingNodeOfABinaryTreeInACloneOfThatTree s = new FindCorrespondingNodeOfABinaryTreeInACloneOfThatTree();
        TreeNode one = new TreeNode(1);
        TreeNode twoL = new TreeNode(2);
        TreeNode twoR = new TreeNode(2);
        one.left = twoL;
        one.right = twoR;
        TreeNode oneC = new TreeNode(1);
        TreeNode twoLC = new TreeNode(2);
        TreeNode twoRC = new TreeNode(2);
        oneC.left = twoLC;
        oneC.right = twoRC;
        System.out.println(s.getTargetCopy(one, oneC, twoR));
        System.out.println(twoRC);
    }

    // follow up:  if repeated values on the tree are allowed.
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return dfs(original, cloned, target);
    }

    private TreeNode dfs(TreeNode root, TreeNode cloned, TreeNode target) {
        if (root == null || root == target) {
            return cloned;
        }
        TreeNode leftRes = dfs(root.left, cloned.left, target);
        if (leftRes != null) {
            return leftRes;
        }
        TreeNode rightRes = dfs(root.right, cloned.right, target);
        return rightRes;
    }

    // Time O(n)
    // Space O(height)
    public final TreeNode getTargetCopyI(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return dfs(cloned, target);
    }

    private TreeNode dfs(TreeNode root, TreeNode target) {
        if (root == null || root.key == target.key) {
            return root;
        }
        TreeNode leftRes = dfs(root.left, target);
        if (leftRes != null) {
            return leftRes;
        }
        TreeNode rightRes = dfs(root.right, target);
        return rightRes;
    }
}
