package cn.vfc.mode.lishitihua;

import java.util.HashMap;

public class Client {
    public static void main(String[] args) {
        /*Father father = new Father();
        HashMap<Object, Object> map = new HashMap<>();
        father.doSomething(map);*/
        Son son = new Son();
        HashMap<Object, Object> map = new HashMap<>();
        son.doSomething(map);
    }
}
