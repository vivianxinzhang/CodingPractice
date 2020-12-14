package com.company;
import java.util.*;

public class LowestCommonAncestorIV {
    public static void main(String[] args) {
        /*           5
                  /    \
                 3      8
              /    \       \
            1     4         11
        * */
        LowestCommonAncestorIV s = new LowestCommonAncestorIV();
        TreeNode five = new TreeNode(5);
        TreeNode three = new TreeNode(3);
        TreeNode eight = new TreeNode(8);
        TreeNode one = new TreeNode(1);
        TreeNode four = new TreeNode(4);
        TreeNode eleven = new TreeNode(11);
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(one);
        nodes.add(four);
        nodes.add(eleven);
        System.out.println(s.lowestCommonAncestor(five, nodes).key);    // 5
    }

    // Assumptions: the list of nodes is not null or not empty
    // all the nodes in the list are guaranteed to be in the tree
    // 物理意义： LCA(root, nodes) LCA of all the target nodes under “root”
    // LCA(root, a, b): LCA of nodes in a set {a, b} that are under root
    // Case 1: if all nodes are under root, return LCA of these nodes
    // Case 2: ifa subset of the given nodes are under root, return LCA of the nodes under root
    // Case 3: if none of the nodes are under root, return null
    // Time O(n)
    // Space O(height) worst case O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>(nodes);
        return helper(root, set);
    }

    private TreeNode helper(TreeNode root, Set<TreeNode> set) {
        if (root == null || set.contains(root)) {    // 只考虑在的点
            return root;
        }
        TreeNode leftRes = helper(root.left, set);
        TreeNode rightRes = helper(root.right, set);    // step 1
        // 有一些nodes在左边  有一些nodes在右边
        if (leftRes != null && rightRes != null) {  // step 2 + 3
            return root;
        }
        // 左边没有点 root没有点 那么一定等于 root.right为根的子树上nodes的LCA
        return leftRes == null ? rightRes : leftRes;
    }
}
