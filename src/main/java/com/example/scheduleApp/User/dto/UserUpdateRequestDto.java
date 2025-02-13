package com.example.scheduleApp.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private Long userId; // 유저 id

    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email; // 변경할 이메일

    private String password; // 새 비밀번호

    private String currentPassword; // 현재 비밀번호 (비밀번호 변경 시 검증)
}
