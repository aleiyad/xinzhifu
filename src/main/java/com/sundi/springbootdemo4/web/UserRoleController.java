package com.sundi.springbootdemo4.web;


import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.UserRole;
import com.sundi.springbootdemo4.service.IUserRoleService;
import com.sundi.springbootdemo4.vo.UserRoleDto;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/userRole")
@Slf4j
public class UserRoleController {

    @Autowired
    private IUserRoleService userRoleService;

    /*给用户授权角色的修改*/
    @RequestMapping(value = "updRole",method = RequestMethod.PUT)
    public ResultEntity updRole(@RequestBody UserRoleDto userRoleDto){
        log.info("vue传的参数为:{}",userRoleDto);
        List<UserRole> roles = new ArrayList<>();
        for (int rid : userRoleDto.getRoleList()) {
            UserRole userRole = new UserRole();
            userRole.setUid(userRoleDto.getUid());
            userRole.setRid(rid);
            roles.add(userRole);
        }
        userRoleService.addCenRole(roles);
        return ResultEntity.ok("成功授权角色");
    }

}
