package com.sundi.springbootdemo4.mapper;

import com.sundi.springbootdemo4.entity.Goods;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundi.springbootdemo4.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> selectObject(GoodsVo goodsVo);

}
