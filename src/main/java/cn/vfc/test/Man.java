package cn.vfc.test;

import java.util.List;

public class Man extends Person{
    String man = "wo shi man";
}
class Person{
    String person = "wo shi person";
}
class Test{
    public static void main(String[] args) {
        Man man = new Man();
        Person person = new Person();
        Person p = new Man();
        System.out.println(person.getClass() instanceof Class);
        System.out.println(man instanceof Person);
        System.out.println(p instanceof Man);
        System.out.println(p instanceof Person);
        System.out.println(((Man)p).man);
    }
}