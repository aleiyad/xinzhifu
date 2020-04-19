package com.sundi.springbootdemo4.common.util;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0 && str.trim().length() > 0;
    }

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }
}
