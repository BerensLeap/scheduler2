package com.example.scheduleApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일은 필수")
    @Email(message = "유요한 이메일이 아님")
    private final String email;
    @NotBlank(message = "비밀번호는 필수")
    private final String password;
}
