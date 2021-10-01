package com.company;

import java.util.Arrays;

public class Heapify {
    public static void main(String[] args) {
        Heapify s = new Heapify();

        int[] array = new int[] {3, 5, 2, 1};
        System.out.println(Arrays.toString(s.heapify(array)));
        // [1, 3, 2, 5]

        array = new int[] {-3, 1, 5, 2, 0, -10};
        System.out.println(Arrays.toString(s.heapify(array)));
        // [-10, 0, -3, 2, 1, 5]
    }

    // Heap is a (binary) tree based data structure
    // Across the entire tree, the relation between a parent node and a child node stays consistent
    // Example: min heap
    // the parent node is always <= its two child nodes (parent node is the smallest node in the subtree rooted at itself).
    // the relation between the two child nodes can differ.
    // The common implementation of a heap is using a complete binary tree
    // A “complete binary tree” is a binary tree in which every level, except possibly the last level,
    // is completely filled, and all nodes are as far left as possible
    // for each node that has at least one child, we perform percolateDown() action, in the order of from the nodes on the deepest level to the root.
    // 原理：当一个node左子树也是堆，右子树也是堆，对它本身做 percolateDown, 会使得以它为root的整棵子树成为堆
    // the range of indices need to perform percolateDown() is: [0, n / 2 - 1]
    // last node’s index: n - 1
    // last node’s parent index: ((n - 1) - 1) / 2 = (n - 2)/2 = n / 2 - 1
    // 1. for every element from [0, n / 2 - 1], from right to left, percolate down
    // 2. for every single node, left subtree && right subtree both heap, percolate itself, the whole subtree is a heap
    // Time O(n)
    // Space O(1)
    // k level full tree: (k = 4)
    // n total number of nodes: 2^k - 1
    // S: 向下比较次数总和
    // sum (each level # of nodes * max # of swap for each node)
    // percolateDown 点最多的层  swap的次数最少
    //          # of nodes in current level     max # of swaps when percolate down
    // level 0:   2^0                             k - 1
    // level 2:   2^1                             k - 2
    // ...
    // level k-1: 2^(k-2)                       1
    // level k:   2^(k-1)                       0
    // S(k) = 2^0 * (k-1) + 2^1 * (k-2) + ... + 2^(k-2) * 1
    // 2S(k) =              2^1 * (k-1) + ... + 2^(k-2) * 1 + 2^(k-1) * 1
    // 2S(k)-S(k) = -2^0 * (k-1) + 2^1 + 2^2 ...2^(k-2) * 1 + 2^(k-1) * 1
    // S(k) = -2^0 * (k-1) + 2^1 + 2^2 ...2^(k-2) * 1 + 2^(k-1) * 1
    // S(k) = -(k-1) + sum(2^i)   1<=i<=k-1
    // S(k) = -(k-1) + 2(1-2^(k-1))/(1-2)
    // S(k) = -(k-1) - 2(1-2^(k-1))
    // S(k) = -k + 1 - 2 + 2^(k)
    // S(k) = 2^(k) - k + 1
    // S(k) = n - log2(n) + 1 ~ O(n)
    public int[] heapify(int[] array) {
        int lastParentIdx = (array.length - 2) / 2;
        for (int i = lastParentIdx; i >= 0; i--) {
            percolateDown(array, i);
        }
        return array;
    }

    private void percolateDown(int[] array, int i) {
        while (2 * i + 1 < array.length) {
            int leftIdx = 2 * i + 1;
            int rightIdx = 2 * i + 2;
            // smallest one among left child and right child
            int swapCandidate = leftIdx;
            // update swapCandidate if right child exist and it is smaller than left child
            if (rightIdx <= array.length - 1 && array[leftIdx] >= array[rightIdx]) {
                swapCandidate = rightIdx;
            }
            // swap if necessary;
            if (array[i] > array[swapCandidate]) {
                swap(array, i, swapCandidate);
                i = swapCandidate;
            } else {
                break;
            }
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
