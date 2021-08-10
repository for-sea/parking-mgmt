package com.forsea.exception;

import lombok.Getter;

@Getter
public class WrongPasswordException extends Exception {
    private Integer code;

    public WrongPasswordException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
