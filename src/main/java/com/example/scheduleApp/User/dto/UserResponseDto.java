package com.example.scheduleApp.User.dto;

import com.example.scheduleApp.User.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponseDto {

    private final Long id;
    private final String userName;
    private final String email;

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
