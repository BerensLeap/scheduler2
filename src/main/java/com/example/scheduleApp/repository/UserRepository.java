package com.example.scheduleApp.repository;

import com.example.scheduleApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
