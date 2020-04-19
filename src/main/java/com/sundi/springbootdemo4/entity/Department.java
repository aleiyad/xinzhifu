package com.sundi.springbootdemo4.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    @TableField("parentId")
    private Integer parentId;

    @TableField("depPath")
    private String depPath;

    private Integer enabled;

    @TableField("isParent")
    private Integer isParent;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
