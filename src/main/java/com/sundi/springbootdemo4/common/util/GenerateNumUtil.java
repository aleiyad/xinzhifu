package com.sundi.springbootdemo4.common.util;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
public class GenerateNumUtil {


    private final static char[] BASE_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final static AtomicInteger SEQ2 = new AtomicInteger(Integer.MAX_VALUE);
    private final static AtomicInteger SEQ3 = new AtomicInteger(Integer.MAX_VALUE);

    private static final Random random = new Random();

    /**
     * 获取订单号
     *
     * @return
     */
    public static synchronized String nextseq2() {
        int nextSequence = SEQ2.incrementAndGet();
        if (nextSequence >= 0) {
            nextSequence = Integer.MAX_VALUE - nextSequence;
        } else {
            nextSequence = Integer.MAX_VALUE + 1 + nextSequence;
        }
        int mod = nextSequence % (BASE_CHARS.length * BASE_CHARS.length * BASE_CHARS.length);
        int mod1 = nextSequence % (BASE_CHARS.length * BASE_CHARS.length);
        return BASE_CHARS[mod / (BASE_CHARS.length * BASE_CHARS.length)] + "" + BASE_CHARS[mod1 / BASE_CHARS.length] + "" + BASE_CHARS[mod1 % BASE_CHARS.length];
    }

    private synchronized static String nextseq3() {
        int nextSequence = SEQ3.incrementAndGet();
        if (nextSequence >= 0) {
            nextSequence = Integer.MAX_VALUE - nextSequence;
        } else {
            nextSequence = Integer.MAX_VALUE + 1 + nextSequence;
        }
        int mod = nextSequence % (BASE_CHARS.length * BASE_CHARS.length * BASE_CHARS.length);
        int mod1 = nextSequence % (BASE_CHARS.length * BASE_CHARS.length);
        return BASE_CHARS[mod / (BASE_CHARS.length * BASE_CHARS.length)] + "" + BASE_CHARS[mod1 / BASE_CHARS.length] + "" + BASE_CHARS[mod1 % BASE_CHARS.length];
    }


    /**
     * 16位流水编码
     * 格式：13位时间串+3位顺序码,保证流水的唯一性
     * 毫秒时间内并发达到62*62*62=238328这个数量编号才会重复 （目前适合单机，集群可以考虑添加机器标示码）
     * 流水号
     *
     * @return
     */
    public static String operationNo() {
        return System.currentTimeMillis() + nextseq3();
    }

    /**
     * 订单号
     *
     * @return
     */
    public static String orderId() {
        return DateUtil.getNowDate(new Date()) + nextseq2();
    }

    /**
     * 外部请求号
     *
     * @return
     */
    public static String outRequestNo() {
        int num = random.nextInt(999);
        String str = String.format("%03d", num);
        return DateUtil.getNowDate(new Date()) + str;
    }
}
