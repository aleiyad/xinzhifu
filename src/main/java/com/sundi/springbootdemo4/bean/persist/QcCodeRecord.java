package com.sundi.springbootdemo4.bean.persist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.joda.money.Money;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 二维码生成记录表
 *
 * @author wangyubing
 * @date 2020/4/9
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "zfb_qc_code_record")
@DynamicUpdate
@DynamicInsert
public class QcCodeRecord {

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
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 流水号
     */
    @Column(name = "operation_no")
    private String operationNo;

    @Column(name = "amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money amount;

    /**
     * 资金授权明细状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 支付宝资金授权订单号
     */
    @Column(name = "alipay_order_no")
    private String alipayOrderNo;


    /**
     * 支付宝资金操作流水号
     */
    @Column(name = "alipay_operation_no")
    private String alipayOperationNo;


    /**
     * 资金操作类型，支持【FREEZE，UNFREEZE，PAY】
     */
    @Column(name = "operation_type")
    private String operationType;

    /**
     * 操作处理完成时间
     */
    @Column(name = "gmt_tran")
    private String gmtTran;

    /**
     * 操作创建时间
     */
    @Column(name = "gmt_create")
    private String gmtCreate;


    /**
     * 付款方支付宝账号（Email或手机号） 登录号
     */
    @Column(name = "payer_logon_id")
    private String payerLogonId;

    /**
     * 付款方支付宝用户号
     */
    @Column(name = "payer_user_id")
    private String payerUserId;

    /**
     * 收款方支付宝账号（Email或手机号） 登录号
     */
    @Column(name = "payee_logon_id")
    private String payeeLogonId;

    /**
     * 收款方支付宝用户号
     */
    @Column(name = "payee_user_id")
    private String payeeUserId;

    /**
     * 资金授权成功时间
     */
    @Column(name = "gmt_trans")
    private Date gmtTrans;

    /**
     * 该笔资金操作流水opertion_id对应的操作信用金额
     */
    @Column(name = "credit_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money creditAmount;

    /**
     * 该笔资金操作流水opertion_id对应的操作自有资金金额
     */
    @Column(name = "fund_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money fundAmount;

    /**
     * 预授权类型，目前支持 CREDIT_AUTH(信用预授权);
     * 商户可根据该标识来判断该笔预授权的类型，当返回值为"CREDIT_AUTH"表明该笔预授权为信用预授权，没有真实冻结资金；当返回值为空或者不为"CREDIT_AUTH"则表明该笔预授权为普通资金预授权，会冻结用户资金。
     */
    @Column(name = "pre_auth_type")
    private String preAuthType;


    /**
     * 订单总共剩余的冻结金额，单位为：元（人民币）
     */
    @Column(name = "rest_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money restAmount;

    /**
     * 剩余冻结信用金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "rest_credit_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money restCreditAmount;

    /**
     * 剩余冻结自有资金金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "rest_fund_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money restFundAmount;

    /**
     * 订单累计的冻结金额，单位为：元（人民币）
     */
    @Column(name = "total_freeze_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalFreezeAmount;

    /**
     * 累计冻结信用金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "total_freeze_credit_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalFreezeCreditAmount;


    /**
     * 累计冻结自有资金金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "total_freeze_fund_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalFreezeFundAmount;


    /**
     * 订单累计用于支付的金额，单位为：元（人民币）
     */
    @Column(name = "total_pay_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalPayAmount;

    /**
     * 累计支付信用金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "total_pay_credit_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalPayCreditAmount;

    /**
     * 累计支付自有资金金额，单位为：元（人民币），精确到小数点后两位
     */
    @Column(name = "total_pay_fund_amount")
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money totalPayFundAmount;

    /**
     * 标价币种,  amount 对应的币种单位。支持澳元：AUD, 新西兰元：NZD, 台币：TWD, 美元：USD, 欧元：EUR, 英镑：GBP
     */
    @Column(name = "trans_currency")
    private String transCurrency;
}
