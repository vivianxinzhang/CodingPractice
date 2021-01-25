package DailyChallenge;
import java.util.*;

public class SortTheMatrixDiagonally {
    public static void main(String[] args) {
        SortTheMatrixDiagonally s = new SortTheMatrixDiagonally();

        int[][] matrix = new int[][] {
                {3, 3, 1, 1},
                {2, 2, 1, 2},
                {1, 1, 1, 2}
        };
        print(matrix);
        System.out.println();
        int[][] result = s.diagonalSort(matrix);
        print(result);
    }

    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // diagonal
    // matrix: m*n   # of diagonals: m+n-1
    // how to represent diagonals
    // cells on the same diagonal has same i-j, range [-(n-1), m-1]
    // The longest diagonal contains not more than \min(N, M)min(N,M) elements.
    // Time O(mn*log(min(m,n)))
    // Space O(mn)
    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return mat;
        }
        int m = mat.length;
        int n = mat[0].length;
        // key: diagonal represented by i-j
        // value: cell value
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                PriorityQueue<Integer> minHeap = map.getOrDefault(i - j, new PriorityQueue<>());
                minHeap.offer(mat[i][j]);
                map.put(i - j, minHeap);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }
        return mat;
    }
}
