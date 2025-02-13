package com.example.service;

import com.example.config.PasswordEncoder;
import com.example.scheduleApp.repository.UserRepository;
import com.example.scheduleApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(String username, String password, String email) {
        // passwordEncoder를 통한 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 새로운 User객체에 암호화된 password 주입
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(email);

        // User 저장 및 반환
        return userRepository.save(newUser);
    }
}
