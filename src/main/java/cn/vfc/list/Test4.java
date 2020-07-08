package cn.vfc.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test4 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 中心扩展法求最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        int start = 0,end = 0;
        for(int i=0;i<len;i++){
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int max = Math.max(len1, len2);
            if(max > end-start){
                start = i-(max-1)/2;
                end = i+(max)/2;
            }
        }
        return s.substring(start,end+1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left,R = right;
        while(L >=0 && R<s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R-L-1;
    }

    /**
     * Z字符串解法一
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    /**
     * Z字符串解法二
     * @param s
     * @param num
     * @return
     */
    public static String convert1(String s,int num){
        if(num < 2){
            return s;
        }
        ArrayList<StringBuilder> list = new ArrayList<>();
        for (int i=0;i<num;i++) list.add(new StringBuilder());
        int i = 0,flag = -1;
        for(Character c :s.toCharArray()){
            list.get(i).append(c);
            if(i == 0 || i == (num - 1)){
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder sb : list){
            stringBuilder.append(sb);
        }
        return stringBuilder.toString();
    }

    public static  int revert(int n){
        String s = String.valueOf(n);
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=l-1;i>=0;i--){
            sb.append(s.charAt(i));
        }
        boolean isFH = false;
        String substring = "";
        if(sb.toString().endsWith("-")){
            isFH = true;
            substring = sb.toString().substring(0, sb.toString().indexOf("-"));
        }
        Integer integer = Integer.valueOf(substring);
        if(isFH == true){
            integer = -integer;
        }
        return integer;
    }


    public static void main(String[] args) {
        int n = -12340000;
        System.out.println(revert(n));
    }
}
