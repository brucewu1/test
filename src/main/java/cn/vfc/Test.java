package cn.vfc;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String a = "node.00001";
        int i = a.lastIndexOf("node.");
        i += "node.".length();
        System.out.println(i);
        System.out.println(a.substring(i));
    }


}
