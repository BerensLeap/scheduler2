package com.example.scheduleApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {
    private final String message;
    private final Long userId;
}