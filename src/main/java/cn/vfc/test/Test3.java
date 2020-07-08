package cn.vfc.test;

public class Test3 {
    public static void main(String[] args) {
        byte[] a = {20,50};
        String s = new String(a);
        System.out.println(NumberStringUtil.bytesToHexString(a));
        System.out.println(s);
    }
}
