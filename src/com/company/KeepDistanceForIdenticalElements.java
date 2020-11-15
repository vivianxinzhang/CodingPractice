package com.company;
import java.util.*;

public class KeepDistanceForIdenticalElements {
    public static void main(String[] args) {
        KeepDistanceForIdenticalElements s = new KeepDistanceForIdenticalElements();
        int[] result = s.keepDistance(1);
        System.out.println(Arrays.toString(result));

        result = s.keepDistance(2);
        System.out.println(Arrays.toString(result));

        result = s.keepDistance(3);
        System.out.println(Arrays.toString(result));
    }

    public int[] keepDistance(int k) {
        // Assumptions: k > 0
        int[] array = new int[2 * k];
        for (int i = 0; i < k; i++) {
            array[i * 2] = i + 1;
            array[i * 2 + 1] = i + 1;
        }
        // used[i] == true if and only if i is used once
        boolean[] used = new boolean[k + 1];
        return helper(array, 0, used) ? array : null;
    }

    private boolean helper(int[] array, int index, boolean[] used) {
        if (index == array.length) {
            return true;
        }
        for (int i = index; i < array.length; i++) {
            int cur = array[i];
            if (!used[cur] || (index > cur && array[index - cur - 1] == cur)) {
                swap(array, index, i);
                used[cur] = !used[cur];
                if (helper(array, index + 1, used)) {
                    return true;
                }
                swap(array, index, i);
                used[cur] = !used[cur];
            }
        }
        return false;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
