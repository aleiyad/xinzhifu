package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;



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
 * @since 2019-12-24
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_order_item",resultMap = "BaseResultMap")
public class TOrderItem extends Model<TOrderItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer oid;

    private Integer gid;

    private Integer amount;

    private BigDecimal price=new BigDecimal(0);


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
