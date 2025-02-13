package com.example.scheduleApp.common.exception;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
