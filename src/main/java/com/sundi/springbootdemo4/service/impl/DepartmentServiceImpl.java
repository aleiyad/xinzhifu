package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.Department;
import com.sundi.springbootdemo4.mapper.DepartmentMapper;
import com.sundi.springbootdemo4.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
