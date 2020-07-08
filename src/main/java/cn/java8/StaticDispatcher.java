package cn.java8;


public class StaticDispatcher {
    static class Human{
        public void sayHello(){
            System.out.println("hello human");
        }
    }
    static class Man extends Human{
        public void sayHello(){
            System.out.println("man");
        }
    }
    static class Woman extends Human{
        public void sayHello(){
            System.out.println("woman");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        //StaticDispatcher sd = new StaticDispatcher();
        man.sayHello();
        woman.sayHello();
    }
}

