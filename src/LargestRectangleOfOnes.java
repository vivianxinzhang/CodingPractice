import com.company.Solution;
import java.util.*;

public class LargestRectangleOfOnes {
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
