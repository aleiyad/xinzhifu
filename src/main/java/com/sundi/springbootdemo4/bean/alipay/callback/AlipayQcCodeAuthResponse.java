package com.sundi.springbootdemo4.bean.alipay.callback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlipayQcCodeAuthResponse {

    /**
     * 创建时间
     */
    private String gmt_create;

    /**
     *
     */
    private String charset;

    private String operation_type;

    private String auth_no;

    private String notify_id;

    private String notify_type;

    private String gmt_trans;

    private String operation_id;

    private String out_request_no;

    private String payer_user_id;

    private String app_id;

    private String amount;

    private String rest_amount;

    private String notify_time;

    private String payee_user_id;

    private String out_order_no;

    private String payee_logon_id;

    private String version;

    private String total_pay_amount;

    private String total_freeze_amount;

    private String auth_app_id;

    private String total_unfreeze_amount;

    private String status;

    private String payer_logon_id;
}
