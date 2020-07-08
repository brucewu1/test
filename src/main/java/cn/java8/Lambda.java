package cn.java8;

import java.util.*;

public class Lambda {
    public static void main(String[] args) {
        /*List<String> list = Arrays.asList("bbb","aaa","ccc");
        Collections.sort(list, (String a,String b)-> b.compareTo(a));
        System.out.println(list);*/
        /*Converter<Integer,String> converter = (f-> Integer.valueOf(f));
        Integer convert = converter.convert("123");
        System.out.println(convert.getClass());*/
        Something something = new Something();
        Converter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // "J"
        /*String java = something.startsWith("java");
        System.out.println(java);*/
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));//val0 val1 val2 val3 val4 val5 val6 val7 val8 val9
        // 测试 Filter(过滤)
        List<String> stringList =  Arrays.asList("ccc","ddd");
        stringList
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);//aaa2 aaa1

    }
}
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

