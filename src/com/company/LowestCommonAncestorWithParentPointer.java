package com.company;

public class LowestCommonAncestorWithParentPointer {
    public static void main(String[] args) {
        LowestCommonAncestorWithParentPointer s = new LowestCommonAncestorWithParentPointer();
        TreeNodeP five = new TreeNodeP(5, null);
        TreeNodeP three = new TreeNodeP(3, five);
        TreeNodeP eight = new TreeNodeP(8, five);
        TreeNodeP one = new TreeNodeP(1, three);
        TreeNodeP four = new TreeNodeP(4, three);
        TreeNodeP eleven = new TreeNodeP(11, eight);
        /*
                      5
                    /    \
                   3      8
                 /   \     \
                1    4      11
        **/
        five.left = three;
        five.right = eight;
        three.left = one;
        three.right = four;
        eight.right = eleven;
        System.out.println(s.lowestCommonAncestor(one, eleven).key);
        System.out.println(s.lowestCommonAncestorI(one, eleven).key);
    }

    static class TreeNodeP {
        int key;
        TreeNodeP left;
        TreeNodeP right;
        TreeNodeP parent;

        public TreeNodeP(int key, TreeNodeP parent) {
            this.key = key;
            this.parent = parent;
        }
    }

    // Assumptions:
    // 1. There is parent pointer for the nodes in the binary tree
    // 2. The given two nodes are not guaranteed to be in the binary tree
    // Implementation 1:
    // Time O(height) worst case O(n)
    // Space O(1)
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        int l1 = length(one);
        int l2 = length(two);
        // This is a small trick that can guarantee when calling mergeNode();
        // the first list is the shorter list, the second list is the longer list.
        if (l1 <= l2) {
            return mergeNode(one, two, l2 - l1);
        } else {
            return mergeNode(two, one, l1 - l2);
        }
    }

    private TreeNodeP mergeNode(TreeNodeP shorter, TreeNodeP longer, int diff) {
        while (diff > 0) {
            longer = longer.parent;
            diff--;
        }
        while (longer != shorter) {
            longer = longer.parent;
            shorter = shorter.parent;
        }
        return longer;
    }

    // get the length of the list from the node to the root of the tree
    // along the path using parent pointers.
    private int length(TreeNodeP node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.parent;
        }
        return length;
    }

    // Implementation 2:
    // step 1: Both nodes go up and find their depths and roots.
    //  step 1.1: if the roots are different, return null because they are not in the same tree.
    // step 2: Move up the deeper node to be the same as the other node
    // step 3: Move both nodes up together and when they collide, you find the LCA.
    // Time O(height) worst case O(n)
    // Space O(1)
    public TreeNodeP lowestCommonAncestorI(TreeNodeP one, TreeNodeP two) {
        // corner case
        if (one == null || two == null) {
            return null;
        }
        // step 1: Both nodes go up and find their depths and roots.
        Pair oneRes = findRootandDepth(one);
        Pair twoRes = findRootandDepth(two);

        // step 1.1: if the roots are different, return null because they are not in the same tree.
        if (oneRes.root != twoRes.root) {
            return null;
        }

        // step 2: Move up the deeper node to be the same as the other node
        while (oneRes.depth > twoRes.depth) {
            one = one.parent;
            oneRes.depth--;
        }

        while (oneRes.depth < twoRes.depth) {
            two = two.parent;
            twoRes.depth--;
        }

        // step 3: Move both nodes up together and when they collide, you find the LCA.
        while (one != two) {
            one = one.parent;
            two = two.parent;
        }

        return one;
    }

    // step 1: Both nodes go up and find their depths and roots.
    private Pair findRootandDepth(TreeNodeP node) {
        int depth = 0;
        while (node.parent != null) {
            node = node.parent;
            depth++;
        }
        return new Pair(depth, node);
    }

    class Pair {
        int depth;
        TreeNodeP root;

        public Pair(int depth, TreeNodeP root) {
            this.depth = depth;
            this.root = root;
        }
    }
}
