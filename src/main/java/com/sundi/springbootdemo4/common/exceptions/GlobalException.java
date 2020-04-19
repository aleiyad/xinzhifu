package com.sundi.springbootdemo4.common.exceptions;

import com.sundi.springbootdemo4.common.enums.ResponseEnum;

import javax.validation.constraints.NotNull;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
public class GlobalException extends RuntimeException {

    private Integer code;

    private String message;

    public GlobalException(@NotNull ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public GlobalException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public GlobalException(Throwable cause) {
        super(cause);
    }

    protected GlobalException(Integer code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
