package com.sundi.springbootdemo4.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.Goods;
import com.sundi.springbootdemo4.entity.Type;
import com.sundi.springbootdemo4.service.IGoodsService;
import com.sundi.springbootdemo4.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-12-22
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @RequestMapping("/getType")
    public ResultEntity selects(){
        Wrapper wrapper = new EntityWrapper<>();
        List<Type> list = typeService.selectList(wrapper);
        return ResultEntity.ok(list);
    }

}
