package com.sundi.springbootdemo4.common.enums;

/**
 * @author wangyubing
 * @date 2020/4/11
 */
public enum OrderStatusEnum {

    INIT("INIT","初始"),
    SUCCESS("SUCCESS","成功"),
    CLOSED("CLOSED","关闭");

    private String status;

    private String message;

    OrderStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
