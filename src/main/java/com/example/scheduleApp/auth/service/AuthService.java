package com.example.scheduleApp.auth.service;

import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public User findByEmail(String email) { // 이메일로 데이터베이스에서 유저 단일 건 조회.
        return findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}
