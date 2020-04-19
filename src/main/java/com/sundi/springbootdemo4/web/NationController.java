package com.sundi.springbootdemo4.web;


import com.sundi.springbootdemo4.service.INationService;
import com.sundi.springbootdemo4.vo.NationVo;
import com.sundi.springbootdemo4.config.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 城市字典表 前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-12-13
 */
@RestController
@RequestMapping("/nation")
public class NationController {

    @Autowired
    INationService nationService;

    @RequestMapping(value = "/selectList",method = RequestMethod.GET)
    public ResultEntity selectList(){

        List<NationVo> list = nationService.selectList();
        return ResultEntity.ok(list);
    }

}
