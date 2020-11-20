package Generics;

import java.util.ArrayList;

public class genericTypeErasure {
    public static void main (String args[]) {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<String> a2 = new ArrayList<String>();
        Class c1 = a1.getClass();
        Class c2 = a2.getClass();
        System.out.println(c1 == c2);   // 判定两个类型相同
        System.out.println(c1);     // class java.util.ArrayList
        try {
            a2.getClass()
                    .getMethod("add", Object.class)
                    .invoke(a2, "a");    // 成功将字符串对象插入整数数组
            System.out.println(a2.size());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
