package com.forsea.exception;

import lombok.Getter;

@Getter
public class UserExistException extends Exception {
    private Integer code;

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
