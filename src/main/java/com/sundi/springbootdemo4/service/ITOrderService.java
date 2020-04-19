package com.sundi.springbootdemo4.service;

import com.sundi.springbootdemo4.entity.TOrder;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
public interface ITOrderService extends IService<TOrder> {

    List<TOrder> selectObject();
}
