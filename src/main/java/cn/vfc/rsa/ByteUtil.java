package cn.vfc.rsa;

import cn.vfc.test.NumberStringUtil;

public class ByteUtil {
    /**
     * int转为16进制字符串(自动补位)
     */
    public static String toHexString(int data) {
        String temp = Integer.toHexString(data);
        if(temp.length() % 2 != 0) {
            temp = "0" + temp;
        }
        return temp.toUpperCase();
    }

    public static void main(String[] args) throws Exception{
        String s = Integer.toHexString(495);
        System.out.println(s);
        byte[] bytes = NumberStringUtil.hexStringToBytes("56312E3120");
        System.out.println(bytes.length);
        System.out.println(new String(bytes));
    }
}
