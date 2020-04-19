package com.sundi.springbootdemo4.service;

import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Goods;
import com.baomidou.mybatisplus.service.IService;
import com.sundi.springbootdemo4.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
public interface IGoodsService extends IService<Goods> {

    PageInfo<Goods> selectObject(GoodsVo goodsVo);

}
