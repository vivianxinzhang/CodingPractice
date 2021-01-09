package TopPractice;

import com.company.Solution;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray s = new FindAllNumbersDisappearedInAnArray();
        int[] array = new int[] {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(s.findDisappearedNumbersI(array));
        System.out.println(s.findDisappearedNumbers(array));
    }


    // 【1， 4， 3， 2】
    // 1 ——> index 0       [-1, 4, 3, 2]
    // 4 ——> index 3       [-1, 4, 3, -2]
    // 3 ——> index 2
    // 2 ——> index 1
    // 【1， 2， 3， 4】
    // Time O(n)
    // Space O(1)
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int currNum = Math.abs(nums[i]);
            // nums[i] should go to position nums[i] - 1
            int currIdx = currNum - 1;
            // mark seen numbers corresponding idx negative
            nums[currIdx] = nums[currIdx] < 0 ? nums[currIdx] : -nums[currIdx];
        }
        for (int i = 0; i < nums.length; i++) {
            // places not marked negative represents missing numbers
            if (nums[i] > 0 ) {
                int currNum = i + 1;
                result.add(i + 1);
            }
        }
        return result;
    }

    // Time O(n)
    // Space O(n)
    public List<Integer> findDisappearedNumbersI(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] array = new int[nums.length + 1];
        for (int num : nums) {
            array[num] = 1;
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
