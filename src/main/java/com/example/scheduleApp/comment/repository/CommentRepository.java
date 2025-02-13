package com.example.scheduleApp.comment.repository;

import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule_id(Long scheduleId);

    List<Comment> findAllByUser_id(Long userId);

    List<Comment> findByUser(User user);
}
