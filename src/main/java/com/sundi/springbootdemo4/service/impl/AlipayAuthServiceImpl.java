package com.sundi.springbootdemo4.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayFundAuthOperationDetailQueryModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;

import com.sundi.springbootdemo4.bean.alipay.SelfAlipayTradeOrderSettleRequest;
import com.sundi.springbootdemo4.common.constant.ServerConstant;
import com.sundi.springbootdemo4.config.DefaultAlipayClientFactory;
import com.sundi.springbootdemo4.service.AlipayAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
@Service
@Slf4j
public class AlipayAuthServiceImpl implements AlipayAuthService {

    public static AlipayClient alipayClient = DefaultAlipayClientFactory.getAlipayClient();

    @Override
    public AlipayFundAuthOperationDetailQueryResponse fundAuthQueryByAlipay(String authNo, String operationId) throws AlipayApiException {
        return fundAuthQuery(authNo, operationId, null, null);
    }

    @Override
    public AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(String outOrderNo, String outRequestNo) throws AlipayApiException {
        return fundAuthQuery(null, null, outOrderNo, outRequestNo);
    }

    private AlipayFundAuthOperationDetailQueryResponse fundAuthQuery(String authNo, String operationId, String outOrderNo, String outRequestNo) throws AlipayApiException {
        AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
        AlipayFundAuthOperationDetailQueryModel model = new AlipayFundAuthOperationDetailQueryModel();
        model.setAuthNo(authNo); // 支付宝资金授权订单号，在授权冻结成功时返回参数中获得
        model.setOutOrderNo(outOrderNo); //商户的授权资金订单号，与支付宝的授权资金订单号不能同时为空
        model.setOperationId(operationId); //支付宝的授权资金操作流水号，冻结成功同步返回
        model.setOutRequestNo(outRequestNo);//商户的授权资金操作流水号，与支付宝的授权资金操作流水号不能同时为空，该值为冻结或解冻是的outRequestNo
        request.setBizModel(model);
        return alipayClient.certificateExecute(request);
    }


