package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_order",resultMap = "BaseResultMap")
public class TOrder extends Model<TOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderSn;

    private Integer userId;

    private BigDecimal totalPrice=new BigDecimal(0);

    private Integer status;

    private String created;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String username;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
