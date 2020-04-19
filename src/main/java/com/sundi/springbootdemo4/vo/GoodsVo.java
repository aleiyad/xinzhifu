package com.sundi.springbootdemo4.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.sundi.springbootdemo4.entity.Type;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
@Data
public class GoodsVo extends Page {

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


}
