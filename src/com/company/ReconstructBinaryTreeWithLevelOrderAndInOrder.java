package com.company;
import java.util.*;

public class ReconstructBinaryTreeWithLevelOrderAndInOrder {
    // Time O()
    // Space O()
    public TreeNode reconstruct(int[] in, int[] level) {
        // Assumptions: level, in are not null
        // there is no duplicate in the binary tree
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < in.length; i++) {
            inMap.put(in[i], i);
        }
        List<Integer> lList = new ArrayList<>();
        for (int num : level) {
            lList.add(num);
        }
        return helper(lList, inMap);
    }

    private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
        if (level.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(level.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (inMap.get(num) < inMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inMap);
        root.right = helper(right, inMap);
        return root;
    }
}
