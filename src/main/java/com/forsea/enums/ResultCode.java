package com.forsea.enums;

public enum ResultCode {
    SUCCESS(0, "操作成功"),
    FAIL(1, "操作失败"),
    ARGS_NOT_VALIDATED(1001, "参数校验不通过"),
    USER_EXIST(1002, "用户已存在")
    ;
    private Integer code;
    private String message;

    ResultCode(Integer code, java.lang.String message) {
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
