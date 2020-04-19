package com.sundi.springbootdemo4.common.util;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 图片转base64
 *
 * @author wangyubing
 * @date 2020/4/10
 */
public class ImageToBase64 {


    /**
     * 网络图片转换Base64的方法
     *
     * @param netImagePath     
     */
    public static synchronized String netImageToBase64(String netImagePath) {
        HttpURLConnection conn = null;
        InputStream is = null;
        String strNetImageToBase64 = null;
        try {
            final ByteArrayOutputStream data = new ByteArrayOutputStream();
            // 创建URL
            URL url = new URL(netImagePath);
            final byte[] by = new byte[1024];
            // 创建链接
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            strNetImageToBase64 = encoder.encode(data.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return strNetImageToBase64;
    }
}
