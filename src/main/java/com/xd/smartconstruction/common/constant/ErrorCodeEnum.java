package com.xd.smartconstruction.common.constant;

/**
 * @author mm
 * @Date 2020-11-27 14:10
 */
public enum ErrorCodeEnum {
    RUN_TIME_ERROR("500", "Server internal error"),

    HTTP_REQ_EXP("501", "http请求异常"),

    PARAM_VALID_ERRPR("10001", "request valid error"),

    USER_NO_POWER("10002", "用户没有权限登录"),

    USER_NOT_EXIST("10003", "用户不存在"),



    UNKNOWN("-1", "Unknown error");

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
