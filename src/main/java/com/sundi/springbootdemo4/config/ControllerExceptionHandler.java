package com.sundi.springbootdemo4.config;

import com.sundi.springbootdemo4.bean.Response;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.common.exceptions.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception e) {
        String path = request.getRequestURL().toString();
        log.error("exceptionHandler服务错误,接口=[{}],错误原因=[{}]", path, e.getCause());
        e.printStackTrace();
        return Response.error();
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Response httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest request, Exception e) {
        String path = request.getRequestURL().toString();
        log.error("exceptionHandler服务错误,接口=[{}],错误原因=[{}]", path, e.getCause());
        return Response.error(ResponseEnum.HTTP_METHOD_ERROR);
    }

    @ExceptionHandler(value = GlobalException.class)
    public Response globalHandler(HttpServletRequest request, GlobalException e) {
        String path = request.getRequestURL().toString();
        log.error("globalHandler预计中的错误,接口=[{}],错误信息=[{}]", path, e.getMessage());
        e.printStackTrace();
        return Response.error(e.getCode(), e.getMessage());
    }
}
