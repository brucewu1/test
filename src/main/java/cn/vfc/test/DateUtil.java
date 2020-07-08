package cn.vfc.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 对日期字符串加上天数
     * @param dateStr 时间格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss
     * @param field 时间字段
     * @param amount 增加的数量
     * @return 与输入一致的时间格式
     */
    public static String addDate(String dateStr,int field,int amount)
    {
        String s=null;
        try
        {
            SimpleDateFormat dateformat=null;
            dateformat=new SimpleDateFormat("yyyyMMdd");
            Date date=dateformat.parse(dateStr);
            Calendar cal=Calendar.getInstance();
            cal.setTime(date);
            cal.add(field, amount);
            s=dateformat.format(cal.getTime());
        }
        catch(Exception e)
        {
            s=null;
        }
        return s;
    }
}
