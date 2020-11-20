package Generics;

public class GenericMethod2 {
    public static void main (String args[]) {
        MyPair<String, Integer> p1 = new MyPair<>("a", 1);
        MyPair<String, Integer> p2 = new MyPair<String, Integer>("b", 2);
        MyPair<String, Integer> p3 = new MyPair<>("a", 1);

        System.out.println(p1 + " compares with " + p2 + " : " + Util.myequal(p1, p2)); // false
        System.out.println(p1 + " compares with " + p3 + " : " +
                Util.<String, Integer>myequal(p1, p3)); // true
    }
}