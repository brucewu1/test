package cn.vfc.test;

import java.util.LinkedList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<Object> list = new LinkedList<>();
        list.add("sdfj");
        list.add("bbb");
        list.add("cccc");
        System.out.println(list.get(0));
        System.out.println(list.subList(1, 3));
    }

}
