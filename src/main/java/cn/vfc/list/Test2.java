package cn.vfc.list;

import java.util.*;

public class Test2 {
    public static int lengthOfLongestSubstring(String s) {
        int l = s.length();
        int n = 0;
        for(int i = 0;i<l;i++){
            for (int j=i+1;j<=l;j++){
                if(allUnique(s,i,j)){
                  n =   Math.max(n,j-i);
                }
            }
        }
        return n;
    }
    public static String longestPalindrome(String s) {
        int n = s.length();
        int max = 0;
        String substring = "";
        for(int i = 0;i<n;i++ ){
            for(int j=i+1;j<=n;j++){
                String test = s.substring(i,j);
                if(isHuiWen(s,i,j) && test.length()>max){
                    substring = s.substring(i, j);
                    max = Math.max(max,substring.length());
                }
            }
        }
        return substring;
    }

    private static boolean isHuiWen(String s, int i, int j) {
        boolean flag = false;
        while (true){
            if(i>=j){
                break;
            }
            if(s.charAt(i) != s.charAt(j-1)){
                break;
            }
            i++;
            j--;
        }
        flag = true;
        return flag;
    }

    public static boolean allUnique(String s, int start, int end) {
        HashSet<Object> set = new HashSet<>();
        for (int i=start;i<end;i++){
            if(set.contains(s.charAt(i))){
                return false;
            }
            set.add(s.charAt(i));

        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(longestPalindrome(s));
    }
}
