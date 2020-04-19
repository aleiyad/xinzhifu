package com.sundi.springbootdemo4.bean.product;

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
public class ProductVO {

    private Long id;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

    private String name;

    private String amount;
}
