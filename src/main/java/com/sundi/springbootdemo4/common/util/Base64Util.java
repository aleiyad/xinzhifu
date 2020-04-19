package com.sundi.springbootdemo4.common.util;

import sun.misc.BASE64Encoder;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
public class Base64Util {

    public static String dataToBase64(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return "data:image/png;base64," + encoder.encode(data).replaceAll("\r\n", "");
    }
}
