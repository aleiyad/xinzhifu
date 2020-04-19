package com.sundi.springbootdemo4.bean.alipay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SelfAlipayTradeOrderSettleRequest {

    /**
     * 结算请求流水号 开发者自行生成并保证唯一性
     */
    private String out_request_no;

    /**
     * 支付宝订单号
     */
    private String trade_no;


    /**
     * 分账列表
     */
    private List<AlipayTradeOrderSettleParam> royalty_parameters;

    /**
     * 操作员
     */
    private String operator_id;
}
