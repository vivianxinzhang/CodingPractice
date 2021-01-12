package Contest;
import java.util.*;

public class MinimizeHammingDistanceAfterSwapOperations {
    public static void main(String[] args) {
        MinimizeHammingDistanceAfterSwapOperations s = new MinimizeHammingDistanceAfterSwapOperations();
        System.out.println();
        int[] source = new int[] {5, 1, 2, 4, 3};
        int[] target = new int[] {1, 5, 4, 2, 3};
        int[][] steps = new int[][] {{0, 4}, {4, 2}, {1, 3}, {1, 4}};
        System.out.println(s.minimumHammingDistance(source, target, steps));    // 0

        source = new int[] {1, 2, 3, 4};
        target = new int[] {2, 1, 4, 5};
        steps = new int[][] {{0, 1}, {2, 3}};
        System.out.println(s.minimumHammingDistance(source, target, steps));    // 1

        source = new int[] {1, 2, 3, 4};
        target = new int[] {2, 1, 4, 5};
        steps = new int[][] {};
        System.out.println(s.minimumHammingDistance(source, target, steps));    // 4
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pair : allowedSwaps) {
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        int match = 0;
        List<int[]>  list = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < target.length; j++) {
                if (target[j] == source[i]) {
                    if (i != j) {
                        int[] curr = new int[] {i, j};
                        list.add(curr);
                    } else {
                        match++;
                    }
                }
            }
        }
        int unmatch = source.length - match;
        for (int[] swap : list) {
            if (graph.get(swap[0]).contains(swap[1])) {
                unmatch--;
            }
        }
        return unmatch;
    }
}
