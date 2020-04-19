package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.Employee;
import com.sundi.springbootdemo4.mapper.EmployeeMapper;
import com.sundi.springbootdemo4.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
