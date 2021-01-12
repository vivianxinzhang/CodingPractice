package DailyChallenge;
import java.util.*;

public class CreateSortedArraythroughInstructions {
    public static void main(String[] args) {
        CreateSortedArraythroughInstructions s = new CreateSortedArraythroughInstructions();
        int[] array = new int[] {1,5,6,2};
        System.out.println(s.createSortedArray(array)); // 1

        array = new int[] {1,2,3,6,5,4};
        System.out.println(s.createSortedArray(array)); // 3

        array = new int[] {1,3,3,3,2,4,2,1,2};
        System.out.println(s.createSortedArray(array)); // 4
    }

    // Method 3: MergeSort

    // Method 2: Segment Tree

    // Method 1: MergeSort
    // Time O(nlogn)
    // Space O(n)
    int[] smaller;
    int[] larger;
    int[][] temp; // record some temporal information
    public int createSortedArray(int[] instructions) {
        int n = instructions.length;
        smaller = new int[n];
        larger = new int[n];
        temp = new int[n][];
        long cost = 0;
        long MOD = 1000000007;

        int[][] arrSmaller = new int[n][];
        int[][] arrLarger = new int[n][];
        for (int i = 0; i < n; i++) {
            arrSmaller[i] = new int[] { instructions[i], i };
            arrLarger[i] = new int[] { instructions[i], i };
        }

        sortSmaller(arrSmaller, 0, n - 1);
        sortLarger(arrLarger, 0, n - 1);

        for (int i = 0; i < n; i++) {
            cost += Math.min(smaller[i], larger[i]);
        }
        return (int) (cost % MOD);
    }

    private void sortSmaller(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortSmaller(arr, left, mid);
        sortSmaller(arr, mid + 1, right);
        mergeSmaller(arr, left, right, mid);
    }

    private void mergeSmaller(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] < arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                smaller[arr[j][1]] += i - left;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            smaller[arr[j][1]] += i - left;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }

    private void sortLarger(int[][] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        sortLarger(arr, left, mid);
        sortLarger(arr, mid + 1, right);
        mergeLarger(arr, left, right, mid);
    }

    private void mergeLarger(int[][] arr, int left, int right, int mid) {
        // merge [left, mid] and [mid+1, right]
        int i = left;
        int j = mid + 1;
        int k = left;
        // use temp[left...right] to temporarily store sorted array
        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                temp[k++] = arr[i];
                i++;
            } else {
                temp[k++] = arr[j];
                larger[arr[j][1]] += mid - i + 1;
                j++;
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i];
            i++;
        }
        while (j <= right) {
            temp[k++] = arr[j];
            larger[arr[j][1]] += mid - i + 1;
            j++;
        }
        // restore from temp
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}
