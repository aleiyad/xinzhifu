package com.sundi.springbootdemo4.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.request.AlipayTradeRoyaltyRelationUnbindRequest;
import com.alipay.api.response.*;
import com.sundi.springbootdemo4.bean.alipay.SelfAlipayTradeOrderSettleRequest;


/**
 * @author wangyubing
 * @date 2020/4/8
 */
public interface AlipayAuthService {

    /**
     * 资金授权操作查询
     *
     * @param authNo      支付宝28位订单号
     * @param operationId 支付宝流水号
     * @return
     * @throws AlipayApiException
     */
    AlipayFundAuthOperationDetailQueryResponse fundAuthQueryByAlipay(String authNo, String operationId) throws AlipayApiException;

    /**
     * 资金授权操作查询alipayTradeRoyaltyRelationUnbind
     *
     * @param outOrderNo   商户订单号
     * @param outRequestNo 商户流水号
     * @return
     * @throws AlipayApiException
     */
    AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(String outOrderNo, String outRequestNo) throws AlipayApiException;

    /**
     * 资金授权发码接口
     *
     * @param orderNum    商户订单号
     * @param operationNo 商户流水号
     * @param orderTitle  订单信息
     * @param amount      金额
     * @return
     * @throws AlipayApiException
     */
    AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(String orderNum, String operationNo, String orderTitle, Double amount) throws AlipayApiException;

    /**
     * 资金授权解冻接口
     *
     * @param authNo       支付宝28位订单码
     * @param outRequestNo 商家流水码 需要生成唯一的
     * @param amount       解冻金额
     * @param remark       标记
     * @return
     * @throws AlipayApiException
     */
    AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnfreeze(String authNo, String outRequestNo, String amount, String remark) throws AlipayApiException;


    /**
     * 授权转支付
     *
     * @param autoNo      支付宝订单号
     * @param outTradeNo  预授权转支付商户订单号，为新的商户交易流水号；如果重试发起扣款，商户订单号不要变
     * @param subject     解冻转支付标题，用于展示在支付宝账单中
     * @param totalAmount 结算支付金额
     * @param payUserId   填写预授权用户uid，通过预授权冻结接口返回的payer_user_id字段获取
     * @param body        可填写备注信息
     * @param notifyUrl   异步通知地址，必填，该接口只通过该参数进行异步通知
     * @return AlipayTradePayCallbackResponse
     * @throws AlipayApiException
     */
    AlipayTradePayResponse tradePay(String autoNo, String outTradeNo, String subject, String totalAmount, String payUserId, String body, String notifyUrl) throws AlipayApiException;


    /**
     * 分账关系绑定接口
     *
     * @param type           分账方类型。userId：表示是支付宝账号对应的支付宝唯一用户号；loginName：表示是支付宝登录号。
     * @param account        分账方帐号。当分账方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，以2088开头的纯16位数字；当分账方类型是loginName时，本参数为用户的支付宝登录号。
     * @param name           分账方全称
     * @param memo           分账关系描述
     * @param out_request_no 外部请求号。32个字符以内，可包含字母、数字、下划线。需保证在商户端不重复。
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeRoyaltyRelationBindResponse alipayTradeRoyaltyRelationBind(String type, String account, String name, String memo, String out_request_no) throws AlipayApiException;


    /**
     * 分账关系查询
     *
     * @param pageSize       页面大小
     * @param pageNum        第几页
     * @param out_request_no 外部请求号。32个字符以内，可包含字母、数字、下划线。需保证在商户端不重复。
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeRoyaltyRelationBatchqueryResponse alipayTradeRoyaltyRelationBatchuery(Integer pageSize, Integer pageNum, String out_request_no) throws AlipayApiException;


    /**
     * 分账关系解绑
     *
     * @param type           分账方类型。userId：表示是支付宝账号对应的支付宝唯一用户号；loginName：表示是支付宝登录号。
     * @param account        分账方帐号。当分账方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，以2088开头的纯16位数字；当分账方类型是loginName时，本参数为用户的支付宝登录号。
     * @param name           分账方全称
     * @param out_request_no 外部请求号。32个字符以内，可包含字母、数字、下划线。需保证在商户端不重复。
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbind(String type, String account, String name, String out_request_no) throws AlipayApiException;


    /**
     * 分账关系解绑
     * @param relationUnbindRequest
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbind(AlipayTradeRoyaltyRelationUnbindRequest relationUnbindRequest) throws AlipayApiException;


    /**
     *  alipay.trade.order.settle(统一收单交易结算接口)
     * @param alipayTradeOrderSettleRequest
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeOrderSettleResponse alipayTradeOrderSettle(SelfAlipayTradeOrderSettleRequest alipayTradeOrderSettleRequest) throws AlipayApiException;
}