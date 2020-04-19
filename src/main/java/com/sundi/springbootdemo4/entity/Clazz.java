package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2019-12-22
 */
@Data
@Accessors(chain = true)
public class Clazz extends Model<Clazz> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String brand;

    private String clazz;

    private Integer parent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
