package com.sundi.springbootdemo4.common.enums;

/**
 * @author wangyubing
 * @date 2020/4/14
 */
public enum OrderStsEnum {

    ALL(1, "支持解冻和扣款"),
    UN_FREEZE_OVER(2, "解冻完成"),
    PAY_OVER(3, "扣款完成");

    private int code;

    private String message;

    OrderStsEnum(int code, String message) {
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
