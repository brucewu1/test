package cn.vfc.test;

import java.util.LinkedList;

public class TestException {
    public static void main(String[] args){
        LinkedList<Object> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        StringBuilder sb = new StringBuilder();
        int i = 2;
        for (Object o : list){
            if(i==4){
                i++;
                continue;
            }
            sb.append((String)o);
            i++;
        }
        System.out.println(sb.toString());
        System.out.println(i);
    }
}
