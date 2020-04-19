package com.sundi.springbootdemo4.web;

import com.sundi.springbootdemo4.config.ResultEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @RequestMapping(value = "/login_p")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResultEntity loginP(){
        return ResultEntity.ok("尚未登录");
    }
}
