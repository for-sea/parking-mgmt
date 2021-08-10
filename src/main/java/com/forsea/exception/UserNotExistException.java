package com.forsea.exception;

import lombok.Getter;

@Getter
public class UserNotExistException extends Exception {
    private Integer code;

    public UserNotExistException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public UserNotExistException(String message) {
        super(message);
    }
}
