package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.List;


import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
@Data
@Accessors(chain = true)
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String shopsname;

    private String plot;

    private BigDecimal price;

    private Integer status;

    private Integer soldnum;

    private Integer typeId;

    private Integer clazz;

    private Integer deleted;

    private String typename;

    private List<Type> types;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
