package Contest;
import java.util.Arrays;
import java.util.Comparator;

public class MaximumUnitsOnATruck {
    public static void main(String[] args) {
        MaximumUnitsOnATruck s = new MaximumUnitsOnATruck();
        int[][] boxTypes = new int[][] {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        System.out.println(s.maximumUnits(boxTypes, truckSize));
    }

    // M[i] maximum total number of units that can be put on the truck with size i.
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return 0;
                }
                return o1[1] < o2[1] ? 1 : -1;
            }
        });
        int sum = 0;
        for (int[] boxType : boxTypes) {
            int numOfBox = boxType[0];
            int countPerBox = boxType[1];
            for (int i = 0; i < numOfBox; i++) {
                sum += countPerBox;
                truckSize--;
                while (truckSize == 0) {
                    return sum;
                }
            }
        }
        return sum;
    }

    // [[184,129],[120,503],[116,562],[226,615],[492,75],[687,650],[16,135],[398,492],[348,614],[246,441]]
    // 5725
    // branching factor: maximum # of boxes m   levels: type of boxes n
    // Time O(n^m)
    // Space O(n)
    public int maximumUnitsI(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return 0;
                }
                return o1[1] < o2[1] ? 1 : -1;
            }
        });
        int[] max = new int[1];
        int currSum = 0;
        dfs(boxTypes, 0, truckSize, currSum, max);
        return max[0];
    }

    private void dfs(int[][] boxTypes, int index, int truckSize, int currSum, int[] max) {
        if (index == boxTypes.length) {
            if (truckSize >= 0) {
                max[0] = Math.max(currSum, max[0]);
            }
            return;
        }
        if (truckSize == 0) {
            max[0] = Math.max(currSum, max[0]);
            return;
        }
        // considering boxTypes[index]
        // count: boxTypes[index][0]  size: boxTypes[index][1]
        // count of current boxTypes
        for (int i = 0; i <= boxTypes[index][0]; i++) {
            currSum += i * boxTypes[index][1];
            truckSize -= i;
            dfs(boxTypes, index + 1, truckSize, currSum, max);
            truckSize += i;
            currSum -= i * boxTypes[index][1];
        }
    }
}
