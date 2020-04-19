package com.sundi.springbootdemo4.common.enums;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
public enum RoyaltyRelationTypeEnum {
    USER_ID("userId", "支付宝账号对应的支付宝唯一用户号"),
    LOGIN_NAME("loginName", "用户的支付宝登录号"),
    ;

    private String type;

    private String message;

    RoyaltyRelationTypeEnum(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
