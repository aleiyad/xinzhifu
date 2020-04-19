package com.sundi.springbootdemo4.service;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.vo.RoleDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface IRoleService extends IService<Role> {

    PageInfo<Role> getRoleList(RoleDto roleDto);

    List<Role> getUserRoleByUid(Integer uid);

}
