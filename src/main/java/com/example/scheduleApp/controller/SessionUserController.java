package com.example.scheduleApp.controller;

import com.example.scheduleApp.dto.LoginRequestDto;
import com.example.scheduleApp.entity.User;
import com.example.scheduleApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SessionUserController {

    private final UserService userService;

    @PostMapping("/session-login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto requestDto) {
        // 로그인 처리
        userService.login(requestDto.getEmail(), requestDto.getPassword())

    }
}