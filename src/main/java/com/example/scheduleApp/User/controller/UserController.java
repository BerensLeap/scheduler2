package com.example.scheduleApp.User.controller;

import com.example.scheduleApp.User.dto.UserCreateRequestDto;
import com.example.scheduleApp.User.dto.UserResponseDto;
import com.example.scheduleApp.User.dto.UserUpdateRequestDto;
import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.User.service.UserService;
import com.example.scheduleApp.common.Const;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //  유저 회원가입
    @PostMapping("/users/signup")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto requestDto) {
        UserResponseDto responseDto = userService.save(requestDto.getUserName(), requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok(responseDto);
    }

    // 유저 id 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    // 모든 유저 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 이메일 || 패스워드 업데이트
    @PatchMapping("/users/{userId}/")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto requestDto
            , @SessionAttribute(name = Const.LOGIN_USER,required = false)User loginUser) {
        UserResponseDto updatedUser = userService.updateEmailOrPassword(
                userId,
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getCurrentPassword(), // 현재 비밀번호 검증용
                loginUser
        );
        return ResponseEntity.ok(updatedUser);
    }

    // 단일 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId,@SessionAttribute(name = Const.LOGIN_USER,required = false)User loginUser) {
        userService.delete(userId, loginUser);
        return ResponseEntity.ok("User deleted successfully");
    }
}
