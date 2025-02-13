package com.example.scheduleApp.service;

import com.example.scheduleApp.common.config.PasswordEncoder;
import com.example.scheduleApp.dto.LoginResponseDto;
import com.example.scheduleApp.common.exception.AuthenticationFailException;
import com.example.scheduleApp.common.exception.NoSuchEmailException;
import com.example.scheduleApp.repository.UserRepository;
import com.example.scheduleApp.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String username, String password, String email) { // 회원가입
        // passwordEncoder를 통한 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 새로운 User객체에 암호화된 password 주입
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setEmail(email);
        // 데이터베이스에 유저 저장
        userRepository.save(newUser);
    }

    public LoginResponseDto login(String email, String password) { // 로그인
        User user = userRepository.findByEmail(email) // 이메일로 유저 조회, 없으면 예외
                .orElseThrow(()-> new NoSuchEmailException("User with email " + email + " not found"));

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationFailException("Wrong password");
        }

        // 로그인 성공 시, 세션에
        HttpSession session = request.getSession();
    }
}
