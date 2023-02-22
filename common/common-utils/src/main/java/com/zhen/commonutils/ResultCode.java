package com.zhen.commonutils;

/**
 * @author koala
 * @Description: global 变量定义
 * @date 2021/7/69:48
 */
public enum ResultCode {


    SUCCESS(20000,"成功"),
    ERROR(20001, "失败"),
    ADD_ERROR(20002,"数据添加失败"),
    CHANGE_ERROR(20003,"数据修改失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

