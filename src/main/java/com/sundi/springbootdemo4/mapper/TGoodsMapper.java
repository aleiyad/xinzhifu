package com.sundi.springbootdemo4.mapper;

import com.sundi.springbootdemo4.entity.TGoods;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
public interface TGoodsMapper extends BaseMapper<TGoods> {

    void inserts(TGoods tGoods);

}
