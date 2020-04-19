package com.sundi.springbootdemo4.service.impl;

import com.sundi.springbootdemo4.entity.Type;
import com.sundi.springbootdemo4.mapper.TypeMapper;
import com.sundi.springbootdemo4.service.ITypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
