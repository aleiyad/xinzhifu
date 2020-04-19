package com.sundi.springbootdemo4.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangyubing
 * @date 2020/4/11
 */
public class DateUtil {

    public static String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static String getNowDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMddHHmmss);
        return formatter.format(date);
    }
}
