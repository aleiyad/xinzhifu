package com.sundi.springbootdemo4.vo;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.sundi.springbootdemo4.entity.Menu;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@Data
public class RoleDto extends Page {

    private Integer id;

    private String name;

    private String nameZh;



}
