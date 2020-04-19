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
 * @author wangyubing
 * @date 2020/4/13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "zfb_order")
@DynamicUpdate
@DynamicInsert
public class Order {

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
     * 1,支持解冻和扣款, 2解冻完成 , 3扣款完成
     */
    @Column(name = "orderSts")
    private Integer orderSts;


    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private String gmt_create;

    /**
     *
     */
    @Column(name = "charset")
    private String charset;

    @Column(name = "operation_type")
    private String operation_type;

    @Column(name = "auth_no")
    private String auth_no;

    @Column(name = "notify_id")
    private String notify_id;

    @Column(name = "notify_type")
    private String notify_type;

    @Column(name = "gmt_trans")
    private String gmt_trans;

    @Column(name = "operation_id")
    private String operation_id;

    @Column(name = "out_request_no")
    private String out_request_no;

    @Column(name = "payer_user_id")
    private String payer_user_id;

    @Column(name = "app_id")
    private String app_id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "rest_amount")
    private String rest_amount;

    @Column(name = "notify_time")
    private String notify_time;

    @Column(name = "payee_user_id")
    private String payee_user_id;

    @Column(name = "out_order_no")
    private String out_order_no;

    @Column(name = "payee_logon_id")
    private String payee_logon_id;

    @Column(name = "version")
    private String version;

    @Column(name = "total_pay_amount")
    private String total_pay_amount;

    @Column(name = "total_freeze_amount")
    private String total_freeze_amount;

    @Column(name = "auth_app_id")
    private String auth_app_id;

    @Column(name = "total_unfreeze_amount")
    private String total_unfreeze_amount;

    @Column(name = "status")
    private String status;

    @Column(name = "payer_logon_id")
    private String payer_logon_id;
}
