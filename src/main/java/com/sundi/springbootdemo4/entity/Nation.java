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
 * 城市字典表
 * </p>
 *
 * @author Mht
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
public class Nation extends Model<Nation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 编码
     */
    private String code;

    private String province;

    private String city;

    private String district;

    private String parent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
