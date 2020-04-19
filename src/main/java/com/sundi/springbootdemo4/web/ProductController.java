package com.sundi.springbootdemo4.web;

import com.google.zxing.WriterException;
import com.sundi.springbootdemo4.bean.Response;
import com.sundi.springbootdemo4.bean.product.ProductBO;
import com.sundi.springbootdemo4.bean.product.ProductListRequestBO;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author wangyubing
 * @date 2020/4/711
 */
@RestController
@RequestMapping(value = "/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "/list")
    public Response list(@RequestBody(required = false) ProductListRequestBO requestBO) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(iProductService.list(requestBO)).build();
    }

    @RequestMapping(value = "/save")
    public Response save(@RequestBody ProductBO productBo) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(iProductService.save(productBo)).build();
    }

    @RequestMapping(value = "/qrCode/{id}")
    public Response qrCode(@PathVariable(required = true) Long id) throws Exception {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(iProductService.qrCode(id)).build();
    }

}
