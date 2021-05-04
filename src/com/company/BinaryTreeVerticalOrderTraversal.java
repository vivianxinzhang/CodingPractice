package com.company;
import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal s = new BinaryTreeVerticalOrderTraversal();
        TreeNode three = new TreeNode(3);
        TreeNode nine = new TreeNode(9);
        TreeNode eight = new TreeNode(8);
        three.left = nine;
        three.right = eight;
        TreeNode four = new TreeNode(4);
        TreeNode zero = new TreeNode(0);
        nine.left = four;
        nine.right = zero;
        TreeNode one = new TreeNode(1);
        TreeNode seven = new TreeNode(7);
        eight.left = one;
        eight.right = seven;
        /**
         *               3
         *             /   \
         *          9       8
         *         /  \   /  \
         *        4    0 1    7
         * */
        System.out.println(s.verticalOrder(three));
        // [4, 9, 3, 0, 1, 8, 7]
    }

    // bfs
    // Time O(n)
    // Space O(n)
    public List<Integer> verticalOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<TreeNode, Integer> cols = new HashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        cols.put(root, 0);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int col = cols.get(cur);
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(cur.key);
            if (cur.left != null) {
                queue.offer(cur.left);
                cols.put(cur.left, col - 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                cols.put(cur.right, col + 1);
            }
        }
        result = getResult(map);
        return result;
    }

    private List<Integer> getResult(Map<Integer, List<Integer>> map) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer key : map.keySet()) {
            minHeap.offer(key);
        }
        while (!minHeap.isEmpty()) {
            Integer curCol = minHeap.poll();
            result.addAll(map.get(curCol));
        }
        return result;
    }
}
