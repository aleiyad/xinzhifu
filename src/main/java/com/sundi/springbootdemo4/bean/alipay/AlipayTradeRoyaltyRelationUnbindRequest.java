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
public class AlipayTradeRoyaltyRelationUnbindRequest {

    private List<AlipayTradeRoyaltyRelationUnbindParam> receiver_list;

    private String out_request_no;
}
