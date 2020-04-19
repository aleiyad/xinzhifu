package com.sundi.springbootdemo4.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
@Data
public class TGoodsVo{

    private Integer id;

    private String name;

    private BigDecimal price=new BigDecimal(0);

    private String description;

    private String  created;


}
