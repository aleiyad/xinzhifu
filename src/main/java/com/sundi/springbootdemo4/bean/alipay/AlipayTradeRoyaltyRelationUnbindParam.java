package com.sundi.springbootdemo4.bean.alipay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlipayTradeRoyaltyRelationUnbindParam {

    private String type;

    private String account;

    private String name;
}
