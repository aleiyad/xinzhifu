package com.sundi.springbootdemo4.service.impl;

import com.sundi.springbootdemo4.entity.TOrder;
import com.sundi.springbootdemo4.mapper.TOrderMapper;
import com.sundi.springbootdemo4.service.ITOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

    @Override
    public List<TOrder> selectObject() {
        return this.baseMapper.selectObject();
    }
}
