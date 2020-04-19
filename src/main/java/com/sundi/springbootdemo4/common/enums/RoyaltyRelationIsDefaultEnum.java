package com.sundi.springbootdemo4.common.enums;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
public enum RoyaltyRelationIsDefaultEnum {

    YES(1, "分账"),
    NO(0, "不分账"),
    ;

    private int code;

    private String message;

    RoyaltyRelationIsDefaultEnum(int code, String message) {
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
