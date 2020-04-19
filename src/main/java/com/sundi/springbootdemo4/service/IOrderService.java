package com.sundi.springbootdemo4.service;


import com.sundi.springbootdemo4.bean.order.OrderListRequestBO;
import com.sundi.springbootdemo4.bean.persist.Order;

import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
public interface IOrderService {

    List<Order> list(OrderListRequestBO requestBO);

    /**
     * 解除冻结
     * @param id 包含订单id
     */
    String thaw(Long id);

    /**
     * 扣款
     * @param id 包含订单id
     */
    String tradePay(Long id);
}
