package com.sundi.springbootdemo4.bean.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductBO {

    private String name;

    private Double price;
}
