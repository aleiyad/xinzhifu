package com.sundi.springbootdemo4.bean;

import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/7
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Response {

    private Integer code;

    private String message;

    private Object obj;


    public static Response success() {
        return success(ResponseEnum.SUCCESS);
    }

    public static Response success(ResponseEnum responseEnum) {
        return success(responseEnum.getCode(), responseEnum.getMessage(), null);
    }

    public static Response success(Object data) {
        return success(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }

    public static Response error() {
        return error(ResponseEnum.ERROR);
    }

    public static Response error(ResponseEnum responseEnum) {
        return error(responseEnum.getCode(), responseEnum.getMessage());
    }

    public static Response error(Integer code, String message) {
        return error(code, message, null);
    }


    public static Response success(Integer code, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setObj(data);
        return response;
    }

    public static Response error(Integer code, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setObj(data);
        return response;
    }
}