    @Override
    public AlipayFundAuthOrderVoucherCreateResponse fundAuthOrderVoucherCreate(String orderNum, String operationNo, String orderTitle, Double amount) throws AlipayApiException {
        AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
        // 回调地址
        request.setNotifyUrl(ServerConstant.FUND_AUTH_ORDER_VOUCHER_CREATE_NOTIFY);
        request.setBizContent("{" +
                "\"out_order_no\":\"" + orderNum + "\"," +
                "\"out_request_no\":\"" + operationNo + "\"," +
                "\"order_title\":\"" + orderTitle + "\"," +
                "\"amount\":" + amount + "," +
                "\"payee_user_id\":\"" + DefaultAlipayClientFactory.payee_user_id + "\"," +
                "\"payee_logon_id\":\"" + DefaultAlipayClientFactory.payee_logon_id + "\"," +
                "\"pay_timeout\":\"" + DefaultAlipayClientFactory.pay_timeout + "\"," +
                //{\"category\":\"HOTEL\"}
//                "\"extra_param\":\"" + DefaultAlipayClientFactory.ALIPAY_EXTRA_PARAM + "\"," +
                "\"product_code\":\"PRE_AUTH\"," +
//                    "\"trans_currency\":\"USD\"," +
//                    "\"settle_currency\":\"USD\"," +
//                    "\"enable_pay_channels\":\"[{\\\"payChannelType\\\":\\\"PCREDIT_PAY\\\"},{\\\"payChannelType\\\":\\\"MONEY_FUND\\\"}]\"," +
//                    "\"identity_params\":\"{\\\"identity_hash\\\":\\\"ABCDEFDxxxxxx\\\",\\\"alipay_user_id\\\":\\\"2088xxx\\\"}\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }
//
//    public static void main(String[] args) throws AlipayApiException {
//        String orderNum = GenerateNumUtil.orderId();
//        // 流水
//        String operationNo = GenerateNumUtil.operationNo();
//        AlipayFundAuthOrderVoucherCreateRequest request = new AlipayFundAuthOrderVoucherCreateRequest();
//        // 回调地址
////        request.setNotifyUrl(ServerConstant.FUND_AUTH_ORDER_VOUCHER_CREATE_NOTIFY);
//        request.setBizContent("{" +
//                "\"out_order_no\":\"" + orderNum + "\"," +
//                "\"out_request_no\":\"" + operationNo + "\"," +
//                "\"order_title\":\"" + "1号测试产品" + "\"," +
//                "\"amount\":" + 0.01 + "," +
//                "\"payee_user_id\":\"" + DefaultAlipayClientFactory.payee_user_id + "\"," +
//                "\"payee_logon_id\":\"" + DefaultAlipayClientFactory.payee_logon_id + "\"," +
////                "\"pay_timeout\":\"" + DefaultAlipayClientFactory.pay_timeout + "\"," +
//                //{\"category\":\"HOTEL\"}
////                "\"extra_param\":\"" + DefaultAlipayClientFactory.ALIPAY_EXTRA_PARAM + "\"," +
//                "\"product_code\":\"PRE_AUTH\"," +
////                    "\"trans_currency\":\"USD\"," +
////                    "\"settle_currency\":\"USD\"," +
////                    "\"enable_pay_channels\":\"[{\\\"payChannelType\\\":\\\"PCREDIT_PAY\\\"},{\\\"payChannelType\\\":\\\"MONEY_FUND\\\"}]\"," +
////                    "\"identity_params\":\"{\\\"identity_hash\\\":\\\"ABCDEFDxxxxxx\\\",\\\"alipay_user_id\\\":\\\"2088xxx\\\"}\"" +
//                "  }");
//        AlipayFundAuthOrderVoucherCreateResponse response = alipayClient.certificateExecute(request);
//        System.out.println(response);
//
//        // 20200412211522000 1586697324566000
////        AlipayFundAuthOperationDetailQueryRequest request = new AlipayFundAuthOperationDetailQueryRequest();
////        AlipayFundAuthOperationDetailQueryModel model = new AlipayFundAuthOperationDetailQueryModel();
//////        model.setAuthNo(authNo); // 支付宝资金授权订单号，在授权冻结成功时返回参数中获得
////        model.setOutOrderNo("20200412211522000"); //商户的授权资金订单号，与支付宝的授权资金订单号不能同时为空
//////        model.setOperationId(operationId); //支付宝的授权资金操作流水号，冻结成功同步返回
////        model.setOutRequestNo("1586697324566000");//商户的授权资金操作流水号，与支付宝的授权资金操作流水号不能同时为空，该值为冻结或解冻是的outRequestNo
////        request.setBizModel(model);
////        AlipayFundAuthOperationDetailQueryResponse alipayFundAuthOperationDetailQueryResponse = alipayClient.certificateExecute(request);
////        System.out.println(alipayFundAuthOperationDetailQueryResponse);
//
//    }

    @Override
    public AlipayFundAuthOrderUnfreezeResponse fundAuthOrderUnfreeze(String authNo, String outRequestNo, String amount, String remark) throws AlipayApiException {
        AlipayFundAuthOrderUnfreezeRequest request = new AlipayFundAuthOrderUnfreezeRequest();
        request.setBizContent("{" +
                "\"auth_no\":\"" + authNo + "\"," +
                "\"out_request_no\":\"" + outRequestNo + "\"," +
                "\"amount\":" + amount + "," +
                "\"remark\":\"" + remark + "\"," +
                "\"extra_param\":\"{\\\"unfreezeBizInfo\\\": \\\"{\\\\\\\"bizComplete\\\\\\\":\\\\\\\"true\\\\\\\"}\\\"}\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }


    /**
     * 授权转支付
     */
    @Override
    public AlipayTradePayResponse tradePay(String autoNo, String outTradeNo, String subject, String totalAmount, String payUserId, String body, String notifyUrl) throws AlipayApiException {
//        AlipayTradePayRequest request = new AlipayTradePayRequest();
//        AlipayTradePayModel model = new AlipayTradePayModel();
//        model.setOutTradeNo(outTradeNo); // 预授权转支付商户订单号，为新的商户交易流水号；如果重试发起扣款，商户订单号不要变；
//        model.setProductCode("PRE_AUTH_ONLINE"); // 固定值PRE_AUTH_ONLINE
//        model.setAuthNo(autoNo); // 填写预授权冻结交易号
//        model.setSubject(subject); // 解冻转支付标题，用于展示在支付宝账单中
//        model.setTotalAmount(totalAmount); // 结算支付金额
//        model.setSellerId(DefaultAlipayClientFactory.payee_user_id); // 填写卖家支付宝账户pid
//        model.setBuyerId(payUserId); // 填写预授权用户uid，通过预授权冻结接口返回的payer_user_id字段获取
//        model.setStoreId(DefaultAlipayClientFactory.OUT_STORECODE); // 填写实际交易发生的终端编号，与预授权的outStoreCode保持一致即可
//        model.setBody(body); // 可填写备注信息
//        model.setAuthConfirmMode("COMPLETE");//必须使用COMPLETE,传入该值用户剩余金额会自动解冻
//        request.setBizModel(model);
//        request.setNotifyUrl(notifyUrl);//异步通知地址，必填，该接口只通过该参数进行异步通知
//        return alipayClient.certificateExecute(request);

        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(outTradeNo);      //预授权转支付商户订单号，为新的商户交易流水号
        model.setScene("bar_code");                // 固定值bar_code
        model.setProductCode("PRE_AUTH");          // 固定值PRE_AUTH
        model.setAuthNo(autoNo);  // 填写预授权冻结交易号
        model.setSubject(subject);     // 解冻转支付标题，用于展示在支付宝账单中
        model.setTotalAmount(totalAmount);             // 结算支付金额
        model.setSellerId(DefaultAlipayClientFactory.payee_user_id);    // 填写卖家支付宝账户pid
        model.setBuyerId(payUserId);     // 2088202415071394填写预授权用户uid，通过预授权冻结接口返回的payer_user_id字段获取
        model.setBody(body);          // 可填写备注信息
        model.setTerminalId("001");      // 填写商户操作员编号
        model.setStoreId(DefaultAlipayClientFactory.OUT_STORECODE);    // 填写实际交易发生的终端编号
        model.setTimeoutExpress("5m");             // 填写解冻转支付交易的超时时间
//     ExtendParams extendParams = new ExtendParams();
//      extendParams.setSysServiceProviderId("此处填写ISV签约返佣协议账户的PID为2088开头的16位数字");
//      model.setExtendParams(extendParams);  //系统商开发需要传入正确的返佣参数才能拿到返佣,无返佣协议不需要传入该参数。
        model.setAuthConfirmMode("COMPLETE"); //自动解冻时取值COMPLETE,不传该参数默认剩余资金不会自动解冻NOT_COMPLETE。
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        return alipayClient.certificateExecute(request);
    }

    @Override
    public AlipayTradeRoyaltyRelationBindResponse alipayTradeRoyaltyRelationBind(String type, String account, String name, String memo, String out_request_no) throws AlipayApiException {
        AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
        request.setBizContent("{" +
                "      \"receiver_list\":[{" +
                "       \"type\":\"" + type + "\"," +
                "\"account\":\"" + account + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"memo\":\"" + memo + "\"" +
                "        }]," +
                "\"out_request_no\":\"" + out_request_no + "\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }

    @Override
    public AlipayTradeRoyaltyRelationBatchqueryResponse alipayTradeRoyaltyRelationBatchuery(Integer pageSize, Integer pageNum, String out_request_no) throws AlipayApiException {
        AlipayTradeRoyaltyRelationBatchqueryRequest request = new AlipayTradeRoyaltyRelationBatchqueryRequest();
        request.setBizContent("{" +
                "\"page_num\":" + pageNum + "," +
                "\"page_size\":" + pageSize + "," +
                "\"out_request_no\":\"" + out_request_no + "\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }

    @Override
    public AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbind(String type, String account, String name, String out_request_no) throws AlipayApiException {
        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
        request.setBizContent("{" +
                "      \"receiver_list\":[{" +
                "        \"type\":\"" + type + "\"," +
                "\"account\":\"" + account + "\"," +
                "\"name\":\"" + name + "\"," +
//                "\"memo\":\"分账给测试商户\"" +
                "        }]," +
                "\"out_request_no\":\"" + out_request_no + "\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }


    @Override
    public AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbind(AlipayTradeRoyaltyRelationUnbindRequest relationUnbindRequest) throws AlipayApiException {
        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
        request.setBizContent(JSON.toJSONString(relationUnbindRequest));
        return alipayClient.certificateExecute(request);
    }

    @Override
    public AlipayTradeOrderSettleResponse alipayTradeOrderSettle(SelfAlipayTradeOrderSettleRequest alipayTradeOrderSettleRequest) throws AlipayApiException {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizContent(JSON.toJSONString(alipayTradeOrderSettleRequest));
        return alipayClient.certificateExecute(request);
    }

    public AlipayFundAuthOperationCancelResponse alipayFundAuthOperationCancel() throws AlipayApiException {
        AlipayFundAuthOperationCancelRequest request = new AlipayFundAuthOperationCancelRequest();
        request.setBizContent("{" +
                "\"auth_no\":\"2014070800002001550000014417\"," +
                "\"out_order_no\":\"4977164666634053\"," +
                "\"operation_id\":\"20161012405744018102\"," +
                "\"out_request_no\":\"2016100810000003551\"," +
                "\"remark\":\"授权撤销\"" +
                "  }");
        return alipayClient.certificateExecute(request);
    }


    public static void main(String[] args) throws AlipayApiException {
//        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//        request.setBizContent("{" +
//                "\"trade_no\":\"2020041922001410721445690513\"," +
////                "\"org_pid\":\"2088101117952222\"," +
//                "      \"query_options\":[" +
//                "        \"TRADE_SETTLE_INFO\"" +
//                "      ]" +
//                "  }");
//        AlipayTradeQueryResponse response = alipayClient.certificateExecute(request);
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//        String outRequestNo = GenerateNumUtil.outRequestNo();

//        AlipayTradeRoyaltyRelationBatchqueryRequest request = new AlipayTradeRoyaltyRelationBatchqueryRequest();
//        request.setBizContent("{" +
//                "\"page_num\":" + 1 + "," +
//                "\"page_size\":" + 1 + "," +
//                "\"out_request_no\":\"" + "2020041915130001" + "\"" +
//                "  }");
//
//        AlipayTradeRoyaltyRelationBatchqueryResponse relationBatchqueryResponse = alipayClient.certificateExecute(request);
//        System.out.println(relationBatchqueryResponse.isSuccess());

//        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
//        request.setBizContent("{" +
//                "      \"receiver_list\":[{" +
//                "        \"type\":\"" + "userId" + "\"," +
//                "\"account\":\"" + "2088802845958306" + "\"," +
//                "\"name\":\"" + "孙磊" + "\"," +
////                "\"memo\":\"分账给测试商户\"" +
//                "        }]," +
//                "\"out_request_no\":\"" + "2020041915130002" + "\"" +
//                "  }");
//        AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbindResponse = alipayClient.certificateExecute(request);
//        System.out.println(alipayTradeRoyaltyRelationUnbindResponse.isSuccess());

    }
}
