package cn.vfc.test;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    private static Map<String,Integer> hashtable = new Hashtable<>();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("000000");
        String format = df.format(autoInc1());
        System.out.println(format);
    }

    public static String genDateString(String format){
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static Integer autoInc1(){
        Integer i = atomicInteger.incrementAndGet();
        return i;
    }
}
