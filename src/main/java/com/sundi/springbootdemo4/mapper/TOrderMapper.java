package com.sundi.springbootdemo4.mapper;

import com.sundi.springbootdemo4.entity.TOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    List<TOrder> selectObject();

}
