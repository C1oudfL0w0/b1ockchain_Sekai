package com.example.Sekai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{


    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String parseDateToStr(final String format, final Date date){
        return new SimpleDateFormat(format).format(date);
    }

}
