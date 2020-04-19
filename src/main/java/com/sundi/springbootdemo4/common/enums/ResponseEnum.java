package com.sundi.springbootdemo4.common.enums;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
public enum ResponseEnum {
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "请联系管理员"),
    MISSING_PARAMETERS(40000, "缺少必要参数"),
    BAD_REQUEST(400, "不理解的请求"),
    HTTP_METHOD_ERROR(40001, "请求方法不支持");

    private int code;

    private String message;

    ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
