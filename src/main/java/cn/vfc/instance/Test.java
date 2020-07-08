package cn.vfc.instance;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        System.out.println(b instanceof A);//true
        System.out.println(a instanceof B);//false
        System.out.println(B.class.isAssignableFrom(B.class));
        System.out.println(B.class.getSuperclass());
        System.out.println(B.class.getInterfaces());
        /*B c = (B)a;
        System.out.println(c);*/

    }
}
