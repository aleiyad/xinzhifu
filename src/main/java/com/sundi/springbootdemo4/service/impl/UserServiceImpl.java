package com.sundi.springbootdemo4.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.entity.User;
import com.sundi.springbootdemo4.mapper.UserMapper;
import com.sundi.springbootdemo4.mapper.UserRoleMapper;
import com.sundi.springbootdemo4.service.IRoleService;
import com.sundi.springbootdemo4.service.IUserService;
import com.sundi.springbootdemo4.vo.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    IRoleService roleService;

    @Override
    public PageInfo<User> selectObject(UserDto userDto) {
        PageHelper.startPage(userDto.getPageNum(), userDto.getPageSize());
        List<User> users = this.baseMapper.selectObject(userDto);
        return new PageInfo<>(users);
    }

    @Override
    @Transactional
    public void deleteByUid(Integer uid) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("id",uid);
        this.baseMapper.deleteCen(uid);
        this.baseMapper.delete(wrapper);
    }


    //做登录
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("用户名:{}",s);
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("username",s);
        User user = this.selectOne(wrapper);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<Role> userRoleByUid = roleService.getUserRoleByUid(user.getId());
        String[] roleName = new String[userRoleByUid.size()];
        for(int i=0;i<userRoleByUid.size();i++){
            roleName[i] = userRoleByUid.get(i).getName();
        }

        return new org.springframework.security.core.userdetails.User(s,user.getPassword(), AuthorityUtils.createAuthorityList(roleName));
    }
}
