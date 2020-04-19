package com.sundi.springbootdemo4.service;

import com.baomidou.mybatisplus.service.IService;
import com.sundi.springbootdemo4.entity.MenuRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface IMenuRoleService extends IService<MenuRole> {

    void save(List<MenuRole> menuRoleList);
}
