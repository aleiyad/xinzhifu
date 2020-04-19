package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.entity.MenuRole;
import com.sundi.springbootdemo4.mapper.MenuRoleMapper;
import com.sundi.springbootdemo4.service.IMenuRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Override
    @Transactional
    public void save(List<MenuRole> menuRoleList) {
        Wrapper<MenuRole> wrapper = new EntityWrapper<MenuRole>();
        wrapper.eq("rid",menuRoleList.get(0).getRid());
        this.delete(wrapper);
        this.insertBatch(menuRoleList);
    }
}
