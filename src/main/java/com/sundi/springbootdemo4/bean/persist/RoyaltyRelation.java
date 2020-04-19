package com.sundi.springbootdemo4.bean.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * 分账商户关系表
 *
 * @author wangyubing
 * @date 2020/4/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "zfb_royaltyRelation")
@DynamicUpdate
@DynamicInsert
public class RoyaltyRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Integer isActive;

    @CreationTimestamp
    @Column(name = "create_time")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 分账方类型。userId：表示是支付宝账号对应的支付宝唯一用户号；loginName：表示是支付宝登录号。
     */
    @Column(name = "type")
    private String type;

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


    /**
     * 外部请求号。32个字符以内，可包含字母、数字、下划线。需保证在商户端不重复。
     */
    @Column(name = "out_request_no")
    private String out_request_no;


    /**
     * 是否默认分账
     */
    @Column(name = "is_default")
    private Integer isDefault;

}
