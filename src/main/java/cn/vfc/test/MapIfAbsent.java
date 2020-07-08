package cn.vfc.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapIfAbsent {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        /*List<String> list = map.get("list");
        if(null == list){
            list = new LinkedList<>();
            map.put("list",list);
        }
        list.add("one");
        System.out.println(map.get("list").get(0));*/
        LinkedList<String> list = new LinkedList<>();
        list.add("two");
        map.put("list",list);
        List<String> list1 = map.computeIfAbsent("list", k -> new LinkedList<>());
        list1.add("one");
        System.out.println(map.get("list").get(1));
    }
}
