package com.sundi.springbootdemo4.repository;

import com.sundi.springbootdemo4.bean.persist.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 订单Repository
 *
 * @author wangyubing
 * @date 2020/4/8
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

}

