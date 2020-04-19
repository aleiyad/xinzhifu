package com.sundi.springbootdemo4.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayFundAuthOrderUnfreezeResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.sundi.springbootdemo4.bean.alipay.AlipayTradeOrderSettleParam;
import com.sundi.springbootdemo4.bean.alipay.SelfAlipayTradeOrderSettleRequest;
import com.sundi.springbootdemo4.bean.order.OrderListRequestBO;
import com.sundi.springbootdemo4.bean.persist.Order;
import com.sundi.springbootdemo4.bean.persist.RoyaltyRelation;
import com.sundi.springbootdemo4.common.constant.MysqlConstant;
import com.sundi.springbootdemo4.common.constant.ServerConstant;
import com.sundi.springbootdemo4.common.enums.OrderStsEnum;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.common.enums.RoyaltyRelationIsDefaultEnum;
import com.sundi.springbootdemo4.common.enums.RoyaltyRelationTypeEnum;
import com.sundi.springbootdemo4.common.exceptions.GlobalException;
import com.sundi.springbootdemo4.common.util.GenerateNumUtil;
import com.sundi.springbootdemo4.config.DefaultAlipayClientFactory;
import com.sundi.springbootdemo4.repository.OrderRepository;
import com.sundi.springbootdemo4.repository.RoyaltyRelationRepository;
import com.sundi.springbootdemo4.service.AlipayAuthService;
import com.sundi.springbootdemo4.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * @author wangyubing
 * @date 2020/4/11
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    AlipayAuthService alipayAuthService;

    @Autowired
    RoyaltyRelationRepository relationRepository;

    @Override
    public List<Order> list(OrderListRequestBO requestBO) {
//        Pageable pageable = PageUtil.init(requestBO, Sort.by(Sort.Direction.DESC, "id"));
        Specification<Order> querySpeci = (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), MysqlConstant.IS_ACTIVE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return repository.findAll(querySpeci, Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public String thaw(Long id) {
        try {
            // 获取订单
            Order order = repository.findById(id).orElse(null);
            if (order == null) {
                throw new GlobalException(ResponseEnum.BAD_REQUEST);
            }
            String auth_no = order.getAuth_no();
            String operationNo = GenerateNumUtil.operationNo();
            String amount = order.getAmount();
            AlipayFundAuthOrderUnfreezeResponse response = alipayAuthService.fundAuthOrderUnfreeze(auth_no, operationNo, amount, "解冻金额" + amount);
            if (!response.isSuccess()) {
                log.error("解冻失败={}", JSON.toJSONString(response));
                throw new GlobalException(ResponseEnum.BAD_REQUEST.getCode(), "解冻失败");
            } else {
                // 修改状态
                order.setOrderSts(OrderStsEnum.UN_FREEZE_OVER.getCode());
                repository.save(order);
                return "解冻成功";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseEnum.BAD_REQUEST.getCode(), "解冻失败");
        }
    }


    @Override
    public String tradePay(Long id) {
        // 支付
        // 获取订单
        Order order = repository.findById(id).orElse(null);
        if (order == null) {
            throw new GlobalException(ResponseEnum.BAD_REQUEST);
        }
        // 扣款
        String auth_no = order.getAuth_no();
        String operationNo = GenerateNumUtil.operationNo();
        String amount = order.getAmount();
        String payer_user_id = order.getPayer_user_id();
        try {
            AlipayTradePayResponse response = alipayAuthService.tradePay(auth_no, operationNo, "支付扣款", amount, payer_user_id, "已扣款", ServerConstant.TRADE_PAY_NOTIFY);
            log.info("扣款返回数据=[{}]", JSON.toJSONString(response));
            if (!response.isSuccess()) {
                throw new GlobalException(ResponseEnum.BAD_REQUEST.getCode(), "扣款失败");
            }
            // 修改状态
            order.setOrderSts(OrderStsEnum.PAY_OVER.getCode());
            repository.save(order);
            // 分账
            // 获取默认分账接口列表
            RoyaltyRelation royaltyRelation = new RoyaltyRelation();
            royaltyRelation.setIsDefault(RoyaltyRelationIsDefaultEnum.YES.getCode());
            royaltyRelation.setIsActive(MysqlConstant.IS_ACTIVE);
            List<RoyaltyRelation> all = relationRepository.findAll(Example.of(royaltyRelation));
            if (CollectionUtils.isEmpty(all)) {
                return "无需要分账账户";
            }
            log.info("分账账户=[{}]", JSON.toJSONString(all));
            BigDecimal amountDecimal = new BigDecimal(amount);
            BigDecimal coefficient = new BigDecimal("0.3");
            BigDecimal multiply = amountDecimal.multiply(coefficient);

            BigDecimal countDecimal = new BigDecimal(String.valueOf(all.size()));
            BigDecimal divide = multiply.divide(countDecimal, 2, ROUND_HALF_UP);
            log.info("平均分账金额=[{}]", divide.toPlainString());
            List<AlipayTradeOrderSettleParam> params = new ArrayList<>();
            for (RoyaltyRelation relation : all) {
                AlipayTradeOrderSettleParam param = new AlipayTradeOrderSettleParam();
                param.setAmount(divide.toPlainString());
                param.setRoyalty_type("transfer");
                param.setTrans_out(DefaultAlipayClientFactory.payee_user_id);
                param.setTrans_out_type(RoyaltyRelationTypeEnum.USER_ID.getType());
                param.setTrans_in_type(relation.getType());
                param.setTrans_in(relation.getAccount());
                param.setDesc("分账给" + relation.getAccount());
                params.add(param);
            }
            SelfAlipayTradeOrderSettleRequest request = new SelfAlipayTradeOrderSettleRequest();
            request.setOut_request_no(String.valueOf(System.currentTimeMillis()));
            request.setTrade_no(response.getTradeNo());
            request.setOperator_id("001");
            request.setRoyalty_parameters(params);
            AlipayTradeOrderSettleResponse alipayTradeOrderSettleResponse = alipayAuthService.alipayTradeOrderSettle(request);
            log.info("分账返回数据=[{}]", JSON.toJSONString(alipayTradeOrderSettleResponse));
            if (!alipayTradeOrderSettleResponse.isSuccess()) {
                return "扣款成功但分账失败";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseEnum.BAD_REQUEST.getCode(), "扣款失败");
        }
        return "扣款成功";
    }

}
