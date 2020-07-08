package cn.vfc.decorator;

import java.io.File;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        Beverage b2 = new HouseBlend();
        b2 = new Milk(b2);
        System.out.println(b2.getDescription() + "$" + b2.cost());
        Class<Test> testClass = Test.class;
        String name = testClass.getName();
        System.out.println(name);
    }
}
