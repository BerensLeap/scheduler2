package com.example.scheduleApp.User.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCreateRequestDto {

    @NotBlank(message = "이름을 입력하세요.")
    @Size(max = 5,message = "이름은 5자 이내로 입력하세요")
    private String userName;

    @NotBlank(message = "email(로그인ID)을 입력하세요.")
    @Email(message = "올바르지 않은 email 주소입니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(max = 12, message = "비밀번호는 12자 이내로 입력하세요." )
    private String password;

}
