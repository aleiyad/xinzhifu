package com.sundi.springbootdemo4.web;


import com.sundi.springbootdemo4.bean.Response;
import com.sundi.springbootdemo4.bean.order.OrderListRequestBO;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangyubing
 * @date 2020/4/11
 */
@RestController
@RequestMapping(value = "/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/list")
    public Response list(@RequestBody(required = false) OrderListRequestBO requestBO) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(orderService.list(requestBO)).build();
    }

    @RequestMapping(value = "/thaw/{id}")
    public Response thaw(@PathVariable Long id) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(orderService.thaw(id)).build();
    }

    @RequestMapping(value = "/tradePay/{id}")
    public Response tradePay(@PathVariable Long id) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(orderService.tradePay(id)).build();
    }
}
