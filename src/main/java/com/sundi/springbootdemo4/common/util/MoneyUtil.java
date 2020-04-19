package com.sundi.springbootdemo4.common.util;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
public class MoneyUtil {

    public static final String CNY = "CNY";

    public static Money of(double amount) {
        return Money.of(CurrencyUnit.of(CNY), amount);
    }
}
