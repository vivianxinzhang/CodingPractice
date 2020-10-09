package com.company;

import java.util.*;

public class VerticalListOfBinaryTree {
    public static void main(String[] args) {
        VerticalListOfBinaryTree s = new VerticalListOfBinaryTree();
        TreeNode two = new TreeNode(2);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        two.left = one;
        two.right = three;
        one.right = four;
        System.out.println(s.verticalPrint(two));
    }

    // Time O(n)
    // Space O(n)
    public List<List<Integer>> verticalPrint(TreeNode root) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<Integer, List<Pair>> map = new HashMap<>();
        Queue<Pair> queue = new ArrayDeque<>();
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
