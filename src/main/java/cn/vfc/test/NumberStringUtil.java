package cn.vfc.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class NumberStringUtil {
    public static String addRightChar(String str, int length, char c, String encode) {
        if (str == null) {
            str = "";
        }

        int str_length = 0;

        try {
            str_length = str.getBytes(encode).length;
        } catch (UnsupportedEncodingException var6) {
            var6.printStackTrace();
        }

        for(int i = 0; i < length - str_length; ++i) {
            str = str + c;
        }

        return str;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString != null && !hexString.equals("")) {
            hexString = hexString.toUpperCase();
            int length = hexString.length() / 2;
            char[] hexChars = hexString.toCharArray();
            System.out.println(Arrays.toString(hexChars));
            byte[] d = new byte[length];

            for(int i = 0; i < length; ++i) {
                int pos = i * 2;
                d[i] = (byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
            }

            return d;
        } else {
            return null;
        }
    }
    private static byte charToByte(char c) {
        return (byte)"0123456789ABCDEF".indexOf(c);
    }
    public static String addLeftZero(String str, int length) {
        int str_length = str.length();

        for(int i = 0; i < length - str_length; ++i) {
            str = '0' + str;
        }

        return str;
    }
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString().toUpperCase();
        } else {
            return "";
        }
    }

    public static void main(String[] args) throws Exception{
        String a = "0";
        String b = NumberStringUtil.bytesToHexString(a.getBytes());
        System.out.println(b);
        byte[] bytes = NumberStringUtil.hexStringToBytes("3232aa");
        System.out.println(NumberStringUtil.bytesToHexString(bytes));
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
        System.out.println(Integer.parseInt(new String(bytes)));
        String obj =(String)null;

    }
}
