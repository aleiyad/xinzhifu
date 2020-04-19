package com.sundi.springbootdemo4.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Goods;
import com.sundi.springbootdemo4.mapper.GoodsMapper;
import com.sundi.springbootdemo4.service.IGoodsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.vo.GoodsVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public PageInfo<Goods> selectObject(GoodsVo goodsVo) {
        PageHelper.startPage(goodsVo.getPageNum(),goodsVo.getPageSize());
        List<Goods> goods = this.baseMapper.selectObject(goodsVo);
        return new PageInfo<>(goods);
    }
}
