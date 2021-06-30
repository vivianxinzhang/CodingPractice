package com.company;

public class StudentAttendanceI {
    public static void main(String[] args) {
        StudentAttendanceI s = new StudentAttendanceI();

        System.out.println(s.checkAttendanceRecord("LPPPPPPLPL"));  // true
        System.out.println(s.checkAttendanceRecord("LPPAPPPLPL"));  // true
        System.out.println(s.checkAttendanceRecord("APPAPLPL"));    // false
    }

    // 'L' = late   'A' = absent    'P' = present
    // A student could win a reward if he/she meets the following requirements:
    // 1. There is at most one 'A' in the attendance record.
    // 2. The longest continuous late ('L') records should be equal or less than 2.
    // Time O(n)
    // Space O(1)
    public boolean checkAttendanceRecord(String input) {
        int countA = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'A') {
                countA++;
            } else if (input.charAt(i) == 'L' && (i >= 2 && input.charAt(i - 1) == 'L' && input.charAt(i - 2) == 'L')) {
                return false;
            }
            if (countA > 1) {
                return false;
            }
        }
        return true;
    }
}
