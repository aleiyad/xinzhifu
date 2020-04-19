package com.sundi.springbootdemo4.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.TGoods;
import com.sundi.springbootdemo4.service.ITGoodsService;
import com.sundi.springbootdemo4.vo.TGoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-12-24
 */
@RestController
@RequestMapping("/tGoods")
@Slf4j
public class TGoodsController {

    @Autowired
    private ITGoodsService itGoodsService;

    /**
     * 查询列表
     * @return
     */
    @RequestMapping(value = "/selectList",method = RequestMethod.GET)
    public ResultEntity select(){
        Wrapper wrapper = new EntityWrapper();
        List<TGoods> list = itGoodsService.selectList(wrapper);
        return ResultEntity.ok(list);
    }

    /**
     * 发布商品
     * @param tGoods
     * @return
     */
    @RequestMapping("/addTGoods")
    public ResultEntity addList(@RequestBody TGoods tGoods){
        log.info("后台接收的数据为:{}",tGoods);
        itGoodsService.insert(tGoods);
        return ResultEntity.ok("发布成功");
    }

}
