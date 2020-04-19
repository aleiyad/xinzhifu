package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@Data
@Accessors(chain = true)
public class Position extends Model<Position> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职位
     */
    private String name;

    @TableField("createDate")
    private Date createDate;

    private Integer enabled;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
