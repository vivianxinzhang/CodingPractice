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

    public static class LargestRectangleOfOnes {
        public static void main(String[] args) {
            LargestRectangleOfOnes s = new LargestRectangleOfOnes();
            int[][] matrix = new int[][] {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 1, 1, 1},
                    {1, 0, 1, 1} };
            System.out.println(s.largest(matrix));
        }

        // Time O(mn)
        // Space O(mn)
        public int largest(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            // Step 1: find longest consecutive 1s from top to bottom - O(mn)
            int[][] M = preProcess(matrix);
            // Step 2: enumerate all the bottom line and find the largest rectangle in the histogram
            int largest = 0;
            for (int i = 0; i < M.length; i++) {
                // find the largest
                int currMax = findMax(M[i]);
                largest = Math.max(largest, currMax);
            }
            return largest;
        }

        // Time O(n)
        // Space O(n)
        private int findMax(int[] array) {
            // Assumptions: array is not null, array.length >= 1
            // all the values in the array are non-negative
            int result = 0;
            // Note that the stack contains the "index", not the "value" of the array
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i <= array.length; i++) {
                // we need a way of popping out all the elements in the stack at last,
                // so that we explicitly add a bar of height 0
                int cur = i == array.length ? 0 : array[i];
                while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
                    int height = array[stack.pollFirst()];
                    // determine the left boundary of the largest rectangle
                    // with height array[i]
                    int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                    // determine the right boundary of the largest rectangle with height of the popped element
                    result = Math.max(result, height * (i - left));
                }
                stack.offerFirst(i);
            }
            return result;
        }

        private int[][] preProcess(int[][] matrix) {
            int[][] M = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (i == 0) {
                        M[i][j] = matrix[i][j];
                    } else if (matrix[i][j] == 0){
                        M[i][j] = 0;
                    } else {
                        M[i][j] = M[i - 1][j] + 1;
                    }
                }
            }
            return M;
        }
    }
}
