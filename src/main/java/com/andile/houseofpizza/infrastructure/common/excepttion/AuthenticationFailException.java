package com.andile.houseofpizza.infrastructure.common.excepttion;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
