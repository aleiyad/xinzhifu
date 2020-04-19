package com.sundi.springbootdemo4.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.vo.RoleDto;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleList(RoleDto roleDto);

    List<Role> getUserRoleByUid(Integer uid);

}
