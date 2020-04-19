package com.sundi.springbootdemo4.web;

import com.sundi.springbootdemo4.bean.Response;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationAddBO;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationRequestBO;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.service.IRoyaltyRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分账controller
 *
 * @author wangyubing
 * @date 2020/4/12
 */
@RestController
@RequestMapping(value = "/royalty/relation")
@CrossOrigin("*")
public class RoyaltyRelationController {

    @Autowired
    private IRoyaltyRelationService iRoyaltyRelationService;

    @RequestMapping(value = "/list")
    public Response list(@RequestBody(required = false) RoyaltyRelationRequestBO requestBO) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(iRoyaltyRelationService.list(requestBO)).build();
    }

    @RequestMapping(value = "/add")
    public Response add(@RequestBody RoyaltyRelationAddBO relationBO) {
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).obj(iRoyaltyRelationService.add(relationBO)).build();
    }

    @RequestMapping(value = "/remove/{id}")
    public Response remove(@PathVariable Long id) {
        iRoyaltyRelationService.remove(id);
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).build();
    }

    @RequestMapping(value = "/set/default/{id}")
    public Response setDefault(@PathVariable Long id) {
        iRoyaltyRelationService.setDefault(id);
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).build();
    }

    @RequestMapping(value = "/cancel/default")
    public Response cancelDefault(@PathVariable Long id) {
        iRoyaltyRelationService.cancelDefault(id);
        return Response.builder().code(ResponseEnum.SUCCESS.getCode()).message(ResponseEnum.SUCCESS.getMessage()).build();
    }
}
