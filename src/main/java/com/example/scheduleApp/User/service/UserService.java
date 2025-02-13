package com.example.scheduleApp.User.service;

import com.example.scheduleApp.User.dto.UserResponseDto;
import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.User.repository.UserRepository;
import com.example.scheduleApp.common.config.PasswordEncoder;
import com.example.scheduleApp.common.exception.ScheduleNotFoundException;
import com.example.scheduleApp.common.exception.UnauthorizedAccessException;
import com.example.scheduleApp.common.exception.UserNotFoundException;
import com.example.scheduleApp.schedule.dto.ScheduleResponseDto;
import com.example.scheduleApp.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto save(String userName, String email, String password) { // 유저 생성

        String encodedPassword = passwordEncoder.encode(password); // 비밀번호 암호화

        User user = new User(userName, email, encodedPassword);
        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail());
    }


    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        return UserResponseDto.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(UserResponseDto.toDto(user));
        }
        return dtos;
    }

    @Transactional
    public UserResponseDto updateEmailOrPassword(Long id, String userName, String email, String password,User loginUser) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        validateAccount(id, user); // 검증
        user.updateEmailOrPassword(email, password);
        return UserResponseDto.toDto(user);
    }

    @Transactional
    public void delete(Long id, User loginUser) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        validateAccount(id, user);
        userRepository.delete(user);
    }

    private void
    validateAccount(Long id, User loginUser) {   // 본인 검증
        if (!loginUser.getId().equals(id)) {
            throw new UnauthorizedAccessException("No authorization");
        }
    }
}

