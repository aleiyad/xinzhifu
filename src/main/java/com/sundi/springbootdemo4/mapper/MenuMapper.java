package com.sundi.springbootdemo4.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundi.springbootdemo4.entity.Menu;
import com.sundi.springbootdemo4.vo.MenuVo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuVo> selectMenus();

    List<Integer> selectMenuRoleByRid(Integer rid);

    List<Menu> getMenuListByUserName(String username);

    List<Menu> getMenuAll();

}
