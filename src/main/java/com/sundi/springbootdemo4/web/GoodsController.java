package com.sundi.springbootdemo4.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.Goods;
import com.sundi.springbootdemo4.service.IGoodsService;
import com.sundi.springbootdemo4.vo.GoodsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2019-12-22
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("/goodsList")
    public ResultEntity selects(GoodsVo goodsVo){
        PageInfo<Goods> list = goodsService.selectObject(goodsVo);
        return ResultEntity.ok(list);
    }

    @RequestMapping(value = "updStatus",method = RequestMethod.PUT)
    public ResultEntity updStatus(GoodsVo goodsVo){
        Wrapper<Goods> wrapper = new EntityWrapper<Goods>();
        wrapper.eq("id",goodsVo.getId());
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVo,goods);
        goodsService.update(goods,wrapper);
        return ResultEntity.ok(goods.getStatus()==1? "商品上架成功" : "商品下架成功");
    }
    @RequestMapping(value = "updDeleted",method = RequestMethod.PUT)
    public ResultEntity updDeleted(GoodsVo goodsVo){
        Wrapper<Goods> wrapper = new EntityWrapper<Goods>();
        wrapper.eq("id",goodsVo.getId());
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVo,goods);
        goodsService.update(goods,wrapper);
        return ResultEntity.ok("逻辑删除成功");
    }

}
