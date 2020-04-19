package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.UserRole;
import com.sundi.springbootdemo4.mapper.UserRoleMapper;
import com.sundi.springbootdemo4.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    //事务 如果修改报错 就不删除中间表uid对应的角色
    @Transactional
    public void addCenRole(List<UserRole> roles) {
        Wrapper<UserRole> wrapper = new EntityWrapper<>();
        wrapper.eq("uid",roles.get(0).getUid());
        this.baseMapper.delete(wrapper);
        this.insertBatch(roles);

    }
}
