package com.sundi.springbootdemo4.config;

import lombok.Data;

@Data
public class ResultEntity {
    private String code;
    private String msg;
    private Object obj;

    public ResultEntity(String code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public static ResultEntity ok(String code, String msg, Object o) {
        return new ResultEntity(code, msg, o);
    }

    public static ResultEntity ok() {
        return new ResultEntity("200", "操作成功", null);
    }

    public static ResultEntity ok(String msg, Object o) {
        return new ResultEntity("200", msg, o);
    }

    public static ResultEntity ok(Object o) {
        return new ResultEntity("200", "操作成功", o);
    }
    public static ResultEntity ok(String msg){
        return new ResultEntity("200",msg,null);
    }


    public static ResultEntity error(String code, String msg, Object o) {
        return new ResultEntity(code, msg, o);
    }

    public static ResultEntity error() {
        return new ResultEntity("500", "操作失败", null);
    }

    public static ResultEntity error(String msg, Object o) {
        return new ResultEntity("500", msg, o);
    }

    public static ResultEntity error(Object o) {
        return new ResultEntity("500", "操作失败", o);
    }
    public static ResultEntity error(String msg){
        return new ResultEntity("500",msg,null);
    }
}

