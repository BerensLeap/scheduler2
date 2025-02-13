package com.example.scheduleApp.common.exception;

public class NoSuchEmailException extends RuntimeException {
    public NoSuchEmailException(String message) {
        super(message);
    }
}
