package com.sundi.springbootdemo4.service.impl;

import com.sundi.springbootdemo4.entity.TGoods;
import com.sundi.springbootdemo4.mapper.TGoodsMapper;
import com.sundi.springbootdemo4.service.ITGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements ITGoodsService {

    @Override
    public void inserts(TGoods tGoods) {
        this.baseMapper.inserts(tGoods);
    }
}
