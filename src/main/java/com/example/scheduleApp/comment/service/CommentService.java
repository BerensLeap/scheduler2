package com.example.scheduleApp.comment.service;

import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.User.repository.UserRepository;
import com.example.scheduleApp.comment.dto.CommentResponseDto;
import com.example.scheduleApp.comment.entity.Comment;
import com.example.scheduleApp.comment.repository.CommentRepository;
import com.example.scheduleApp.common.exception.ScheduleNotFoundException;
import com.example.scheduleApp.common.exception.UserNotFoundException;
import com.example.scheduleApp.schedule.entity.Schedule;
import com.example.scheduleApp.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.CommonDataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommonDataSource commonDataSource;


    // 댓글 생성
    @Transactional
    public CommentResponseDto save(String content, Long scheduleId, User user) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow( // 스케줄 조회
                () -> new ScheduleNotFoundException("Schedule not found"));

        Comment comment = new Comment(content, user, schedule); // 댓글 객체 생성

        Comment savedComment = commentRepository.save(comment); // 댓글 저장

        return new CommentResponseDto(
                savedComment.getId(),
                savedComment.getContent(),
                schedule.getTitle(),
                schedule.getContent(),
                user.getUserName(),
                user.getEmail(),
                savedComment.getCreatedAt(),
                savedComment.getUpdatedAt()
        );
    }

    // 유저id로 작성한 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findALLByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found"));

        //해당 유저가 작성한 모든 댓글
        List<Comment> comments = commentRepository.findByUser(user);

        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getSchedule().getTitle(),
                        comment.getSchedule().getContent(),
                        user.getUserName(),
                        user.getEmail(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                )).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllByScheduleId(Long scheduleId) {
        List<Comment> comments = commentRepository.findAllBySchedule_id(scheduleId);
        return comments.stream()
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getSchedule().getTitle(),
                        comment.getSchedule().getContent(),
                        comment.getUser().getUserName(),
                        comment.getUser().getEmail(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                )).collect(Collectors.toList());
    }

    // 댓글 수정
}
