package com.bus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static String getTime(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        return year+" - "+month+"- "+day+" "+hour+" : "+minute+" : "+second;
    }
    /*
    * @param parttern :需要提供格式经时间的格式:例yyyy-MM-dd HH : mm : ss
      @return : 格式化之后的时间（(字符串)
    * */

    public static String getTime(String parttern){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        return format.format(date);
    }
    /*
    * @oaram source :要被转换的时间
      @param parttern 转换格式
      @return 转换后，得到的java Date类的对象

    */
    public static Date stringToDate(String source,String parttern){
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        try {
            return format.parse(source);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String dateToString(Date date , String parttern){
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        return format.format(date);
    }
//    public static void main( String[ ] args) {
//        Date date = new Date();
//        System.out.println(date);
//        Calendar cal = Calendar.getInstance();cal.setTime(date);
//        cal.set(Calendar.MONTH,cal.get(Calendar . MONTH) + 1);
//        Date newDate = cal.getTime( );
//        System.out.println(newDate);
//    }





}
