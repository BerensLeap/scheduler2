package com.example.scheduleApp.controller;

import com.example.scheduleApp.config.PasswordEncoder;
import com.example.scheduleApp.dto.LoginRequestDto;
import com.example.scheduleApp.repository.UserRepository;
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
public class LoginController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

    }
}
