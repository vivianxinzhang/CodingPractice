package com.company;
import java.util.*;

public class ConsecutiveDaysToWork {
    public static void main(String[] args) {
        ConsecutiveDaysToWork s = new ConsecutiveDaysToWork();
        int[] array = new int[] {2, -1, 4, -2, 1};
        s.workSchedule(12, 10);
    }

    // Method 1:
    // Time
    // Space
    public List<String> workSchedule(int totalDays, int workDays) {
        List<String> result = new ArrayList<>();
        int[] array = new int[totalDays];
        dfs(array, 0, 0, 0, result);
        return result;
    }

    private void dfs(int[] array, int index, int conseDaysWorked, int total, List<String> result) {
        if (index == array.length) {
            if (total == 10) {
                System.out.println(Arrays.toString(array));
            }
            return;
        }
        if (total == 10) {
            System.out.println(Arrays.toString(array));
            return;
        }
        // not work
        dfs(array, index + 1, 0, total, result);
        // work
        if (conseDaysWorked < 5) {
            array[index] = 1;
            dfs(array, index + 1, conseDaysWorked + 1, total + 1, result);
            array[index] = 0;
        }
    }
}
