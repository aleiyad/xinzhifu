package com.sundi.springbootdemo4.bean.royaltyRelation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoyaltyRelationAddBO {

    /**
     * 分账方帐号。当分账方类型是userId时，本参数为用户的支付宝账号对应的支付宝唯一用户号，以2088开头的纯16位数字；当分账方类型是loginName时，本参数为用户的支付宝登录号。
     */
    @Column(name = "account")
    private String account;

    /**
     * 分账方全称
     */
    @Column(name = "name")
    private String name;


    /**
     * 分账关系描述
     */
    @Column(name = "memo")
    private String memo;

}
