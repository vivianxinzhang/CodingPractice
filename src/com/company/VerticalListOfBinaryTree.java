package com.company;

import java.util.*;

public class VerticalListOfBinaryTree {
    public static void main(String[] args) {
        VerticalListOfBinaryTree s = new VerticalListOfBinaryTree();
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
        System.out.println(s.verticalPrint(three));
        // [4, 9, 3, 0, 1, 8, 7]
    }

    // bfs
    // Time O(n)
    // Space O(n)
    public List<List<Integer>> verticalPrint(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<TreeNode, Integer> colMap = new HashMap<>();
        Map<Integer, List<Integer>> resMap = new HashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        colMap.put(root, 0);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int colIdx = colMap.get(cur);
            List<Integer> curList = resMap.getOrDefault(colIdx, new ArrayList<>());
            curList.add(cur.key);
            resMap.put(colIdx, curList);
            if (cur.left != null) {
                queue.offer(cur.left);
                colMap.put(cur.left, colIdx - 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                colMap.put(cur.right, colIdx + 1);
            }
        }
        return toList(resMap);
    }

    private List<List<Integer>> toList(Map<Integer, List<Integer>> resMap) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int colIdx : resMap.keySet()) {
            minHeap.offer(colIdx);
        }
        while (!minHeap.isEmpty()) {
            res.add(resMap.get(minHeap.poll()));
        }
        return res;
    }

    // Time O(n)
    // Space O(n)
    public List<List<Integer>> verticalPrintI(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Pair>> map = new HashMap<>();
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(root, 0, 0));
        int leftMostCol = 0;
        int rightMostCol = 0;
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();
            leftMostCol = Math.min(leftMostCol, curr.x);
            rightMostCol = Math.max(rightMostCol, curr.x);
            if (!map.containsKey(curr.x)) {
                map.put(curr.x, new ArrayList<>());
            }
            map.get(curr.x).add(curr);
            if (curr.node.left != null) {
                queue.offer(new Pair(curr.node.left, curr.x - 1, curr.y + 1));
            }
            if (curr.node.right != null) {
                queue.offer(new Pair(curr.node.right, curr.x + 1, curr.y + 1));
            }
        }
        for (int i = leftMostCol; i <= rightMostCol; i++) {
            Collections.sort(map.get(i), new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    // when y is equal, sort it by value
                    if (o1.y == o2.y) {
                        return o1.node.key - o2.node.key;
                    }
                    //otherwise don't change the order as BFS guarantees that top nodes are visited first
                    return 0;
                }
            });
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < map.get(i).size(); j++) {
                list.add(map.get(i).get(j).node.key);
            }
            result.add(list);
        }
        return result;
    }

    class Pair {
        TreeNode node;
        int x;  // horizontal
        int y;  // depth

        public Pair(TreeNode node, int x, int y) {
            this.node = node;
            this.x = x;
            this.y = y;
        }
    }
}
