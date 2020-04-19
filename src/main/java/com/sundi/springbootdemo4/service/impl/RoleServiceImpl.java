package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.mapper.RoleMapper;
import com.sundi.springbootdemo4.service.IRoleService;
import com.sundi.springbootdemo4.vo.RoleDto;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public PageInfo<Role> getRoleList(RoleDto roleDto) {
        PageHelper.startPage(roleDto.getPageNum(), roleDto.getPageSize());
        List<Role> roleList = this.baseMapper.getRoleList(roleDto);
        return new PageInfo<>(roleList);
    }

    @Override
    public List<Role> getUserRoleByUid(Integer uid) {
        return this.baseMapper.getUserRoleByUid(uid);
    }
}
