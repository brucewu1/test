package cn.vfc.list;

import java.util.HashSet;

public class MyAtoi {
    public static int MyAtoi(String str){
        str = str.trim();
        if(str == null || str == ""){
            return 0;
        }
        int sign = 1;
        int start = 0;
        long res = 0;
        if(str.startsWith("+")){
            sign = 1;
            start++;
        }
        if(str.startsWith("-")){
            sign = -1;
            start++;
        }
        for (int i=start;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                return (int)res*sign;
            }
            res = res  * 10 + str.charAt(i) - '0';
        }
        if(sign == 1 && res>Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if(sign == -1 && res < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }
        return (int)res * sign;
    }

    public static void main(String[] args) {
        String s = "+";
        System.out.println(MyAtoi(s));
    }
}
