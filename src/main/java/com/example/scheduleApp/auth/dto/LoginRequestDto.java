package com.example.scheduleApp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class LoginRequestDto { // 이메일과 비밀번호만 사용.

    @NotBlank(message = "email을 입력하세요.")
    @Email(message = "유효한 email이 아닙니다.")
    private final String email;
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
