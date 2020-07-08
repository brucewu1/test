package cn.event;

public class Test1 {
    private static final int DEFAULT = 10000;
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Class<? extends Test1> aClass = test1.getClass();
        System.out.println(aClass);
    }
}
