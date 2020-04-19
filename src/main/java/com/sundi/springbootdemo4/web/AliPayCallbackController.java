package com.sundi.springbootdemo4.web;

import com.alibaba.fastjson.JSON;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.sundi.springbootdemo4.bean.persist.Order;
import com.sundi.springbootdemo4.config.DefaultAlipayClientFactory;
import com.sundi.springbootdemo4.common.constant.MysqlConstant;
import com.sundi.springbootdemo4.common.enums.OrderStsEnum;
import com.sundi.springbootdemo4.repository.OrderRepository;
import com.sundi.springbootdemo4.repository.RoyaltyRelationRepository;
import com.sundi.springbootdemo4.service.AlipayAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
@RestController
@RequestMapping("/notify")
@Slf4j
public class AliPayCallbackController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    AlipayAuthService alipayAuthService;
    @Autowired
    RoyaltyRelationRepository relationRepository;

    @RequestMapping(value = "/fundAuthOrderVoucherCreate")
    public void fundAuthOrderVoucherCreate(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        String charset = DefaultAlipayClientFactory.CHARSET;
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean validation;
        String result = "false";
        try {
            validation = AlipaySignature.certVerifyV1(params, DefaultAlipayClientFactory.ALIPAY_PUBLIC_CERT_PATH, DefaultAlipayClientFactory.CHARSET,
                    DefaultAlipayClientFactory.SIGN_TYPE);

            if (validation) {
                //TODO 根据业务需要进行处理
                String json = JSON.toJSONString(params);
                Order order = JSON.parseObject(json, Order.class);
                order.setOrderSts(OrderStsEnum.ALL.getCode());
                order.setIsActive(MysqlConstant.IS_ACTIVE);
                orderRepository.save(order);
                result = "success";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + charset);
        response.setCharacterEncoding(charset);
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
        return;
    }

    @RequestMapping(value = "/tradePay")
    public void tradePay(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        String charset = DefaultAlipayClientFactory.CHARSET;
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        boolean validation;
        String result = "false";
        try {
            validation = AlipaySignature.certVerifyV1(params, DefaultAlipayClientFactory.ALIPAY_PUBLIC_CERT_PATH, DefaultAlipayClientFactory.CHARSET,
                    DefaultAlipayClientFactory.SIGN_TYPE);
            if (validation) {
                //TODO 根据业务需要进行处理
                String json = JSON.toJSONString(params);
                log.info("扣款回调数据=[{}]", json);
//                // 改order状态
//                AlipayTradePayCallbackResponse alipayTradePayCallbackResponse = JSON.parseObject(json, AlipayTradePayCallbackResponse.class);
//                if (alipayTradePayCallbackResponse.getTrade_status().equals("TRADE_SUCCESS")) {
//                    // 获取默认分账接口列表
//                    RoyaltyRelation royaltyRelation = new RoyaltyRelation();
//                    royaltyRelation.setIsDefault(RoyaltyRelationIsDefaultEnum.YES.getCode());
//                    royaltyRelation.setIsActive(MysqlConstant.IS_ACTIVE);
//                    List<RoyaltyRelation> all = relationRepository.findAll(Example.of(royaltyRelation));
//                    if (CollectionUtils.isEmpty(all)) {
//                        result = "success";
//                    } else {
//                        log.info("分账账户=[{}]", JSON.toJSONString(all));
//                        BigDecimal amountDecimal = BigDecimal.valueOf(Double.parseDouble(alipayTradePayCallbackResponse.getTotal_amount()));
//                        BigDecimal countDecimal = BigDecimal.valueOf(Double.parseDouble(String.valueOf(all.size())));
//                        BigDecimal divide = amountDecimal.divide(countDecimal, 2, ROUND_HALF_UP);
//                        log.info("平均分账金额=[{}]", divide.toPlainString());
//                        List<AlipayTradeOrderSettleParam> paramList = new ArrayList<>();
//                        for (RoyaltyRelation relation : all) {
//                            AlipayTradeOrderSettleParam param = new AlipayTradeOrderSettleParam();
//                            param.setAmount(divide.doubleValue());
//                            param.setRoyalty_type("transfer");
//                            param.setTrans_out(DefaultAlipayClientFactory.payee_user_id);
//                            param.setTrans_out_type(RoyaltyRelationTypeEnum.USER_ID.getType());
//                            param.setTrans_in_type(RoyaltyRelationTypeEnum.LOGIN_NAME.getType());
//                            param.setTrans_in(relation.getAccount());
//                            param.setDesc("分账给" + relation.getAccount());
//                            paramList.add(param);
//                        }
//                        SelfAlipayTradeOrderSettleRequest settleRequest = new SelfAlipayTradeOrderSettleRequest();
//                        settleRequest.setOut_request_no(String.valueOf(System.currentTimeMillis()));
//                        settleRequest.setTrade_no(alipayTradePayCallbackResponse.getTrade_no());
//                        settleRequest.setOperator_id("001");
//                        settleRequest.setRoyalty_parameters(paramList);
//                        AlipayTradeOrderSettleResponse alipayTradeOrderSettleResponse = alipayAuthService.alipayTradeOrderSettle(settleRequest);
//                        if (!alipayTradeOrderSettleResponse.isSuccess()) {
//                            log.error("分账返回数据=[{}]", JSON.toJSONString(alipayTradeOrderSettleResponse));
//                        }
//                    }
//                }
//                // 商户分账
                result = "success";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + charset);
        response.setCharacterEncoding(charset);
        response.getWriter().write(result);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
        return;
    }

}
