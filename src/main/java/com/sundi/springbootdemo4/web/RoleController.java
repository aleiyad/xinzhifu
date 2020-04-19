package com.sundi.springbootdemo4.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.entity.UserRole;
import com.sundi.springbootdemo4.service.IRoleService;
import com.sundi.springbootdemo4.service.IUserRoleService;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.vo.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    //角色+菜单列表加分页
    @RequestMapping("getRoleList")
    public ResultEntity getRoleList(RoleDto roleDto){
        PageInfo<Role> roleList = roleService.getRoleList(roleDto);
        return ResultEntity.ok(roleList);
    }


    //查询所有的角色复选框
    @RequestMapping("getRole")
    public ResultEntity getRole(Role role){
        Wrapper<Role> wrapper = new EntityWrapper<>(role);
        List<Role> roles = roleService.selectList(wrapper);
        return ResultEntity.ok(roles);
    }
    //查询已经拥有的角色
    @RequestMapping("getRoleByUid")
    public ResultEntity getRoleByUid(Integer uid){
        Wrapper<UserRole> wrapper = new EntityWrapper<>();
        wrapper.eq("uid",uid);
        List<UserRole> list = userRoleService.selectList(wrapper);
        log.info("查询的已拥有角色为：{}",list);
        return ResultEntity.ok(list);
    }


}
