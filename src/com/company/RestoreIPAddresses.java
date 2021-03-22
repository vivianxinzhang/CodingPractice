package com.company;
import java.util.*;

public class RestoreIPAddresses {
    public static void main(String[] args) {
        RestoreIPAddresses s = new RestoreIPAddresses();
        System.out.println(s.Restore("0000"));
        // [0.0.0.0]
        System.out.println(s.Restore("25525511135"));
        // [255.255.11.135, 255.255.111.35]
    }

    // what is valid IP integer: 0 <=  X <= 255
    // valid integer → if there are more than one digit in the integer, the first digit should not be 0
    // 分四层     →  	每层是一个IP的integer
    // 分三个branch   →   每个branch是每个integer可取多少种
    // Implementation 2:
    // Time O(3^4)
    // Space O(4)
    public List<String> RestoreI(String ip) {
        List<String> result = new ArrayList<>();
        if (ip == null || ip.length() == 0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        helper(ip.toCharArray(), 0, 0, sb, result);
        return result;
    }

    private void helper(char[] ip, int level, int offset, StringBuilder sb, List<String> result) {
        if (level == 4) {
            if (sb.length() == ip.length + 4) {
                result.add(sb.substring(0, sb.length() - 1));
            }
            return;
        }
        // current level
        // 1 digit for current level
        if (offset < ip.length) {
            helper(ip, level + 1, offset + 1, sb.append(ip[offset]).append('.'), result);
            sb.delete(sb.length() - 2, sb.length());
        }
        // 2 digits for current level
        if (offset + 1 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            if (a != '0') {
                helper(ip, level + 1, offset + 2, sb.append(a).append(b).append('.'), result);
                sb.delete(sb.length() - 3, sb.length());
            }
        }
        // 3 digits for current level
        if (offset + 2 < ip.length) {
            char a = ip[offset];
            char b = ip[offset + 1];
            char c = ip[offset + 2];
            if (a == '1' || a == '2' && b >= '0' && b <= '4' || a == '2' && b == '5' && c >= '0' && c <= '5') {
                helper(ip, level + 1, offset + 3, sb.append(a).append(b).append(c).append('.'), result);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }

    // 目的是把3个点插入进去把String分成4段：
    // pruning:
    // 1. 如果剩余的Number长度不足以给剩下的Integer至少每个数一个digit  或者 远远长于max可分配的长度(每个都是3位数), 直接返回
    // 2. 如果上一个被加进path的integer string转换成int后大于255，或者它是一个首位是0且长度大于1的string，则 直接返回
    // Implementation 1:
    // Time O(3^4)
    // Space O(4)
    public List<String> Restore(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() == 0){
            return result;
        }
        StringBuilder sb = new StringBuilder();
        DFS(result,s,sb,4,0);
        return result;
    }

    private void DFS(List<String> result, String s, StringBuilder sb, int level, int index){
        if(index == s.length()){
            if(level == 0){
                result.add(sb.deleteCharAt(sb.length() - 1).toString());
            }
            return;
        }
        if(level == 0){
            return;
        }
        // start index: index
        // end index: i
        // substring [index, i]
        for(int i = index; i< Math.min(index + 3,s.length()); i++){
            int length = sb.length();
            if(isValid(s.substring(index,i + 1))){
                sb.append(s.substring(index,i + 1)).append('.');
                DFS(result,s,sb,level - 1,i+1);
                sb.setLength(length);
            }
        }
    }

    private boolean isValid(String s){
        if(s.charAt(0) == '0'){
            return s.length() == 1;
        }
        int num = Integer.valueOf(s);
        return num >= 0 && num <= 255;
    }
}
