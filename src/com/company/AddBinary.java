package com.company;

public class AddBinary {
    public static void main(String[] args) {
        AddBinary s = new AddBinary();
        System.out.println(s.addBinary("11", "1"));     // 100
    }

    // Time O(n)
    // Space O(n)
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carryNum = 0;
        while (i >= 0 && j >= 0) {
            carryNum += a.charAt(i) - '0';
            i--;
            carryNum += b.charAt(j) - '0';
            j--;
            sb.append((char) (carryNum % 2 + '0'));
            carryNum /= 2;
        }
        while (i >= 0) {
            carryNum += a.charAt(i) - '0';
            i--;
            sb.append((char) (carryNum % 2 + '0'));
            carryNum /= 2;
        }
        while (j >= 0) {
            carryNum += b.charAt(j) - '0';
            j--;
            sb.append((char) (carryNum % 2 + '0'));
            carryNum /= 2;
        }
        if (carryNum > 0) {
            sb.append((char)(carryNum + '0'));
        }
        return sb.reverse().toString();
    }
}
