package com.example.scheduleApp.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 여러 예외를 한 번에 처리
    @ExceptionHandler({AuthenticationFailException.class, NoSuchEmailException.class})
    public ResponseEntity<String> handleAllException(RuntimeException ex, HttpServletRequest request) {// 예외 메시지와 함께 HTTP 상태 코드 반환
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);  // HTTP Status 401 반환
    }
}