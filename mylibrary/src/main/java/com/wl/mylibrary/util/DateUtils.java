package com.wl.mylibrary.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * @author ymx
 */
public class DateUtils {

    public static final SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat df3 = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat df4 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    public static final SimpleDateFormat df5 = new SimpleDateFormat("Gyyyy年MM月dd日");
    public static final SimpleDateFormat df6 = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat df7 = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat df8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat df9 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat df10 = new SimpleDateFormat("MMM d,yyyy h:m:s aa", Locale.ENGLISH);
    public static final SimpleDateFormat df11 = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat df12 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    public static final SimpleDateFormat df13 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 将Date转为字符串日期
     *
     * @param df
     * @param date
     * @return
     */
    public static String date2String(DateFormat df, Date date) {
        return df.format(date);
    }

    /**
     * 将字符串日期转为Date
     *
     * @param df
     * @param date
     * @return
     */
    public static Date string2Date(DateFormat df, String date) {
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long string2Long(DateFormat df, String date){
        return string2Date(df,date).getTime();
    }

    /**
     * 日期格式转换
     *
     * @param df
     * @param date
     * @return
     */
    public static String getStringDate(DateFormat df, String date) {
        return df.format(new Date(date));
    }

    /**
     * 从一种日期格式转换成另一种日期格式
     * @param df1
     * @param df2
     * @param date
     * @return
     */
    public static String stringDatedf2df(DateFormat df1,DateFormat df2,String date){
        return df2.format(string2Date(df1,date));
    }

    /**
     * 日期转换成最近。。。
     *
     * @param timesamp
     * @return
     */
    public static String getDay(Date timesamp) {
        if (timesamp == null) {
            return "未";
        }
        String result = "未";
        SimpleDateFormat sdf = new SimpleDateFormat("DD");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = timesamp;
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天 " + date2String(df7, timesamp);
                break;
            case 1:
                result = "昨天 " + date2String(df7, timesamp);
                break;
            case 2:
                result = "前天 " + date2String(df7, timesamp);
                break;

            default:
                result = temp + "天前 " + date2String(df7, timesamp);
                break;
        }
        return result;
    }

    public static int[] getDayAndhourAndmin(long time){
        int[] times = new int[2];
        times[0] = (int) (time/1000/60/60/24);
        times[1] = (int) (time - times[0]*1000*60*60*24)/1000/60/60;
        //times[2] = (int) (time - times[0]*1000*60*60*24-times[1]*1000*60*60)/1000;
        return times;
    }

    public static Date plusDate(String date,int day){
        return new Date(new Date(date).getTime()+day*24*60*60*1000);
    }

}