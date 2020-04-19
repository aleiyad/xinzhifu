package com.sundi.springbootdemo4.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sundi.springbootdemo4.config.ResultEntity;
import com.sundi.springbootdemo4.entity.TGoods;
import com.sundi.springbootdemo4.entity.TOrder;
import com.sundi.springbootdemo4.entity.TOrderItem;
import com.sundi.springbootdemo4.entity.User;
import com.sundi.springbootdemo4.service.ITOrderItemService;
import com.sundi.springbootdemo4.service.ITOrderService;
import com.sundi.springbootdemo4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/tOrder")
public class TOrderController {


    @Autowired
    private ITOrderService itOrderService;

    @Autowired
    private ITOrderItemService itOrderItemService;

    @Autowired
    IUserService userService;

    /**
     * 商品下单添加到订单表和详情表
     * @param lists
     * @param authentication
     * @return
     */
    @RequestMapping("/addOrder")
    public ResultEntity addOrder(@RequestBody List<TGoods> lists, Authentication authentication){
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username",authentication.getName());
        User user = userService.selectOne(wrapper);
        TOrder tOrder = new TOrder();
        TOrderItem tOrderItem = new TOrderItem();
        int i=1;
        for (TGoods goods :lists) {
            tOrder.setId(i);
            tOrder.setOrderSn("100"+i);
            tOrder.setUserId(user.getId());
            tOrder.setCreated(goods.getCreated());
            tOrder.setStatus(1);
            tOrder.setTotalPrice(goods.getPrice());
            itOrderService.insert(tOrder);
            tOrderItem.setOid(tOrder.getId());
            tOrderItem.setGid(goods.getId());
            tOrderItem.setAmount(1);
            tOrderItem.setPrice(goods.getPrice());
            itOrderItemService.insert(tOrderItem);
            i++;
        }
        return ResultEntity.ok("下单成功");
    }

    /**
     * 查询列表
     * @return
     */
    @RequestMapping(value = "/orderList",method = RequestMethod.GET)
    public ResultEntity orderList(){
        List<TOrder> list =itOrderService.selectObject();
        return ResultEntity.ok(list);
    }

    /**
     * 修改商品状态
     * @param tOrder
     * @return
     */
    @RequestMapping(value = "/updOrders",method = RequestMethod.PUT)
    public ResultEntity updOrder(@RequestBody TOrder tOrder){
        Wrapper<TOrder> wrapper = new EntityWrapper();
        wrapper.eq("id",tOrder.getId());
        itOrderService.update(tOrder,wrapper);
        return ResultEntity.ok("修改成功");
    }

}
