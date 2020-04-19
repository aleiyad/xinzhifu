package com.sundi.springbootdemo4.service;

import com.sundi.springbootdemo4.entity.TGoods;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
public interface ITGoodsService extends IService<TGoods> {

    void inserts(TGoods tGoods);
}
