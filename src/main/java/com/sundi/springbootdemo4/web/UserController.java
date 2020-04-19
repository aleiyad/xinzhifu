package com.sundi.springbootdemo4.web;



import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.entity.User;
import com.sundi.springbootdemo4.service.IUserService;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.vo.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IUserService userService;

    //密码加密
    @Autowired
    PasswordEncoder encoder;

    @Value("${user.img.path}")
    String userImgPath;
    @Value("${user.img.url}")
    String userImgUrl;

    //多对多查询
    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public ResultEntity selectObjects(UserDto userDto) {
        PageInfo<User> pageInfo = userService.selectObject(userDto);
        return ResultEntity.ok(pageInfo);
    }

    //添加
    @RequestMapping(value = "/addList",method = RequestMethod.POST)
    public ResultEntity add(@ModelAttribute UserDto userDto) throws IOException {

        String originalFilename = "";
        String suffixName = "";
        String fileName = "";
        for (MultipartFile multipartFile : userDto.getFiles()) {
            //获取名字
            originalFilename = multipartFile.getOriginalFilename();
            suffixName  = originalFilename.substring(originalFilename.lastIndexOf("."));
            //重新生成的图片名称
            fileName = UUID.randomUUID().toString().concat(suffixName);
            File file = new File(userImgPath.concat(fileName));
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
        }

        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("username",userDto.getUsername());
        User user = userService.selectOne(wrapper);
        if (user!=null){
            return ResultEntity.error("您设置的用户名已存在");
        }
        User u = new User();
        BeanUtils.copyProperties(userDto,u);
        //设置密码并加密
        u.setPassword(encoder.encode("123456"));
        u.setProvince(userDto.getValue()[0]);
        u.setCity(userDto.getValue()[1]);
        u.setDistrict(userDto.getValue()[2]);
        u.setUserface(userImgUrl.concat(fileName));
        userService.insert(u);

        return ResultEntity.ok("添加成功");
    }

    //修改
    @RequestMapping(value = "/updateList",method = RequestMethod.PUT)
    public ResultEntity update(@ModelAttribute UserDto userDto) throws IOException {


        String originalFilename = "";
        String suffixName = "";
        String fileName = "";
        for (MultipartFile multipartFile : userDto.getFiles()) {
            //获取名字
            originalFilename = multipartFile.getOriginalFilename();
            suffixName  = originalFilename.substring(originalFilename.lastIndexOf("."));
            //重新生成的图片名称
            fileName = UUID.randomUUID().toString().concat(suffixName);
            File file = new File(userImgPath.concat(fileName));
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            multipartFile.transferTo(file);
        }


        logger.info("id为:{}",userDto.getId());
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("username",userDto.getUsername());
        User user = userService.selectOne(wrapper);
        if (user!=null&&user.getId()!=userDto.getId()){
            return ResultEntity.error("您更改的用户名已存在");
        }
        User u = new User();
        BeanUtils.copyProperties(userDto,u);
        //设置密码并加密
        u.setPassword(encoder.encode("123456"));
        u.setProvince(userDto.getValue()[0]);
        u.setCity(userDto.getValue()[1]);
        u.setDistrict(userDto.getValue()[2]);
        u.setUserface(userImgUrl.concat(fileName));
        logger.info("修改的user对象的值为:{}", JSON.toJSONString(u));
        Wrapper<User> wrapper1 = new EntityWrapper();
        wrapper1.eq("id",u.getId());
        userService.update(u,wrapper1);
        return ResultEntity.ok("修改成功");
    }

    //删除
    @RequestMapping(value = "/deleteByUid")
    public ResultEntity deleteByUid(Integer uid) {
        logger.info("id为:{}",uid);
        userService.deleteByUid(uid);
        return ResultEntity.ok("删除成功");
    }






}
