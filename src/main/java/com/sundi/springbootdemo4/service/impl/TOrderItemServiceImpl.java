package com.sundi.springbootdemo4.service.impl;

import com.sundi.springbootdemo4.entity.TOrderItem;
import com.sundi.springbootdemo4.mapper.TOrderItemMapper;
import com.sundi.springbootdemo4.service.ITOrderItemService;
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
public class TOrderItemServiceImpl extends ServiceImpl<TOrderItemMapper, TOrderItem> implements ITOrderItemService {

}
