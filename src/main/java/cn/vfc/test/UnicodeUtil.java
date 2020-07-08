package cn.vfc.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeUtil {
    public static String stringToUnicode(String s) {
        String str = "";

        for(int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i);
            str = str + Integer.toHexString(ch).toUpperCase();
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(UnicodeUtil.stringToUnicode("huawei!@#123"));
        System.out.println(UnicodeUtil.unicodeToString("6877776663"));
    }

    public static String unicodeToString(String str) {
        if (str != null && str != "") {
            Pattern pattern = Pattern.compile("([0-9a-fA-F]{2})");
            Matcher matcher = pattern.matcher(str);
            StringBuffer sb = new StringBuffer();

            while(matcher.find()) {
                char ch = (char)Integer.parseInt(matcher.group(1), 16);
                sb.append(ch);
            }

            return sb.toString();
        } else {
            return str;
        }
    }
}
