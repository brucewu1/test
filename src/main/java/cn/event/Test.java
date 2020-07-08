package cn.event;

public class Test {
    public static void main(String[] args) {
        String s = stringToUnicode("BANK1091@");
        System.out.println(System.getenv("ACQUIRER_PATH_ALL"));
        System.out.println(s);
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader.getClass());
    }
    public static String stringToUnicode(String s) {
        String str = "";

        for(int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i);
            str = str + Integer.toHexString(ch).toUpperCase();
        }

        return str;
    }
}
