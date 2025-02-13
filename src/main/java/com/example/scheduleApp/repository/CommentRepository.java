package com.example.scheduleApp.repository;

import com.example.scheduleApp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
