package cn.java8;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        String aa = "djfdsjajfaaaaa";

        Map<Character, Integer> map = countStr(aa);
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        for(Map.Entry entry : entries){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        countStr1(aa);
    }

    public static Map<Character, Integer> countStr(String str){
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        return map;
    }

    public static void countStr1(String str){
        int max_length = 0;
        String max_char = "";
        while (str.length()>0){
            String s = str.substring(0, 1);
            String s1 = str.replaceAll(s, "");
            if(max_length < str.length() - s1.length()){
                max_length = str.length() - s1.length();
                str = s1;
                max_char = s;
            }else {
                str = s1;
            }
        }
        System.out.println(max_char+":"+max_length);
    }
}
