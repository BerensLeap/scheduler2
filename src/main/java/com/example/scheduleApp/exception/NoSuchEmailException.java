package com.example.scheduleApp.exception;

public class NoSuchEmailException extends RuntimeException {
    public NoSuchEmailException(String message) {
        super(message);
    }
}
