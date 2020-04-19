package com.sundi.springbootdemo4.web;



import com.alibaba.fastjson.JSON;
import com.sundi.springbootdemo4.entity.Menu;
import com.sundi.springbootdemo4.service.IMenuService;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.vo.MenuVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    IMenuService menuService;

    //查询所有的菜单选项
    @RequestMapping(value = "selectMents",method = RequestMethod.GET)
    public ResultEntity select(){
        List<MenuVo> list = menuService.selectMenus();
        return ResultEntity.ok(list);
    }

    //查询已经拥有的菜单
    @RequestMapping(value = "selectMentRoleByRid",method = RequestMethod.GET)
    public ResultEntity menuRoleByRid(Integer rid){
        List<Integer> MenuRoleByRid = menuService.selectMenuRoleByRid(rid);
        return ResultEntity.ok(MenuRoleByRid);
    }

    @RequestMapping(value = "getMenuListByUserName",method = RequestMethod.GET)
    public ResultEntity getMenuListByUserName(Authentication authentication){
        log.info("用户登录成功之后获取Authentication为:{}", JSON.toJSONString(authentication));
        List<Menu> menuListByUserName = menuService.getMenuListByUserName(authentication.getName());
        return ResultEntity.ok(menuListByUserName);
    }

}
