package com.naiive.zhihudaily.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangzhe on 16/5/23.
 */
public class DateUtil {

    public static Date dayBefore(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,-24);
        return calendar.getTime();
    }

    public static String date2String(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

}
