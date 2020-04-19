package com.sundi.springbootdemo4.bean.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderVO {

    private Long id;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 流水号
     */
    private String operationNo;

    private Double amount;

    /**
     * 资金授权明细状态
     */
    private String status;

}
