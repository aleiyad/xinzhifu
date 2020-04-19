package com.sundi.springbootdemo4.bean.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderThawRequestBO {

    private Long id;

    private Double amount;
}
