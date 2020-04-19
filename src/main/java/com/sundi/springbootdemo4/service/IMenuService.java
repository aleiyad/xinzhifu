package com.sundi.springbootdemo4.service;

import com.baomidou.mybatisplus.service.IService;
import com.sundi.springbootdemo4.entity.Menu;
import com.sundi.springbootdemo4.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface IMenuService extends IService<Menu> {

    List<MenuVo> selectMenus();

    List<Integer> selectMenuRoleByRid(Integer rid);

    List<Menu> getMenuListByUserName(String username);

    List<Menu> getMenuAll();
}
