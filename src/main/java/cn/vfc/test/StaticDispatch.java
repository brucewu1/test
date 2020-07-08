package cn.vfc.test;

public class StaticDispatch {
    static abstract class Human{};
    static class Man extends Human{};
    static class Woman extends Human{};
    public void sayHello(Human gay){
        System.out.println("say gay");
    }
    public void sayHello(Man man){
        System.out.println("say man");
    }
    public void sayHello(Woman woman){
        System.out.println("say woman");

    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello((Man) man);
        sr.sayHello((Woman) woman);
    }
}
