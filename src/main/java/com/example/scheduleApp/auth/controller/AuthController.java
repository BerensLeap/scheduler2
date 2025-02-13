package com.example.scheduleApp.auth.controller;
import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.auth.dto.LoginRequestDto;
import com.example.scheduleApp.auth.service.AuthService;
import com.example.scheduleApp.common.config.PasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;


    //유저 로그인 기능.
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

        User user = authService.findByEmail(requestDto.getEmail()); // 이메일로 조회한 user 객체
        if (user == null) { // 조회한 user가 null이라면.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 비밀번호 불일치, 로그인 실패
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user.toString()); // 로그인 성공
        }
    }

    // 로그아웃, 세션 만료
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null) {
        session.invalidate();}
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
