package com.sundi.springbootdemo4.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundi.springbootdemo4.entity.Role;
import com.sundi.springbootdemo4.entity.User;
import com.sundi.springbootdemo4.vo.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectObject(UserDto userDto);

    void deleteCen(Integer uid);
}
