package com.forsea.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private Integer code;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(String message) {
        super(message);
    }
}
