package com.sundi.springbootdemo4.web;


import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.MenuRole;
import com.sundi.springbootdemo4.service.IMenuRoleService;
import com.sundi.springbootdemo4.vo.RoleMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/menuRole")
public class MenuRoleController {

    @Autowired
    IMenuRoleService menuRoleService;

    @RequestMapping(value = "shouquanUpdate",method = RequestMethod.POST)
    public ResultEntity save(@RequestBody RoleMenuDto roleMenuDto){
        List<MenuRole> menuRoleList = new ArrayList<>();
        for (Integer integer : roleMenuDto.getMenuIdList()) {
            MenuRole menuRole = new MenuRole();
            menuRole.setMid(integer);
            menuRole.setRid(roleMenuDto.getRid());
            menuRoleList.add(menuRole);
        }
        menuRoleService.save(menuRoleList);
        return ResultEntity.ok("成功授权菜单");
    }

}
