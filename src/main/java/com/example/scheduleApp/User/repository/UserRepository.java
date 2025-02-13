package com.example.scheduleApp.User.repository;


import com.example.scheduleApp.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String email);   // 이메일로 회원 조회

     Optional<User> findByEmailAndPassword(String email, String password);
}
