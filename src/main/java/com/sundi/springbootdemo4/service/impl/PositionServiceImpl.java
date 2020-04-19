package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.Position;
import com.sundi.springbootdemo4.mapper.PositionMapper;
import com.sundi.springbootdemo4.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
