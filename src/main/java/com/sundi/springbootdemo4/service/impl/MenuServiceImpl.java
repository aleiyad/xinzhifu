package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.Menu;
import com.sundi.springbootdemo4.mapper.MenuMapper;
import com.sundi.springbootdemo4.service.IMenuService;
import com.sundi.springbootdemo4.vo.MenuVo;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<MenuVo> selectMenus() {
        return this.baseMapper.selectMenus();
    }

    @Override
    public List<Integer> selectMenuRoleByRid(Integer rid) {
        return this.baseMapper.selectMenuRoleByRid(rid);
    }

    @Override
    public List<Menu> getMenuListByUserName(String username) {
        return this.baseMapper.getMenuListByUserName(username);
    }

    @Override
    public List<Menu> getMenuAll() {
        return this.baseMapper.getMenuAll();
    }
}
